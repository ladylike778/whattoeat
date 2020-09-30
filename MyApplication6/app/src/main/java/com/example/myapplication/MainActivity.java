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
import android.view.inputmethod.InputMethodManager;
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
    String db_name="whattoeat",tb_name="foodname";
    String[]a={"炒麵","炒飯","漢堡","披薩","義大利麵","三明治"};
    ArrayList<String> menu= new ArrayList<>(Arrays.asList(a));
    TextView tx1,tx2,tx3,tx5;
    EditText editText;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        pref = getSharedPreferences("test", MODE_PRIVATE);
        tx1=(TextView)findViewById(R.id.textView2);
        tx2=(TextView)findViewById(R.id.textView3);
        tx3=(TextView)findViewById(R.id.textView);
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

        db=openOrCreateDatabase(db_name, Context.MODE_PRIVATE,null);{
            String createTable="CREATE TABLE IF NOT EXISTS "+ tb_name+ "(foodname VARCHAR(64),dt1 text)";
            db.execSQL(createTable);
            db.close();
        }
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
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(MainActivity.this.getCurrentFocus().getWindowToken()
                ,InputMethodManager.HIDE_NOT_ALWAYS);
        editText.setText("");
    }

    public void fooddata(View v){
        Intent intent= new Intent(MainActivity.this,Main3Activity.class);
        startActivity(intent);
        }
    private void addData(String abc) {
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("MM/dd  HH:mm:ss");
        String dataString=sdf.format(date);

        ContentValues cv = new ContentValues(1);
        cv.put("foodname", abc);
        cv.put("dt1",dataString);
        db.insert(tb_name, null, cv);
        db.close();
    }
    public void cdt(View v){
        db=openOrCreateDatabase(db_name, Context.MODE_PRIVATE,null);
        String wx="DROP TABLE IF EXISTS "+ tb_name;
        db.execSQL(wx);
        db.close();
    }
}

