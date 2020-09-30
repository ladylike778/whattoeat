package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class Main2Activity extends AppCompatActivity {
    ListView listView;
    private ArrayList abcd;
    private ArrayAdapter<String> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        abcd=getIntent().getStringArrayListExtra("foods");
        listView = (ListView) findViewById(R.id.listView);
        listAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,abcd);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Toast.makeText(getApplicationContext(), (CharSequence) abcd.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
