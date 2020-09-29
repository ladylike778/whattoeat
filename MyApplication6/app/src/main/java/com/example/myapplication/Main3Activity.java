package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main3Activity extends AppCompatActivity {
    private String rec;

    TextView txx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        txx=(TextView)findViewById(R.id.textView4);
        rec=getIntent().getStringExtra("testt");
        txx.setText(rec);
    }
    public void backmain(View v){
        txx.setText(null);
        Intent intent= new Intent(Main3Activity.this,MainActivity.class);
        startActivity(intent);
    }

}
