package com.example.vamshi.baking.UI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.vamshi.baking.Adapters.ListViewAdapter;
import com.example.vamshi.baking.Data.DownloadTask;
import com.example.vamshi.baking.Data.Ingredients;
import com.example.vamshi.baking.Data.Recipe;
import com.example.vamshi.baking.Data.Steps;
import com.example.vamshi.baking.R;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    public static List<Recipe> mRecipies;
    public static List<Ingredients> mIngredients;
    public static List<Steps> mSteps;

    private String url = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
    public static ListView myList;
    public static ListAdapter myAdapter;


    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myList = (ListView)findViewById(R.id.Recipe_list);
        mRecipies = new ArrayList<>();
        mIngredients = new ArrayList<>();
        mSteps = new ArrayList<>();
        DownloadTask n = new DownloadTask();
        n.execute(url);
        myAdapter = new ListViewAdapter(this, mRecipies);

    }
}
