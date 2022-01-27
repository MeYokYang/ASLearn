package com.meyok.c3_logininterface;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.meyok.c3_logininterface.utils.Constants;
import com.meyok.c3_logininterface.utils.HttpConnectionUtils;
import com.meyok.c3_logininterface.utils.NetWorkInfoUtils;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    Button mBtnClick;
    EditText mEtUserName, mEtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        initEvent();

    }

    private void initView(){
        mEtUserName = findViewById(R.id.et_username);
        mEtPassword = findViewById(R.id.et_password);
        mBtnClick = findViewById(R.id.btn_login);
    }

    private void initEvent() {
        mBtnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = mEtUserName.getText().toString().trim();
                String password = mEtPassword.getText().toString().trim();

                if (username != null && !username.equals("") && password != null && !password.equals("")){

                    if (NetWorkInfoUtils.isConnected(LoginActivity.this)){
                        login(username, password);
                    }else {
                        Toast.makeText(LoginActivity.this, "请检查网络连接", Toast.LENGTH_LONG).show();
                    }

                }else{
                    Toast.makeText(LoginActivity.this, "用户名或密码不能为空", Toast.LENGTH_LONG).show();
                }


            }
        });
    }


    private void login(String username, String password) {

        //保存用户信息
        saveUserInfo(username, password);

        //显示加载框
        showProgressDialog();

        //普通访问
        new Thread(new Runnable(){
            @Override
            public void run() {
                String currentUrl = Constants.URL + "?cmd=0" + "&name=" + username + "&password" + password;
                String result = HttpConnectionUtils.executeHttpGet(currentUrl);

                if(result != null){

                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        int isLogin = jsonObject.getInt("code");
                        if(isLogin == 0){
                            //实现页面跳转
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        }else {
                            //进行错误提示
                            Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }else {

                }

            }
        }).start();

        //HttpClient访问
        /*new Thread(new Runnable(){
                                @Override
                                public void run() {
                                    String currentUrl = "http://172.21.166.20:8000/swagger/swagger.json";
                                    String result = HttpConnectionUtils.executeHttpClient(currentUrl);
                                    try {
                                        JSONObject jsonObject = new JSONObject(result);
                                        int isLogin = jsonObject.getInt("code");
                                        if(isLogin == 0){
                                            //实现页面跳转
                                            Log.i(TAG, "登录成功");
                                        }else {
                                            //进行错误提示
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    Log.i(TAG, ""+result);
                                }
                            }).start();*/

        //使用xUtils访问
        /*HttpUtils http = new HttpUtils();
        http.send(HttpRequest.HttpMethod.GET, Constants.URL, new RequestCallBack<Object>() {

            @Override
            public void onSuccess(ResponseInfo<Object> responseInfo) {
                if (responseInfo != null) {
                    String result = (String) responseInfo.result;
                    Log.i(TAG, result);
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });*/
    }

    private void saveUserInfo(String username, String password) {

        //MODE_PRIVATE 只有本应用可以访问
        //MODE_WORLD_READABLE 其他应用可以读取
        //MODE_WORLD_WRITABLE 其他应用可以写
        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
        //获取Editor对象
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("name", username);
        editor.putString("password", password);
        editor.commit();

    }

    private void showProgressDialog() {
        //加载提示框，2秒后消失
        ProgressDialog dialog = ProgressDialog.show(LoginActivity.this, "登陆中", "2秒后自动消失");
        new Thread(new Runnable(){

            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                dialog.dismiss();
            }
        }).start();
    }

}