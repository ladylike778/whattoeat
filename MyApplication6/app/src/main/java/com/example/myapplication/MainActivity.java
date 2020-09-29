package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
    TextView tx3;
    TextView tx5;
    EditText editText;
    SharedPreferences pref;
    String testname1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        pref = getSharedPreferences("test", MODE_PRIVATE);
        tx1=(TextView)findViewById(R.id.textView2);
        tx2=(TextView)findViewById(R.id.textView3);
        tx3=(TextView)findViewById(R.id.textView);
        tx5=(TextView)findViewById(R.id.textView5);
        tx3.setSelected(true);
        editText=(EditText)findViewById(R.id.editText);
        tx2.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent= new Intent(MainActivity.this,Main2Activity.class);
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("foods",menu);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
    public void EatWhat(View v){
        int random = new Random().nextInt(menu.size());
        tx1.setVisibility(View.VISIBLE);
        tx1.setText(menu.get(random));

        db=openOrCreateDatabase(db_name, Context.MODE_PRIVATE,null);
        addData(tx1.getText().toString());
        db.close();
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
        db=openOrCreateDatabase(db_name, Context.MODE_PRIVATE,null);{
            String createTable="CREATE TABLE IF NOT EXISTS "+ tb_name+ "(foodname VARCHAR(64))";
            db.execSQL(createTable);
            tx5.setText(db.getPath());
            db.close();
        }
        tx5.setText("");
        db=openOrCreateDatabase(db_name, Context.MODE_PRIVATE,null);
        String[] col={};
        Cursor cursor=db.query(tb_name,col,null,null,null,null,null);
        //StringBuilder resultData=new StringBuilder("");
        //list.clear();
        String result="之前吃過";
        while ((cursor.moveToNext())){
            testname1=cursor.getString(0);
            //resultData.append(testname1).append("\n");
            //list.add(testname1);
            result+=testname1+",";
        }
        tx5.setText(result);


        }
    private void addData(String abc) {
        ContentValues cv = new ContentValues(1);
        cv.put("foodname", abc);
        db.insert(tb_name, null, cv);
    }
    public void cdt(View v){
        db=openOrCreateDatabase(db_name, Context.MODE_PRIVATE,null);
        String wx="DROP TABLE IF EXISTS "+ tb_name;
        db.execSQL(wx);

    }
    }

