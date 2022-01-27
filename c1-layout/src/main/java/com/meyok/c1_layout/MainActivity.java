package com.meyok.c1_layout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submit(View view) {
        Toast.makeText(this, "submit button", Toast.LENGTH_LONG).show();
    }

    public void save(View view) {
        Toast.makeText(this, "submit imageButton", Toast.LENGTH_LONG).show();
    }
}