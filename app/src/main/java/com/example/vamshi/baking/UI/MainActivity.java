package com.example.vamshi.baking.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.vamshi.baking.Adapters.ListViewAdapter;
import com.example.vamshi.baking.Data.DownloadTask;
import com.example.vamshi.baking.Data.Recipe;
import com.example.vamshi.baking.R;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;


public class MainActivity extends AppCompatActivity {


    public static ListView myList;
    public static ListAdapter myAdapter;
    public static Realm realm;


    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Realm.init(this);
        realm = Realm.getDefaultInstance();

        DownloadTask newTask = new DownloadTask();
        newTask.execute("hi");
        setContentView(R.layout.activity_main);
        myList = (ListView) findViewById(R.id.Recipe_list);

       // getData();

        setDisplay();

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String p = String.valueOf(position);
                Intent in = new Intent(MainActivity.this, SecondScreenDetails.class);
                in.putExtra("Position", p);
                startActivity(in);
            }
        });

    }

    public void setDisplay(){

        ArrayList<Recipe> finalRecipies = new ArrayList<>();
        RealmResults<Recipe> rrRecipies = realm.where(Recipe.class).findAll();

        for(Recipe r: rrRecipies){
            finalRecipies.add(r);
            Toast.makeText(this, r.getName(), Toast.LENGTH_SHORT).show();
        }
        myAdapter = new ListViewAdapter(this, finalRecipies);
        myList.setAdapter(myAdapter);

    }


    @Override
    protected void onDestroy() {
        realm.close();
        super.onDestroy();
    }
}


