package com.example.vamshi.baking.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.vamshi.baking.Adapters.ListViewAdapter;
import com.example.vamshi.baking.Data.Recipe;
import com.example.vamshi.baking.R;
import com.example.vamshi.baking.Retrofit.IRecipe;
import com.example.vamshi.baking.Retrofit.RetrofitBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {


    public static ListView myList;
    public static ListAdapter myAdapter;


    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myList = (ListView) findViewById(R.id.Recipe_list);
        setDisplay();
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String p = String.valueOf(position);
//                Toast.makeText(MainActivity.this, p, Toast.LENGTH_SHORT).show();
                Intent in = new Intent(MainActivity.this, SecondScreenDetails.class);
                in.putExtra("Position", p);
                startActivity(in);
            }
        });

    }

    public void setDisplay(){

        IRecipe irecipie = RetrofitBuilder.Retrieve();
        final Call<ArrayList<Recipe>> recipie = irecipie.getRecipe();
        recipie.enqueue(new Callback<ArrayList<Recipe>>() {
            @Override
            public void onResponse(Call<ArrayList<Recipe>> call, Response<ArrayList<Recipe>> response) {
                ArrayList<Recipe> mRecipies = new ArrayList<>();
                mRecipies = response.body();
                myAdapter = new ListViewAdapter(getApplicationContext(), mRecipies);
                myList.setAdapter(myAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Recipe>> call, Throwable t) {

            }
        });


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}


