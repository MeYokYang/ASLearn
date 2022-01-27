package com.meyok.c3_logininterface.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class HttpConnectionUtils {

    public static String executeHttpGet(String currentUrl){
        String result = null;
        HttpURLConnection connection = null;
        InputStreamReader isr = null;

        try {
            URL url = new URL(currentUrl);
            connection = (HttpURLConnection) url.openConnection();
            //默认get方法
//            connection.setRequestMethod("POST");
//            PrintWriter pw = new PrintWriter(connection.getOutputStream());
//            pw.print("cmd=0&name=bdqn&password=123456");
//            pw.flush();
//            pw.close();
            isr = new InputStreamReader(connection.getInputStream());
            BufferedReader br = new BufferedReader(isr);
            StringBuffer sb = new StringBuffer();
            String line;
            while((line = br.readLine()) != null){
                sb.append(line);
            }
            result = sb.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(connection!=null){
                connection.disconnect();
            }
            try {
                if(isr!=null){
                    isr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /*public static String executeHttpClient(String currentUrl){
        String result = null;
        //创建HttpClient对象
        HttpClient client = new DefaultHttpClient();

        //创建请求方式对象
        //get方法
        HttpGet get = new HttpGet(currentUrl);

        //post方法
*//*        HttpPost post = new HttpPost(currentUrl);
        List<NameValuePair> parameters = new Arraylist<NameValuePair>();
        parameters.add(new BasicNameValuePair("cmd","0"));
        parameters.add(new BasicNameValuePair("name","bdqn"));
        parameters.add(new BasicNameValuePair("password","123456"));
        //设置参数的编码
        UrlEncodedFormEntity entity = null;
        try{
            entity = new UrlEncodedFormEntity(parameters, "UTF-8");
        } catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }

        post.setEntity(entity);*//*


        try{
            //获取数据
            HttpResponse response = client.execute(get); //post方法改为post
            //转换赋值
            result = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;


    }*/


}
