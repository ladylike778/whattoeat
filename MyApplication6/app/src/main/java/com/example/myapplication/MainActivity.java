package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;
    String db_name="whattoeat";
    String tb_name="foodname";
    String myData="";
    private String filename="food";
    private String filepath="thefilestorage";
    File myfoodfile;
    String[]a={"麥當勞","肯德基"};
    ArrayList<String> menu= new ArrayList<>(Arrays.asList(a));
    TextView tx1;
    TextView tx2;
    EditText editText;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref = getSharedPreferences("test", MODE_PRIVATE);


        tx1=(TextView)findViewById(R.id.textView2);
        tx2=(TextView)findViewById(R.id.textView3);
        editText=(EditText)findViewById(R.id.editText);

        tx2.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent= new Intent(MainActivity.this,Main2Activity.class);
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("foods",menu);
                intent.putExtras(bundle);   // 記得put進去，不然資料不會帶過去哦
                startActivity(intent);
            }
        });
    }
    public void EatWhat(View v){
        int random = new Random().nextInt(menu.size());
        tx1.setVisibility(View.VISIBLE);
        tx1.setText(menu.get(random));
    }

    public void newfood(View v){
        menu.add(editText.getText().toString());
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("MM/dd  HH:mm:ss");
        String dataString=sdf.format(date);

//        pref.edit().putString("food",editText.getText().toString()).apply();
//        menu.add(getSharedPreferences("test",MODE_PRIVATE).getString("food",""));

    }
    public void fooddata(View v){
        String[] columns={};
        db=openOrCreateDatabase(db_name, Context.MODE_PRIVATE,null);{
            String createTable="CREATE TABLE IF NOT EXISTS "+ tb_name+ "(foodname VARCHAR(64))";
            db.execSQL(createTable);
            db.close();
        }

        }
    }

