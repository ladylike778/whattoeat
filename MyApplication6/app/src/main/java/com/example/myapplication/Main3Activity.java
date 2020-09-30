package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main3Activity extends AppCompatActivity {
    SQLiteDatabase db;
    String db_name="whattoeat",tb_name="foodname", myData="";
    private String rec;

    TextView txx;
    String testname1;
    String testname2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        txx=(TextView) findViewById(R.id.textView4);

        txx.setText("");
        db=openOrCreateDatabase(db_name, Context.MODE_PRIVATE,null);
        Toast toast = Toast.makeText(this, db.getPath(), Toast.LENGTH_SHORT);
        Display display = getWindowManager().getDefaultDisplay();
        int height = display.getHeight();
        toast.getView().setBackgroundColor(Color.LTGRAY);
        toast.setGravity(Gravity.TOP, 0, height / 8);
        toast.show();
        String[] col={};
        Cursor cursor=db.query(tb_name,col,null,null,null,null,null);

        String result="之前吃了:"+"\n";
        while ((cursor.moveToNext())){
            testname1=cursor.getString(0);
            testname2=cursor.getString(1);
            result+=testname2+testname1+"\n";
        }
        txx.setText(result);
        db.close();
    }
    public void backmain(View v){
        txx.setText(null);
        Intent intent= new Intent(Main3Activity.this,MainActivity.class);
        startActivity(intent);
    }
    public void cdt(View v){
        db=openOrCreateDatabase(db_name, Context.MODE_PRIVATE,null);
        String wx="DROP TABLE IF EXISTS "+ tb_name;
        db.execSQL(wx);
        db.close();
    }

}
