package com.example.vamshi.baking.UI;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.vamshi.baking.Adapters.MainRecyclerViewAdapter;
import com.example.vamshi.baking.Data.Ingredients;
import com.example.vamshi.baking.Data.Recipe;
import com.example.vamshi.baking.R;
import com.example.vamshi.baking.Retrofit.IRecipe;
import com.example.vamshi.baking.Retrofit.RetrofitBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {


    public RecyclerView myRecipeList;
    public MainRecyclerViewAdapter myAdapter;
    public ArrayList<Recipe> r;
    public ArrayList<Ingredients> i;

    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myRecipeList = (RecyclerView)findViewById(R.id.Recipe_list);
        r = new ArrayList<>();
        // CHECKING NETWORK CONNECTION
        if(!isNetworkAvailable()){
            Toast.makeText(this, "Please Connect To The Internet!", Toast.LENGTH_SHORT).show();
        }else{

            setDisplay();
            FetchData();
//            if (r!=null && !r.isEmpty())
//                setDisplay();
//            else FetchData();
//
//            if(savedInstanceState==null){
//                FetchData();
//            }else{
//                onRestoreInstanceState(savedInstanceState);
//                setDisplay();
//            }
        }


    }

    private void setDisplay() {
        myAdapter = new MainRecyclerViewAdapter(r, MainActivity.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        myAdapter.setData(r);
        myRecipeList.setLayoutManager(mLayoutManager);
        myRecipeList.setItemAnimator(new DefaultItemAnimator());
        myRecipeList.setAdapter(myAdapter);

    }

    public void FetchData(){

        IRecipe irecipie = RetrofitBuilder.Retrieve();
        final Call<ArrayList<Recipe>> recipie = irecipie.getRecipe();
        recipie.enqueue(new Callback<ArrayList<Recipe>>() {
            @Override
            public void onResponse(Call<ArrayList<Recipe>> call, Response<ArrayList<Recipe>> response) {
                r = response.body();
                i = r.get(0).getIngredients();
                SharedPreferences sharedPreferences = getSharedPreferences("Ingredients", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                for(int a=0; a<r.size(); a++){
                    i = r.get(a).getIngredients();
                    for (int j =0; j< i.size(); j++){
                        editor.putString("Ingredient"+ String.valueOf(a) + (String.valueOf(j)), i.get(j).getIngredient());
                    }
                }
                editor.apply();
               myAdapter.setData(r);
            }

            @Override
            public void onFailure(Call<ArrayList<Recipe>> call, Throwable t) {

            }
        });

    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public boolean isTablet(Context context) {
        boolean xlarge = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == 4);
        boolean large = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);
        return (xlarge || large);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("FullR", r);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        r = savedInstanceState.getParcelableArrayList("FullR");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.fav_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.Settings: {
                Intent in = new Intent(MainActivity.this, Settings.class);
                startActivity(in);
            }
        }
        return super.onOptionsItemSelected(item);
    }
}




