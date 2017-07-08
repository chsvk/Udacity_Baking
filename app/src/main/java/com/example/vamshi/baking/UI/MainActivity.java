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
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {


    public static ListView myList;
    public static ListAdapter myAdapter;
    public List<Recipe> mRecipies;


    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myList = (ListView) findViewById(R.id.Recipe_list);
        setDisplay();


    }

    public void setDisplay(){

        IRecipe irecipie = RetrofitBuilder.Retrieve();
        Call<ArrayList<Recipe>> recipie = irecipie.getRecipe();
        recipie.enqueue(new Callback<ArrayList<Recipe>>() {
            @Override
            public void onResponse(Call<ArrayList<Recipe>> call, Response<ArrayList<Recipe>> response) {
                mRecipies = response.body();
                myAdapter = new ListViewAdapter(getApplicationContext(), mRecipies);
                myList.setAdapter(myAdapter);
                myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent in = new Intent(MainActivity.this, SecondScreenDetails.class);
                        in.putExtra("Position", String.valueOf(position));
                        startActivity(in);
                    }
                });
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


