package com.example.vamshi.baking.UI;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.vamshi.baking.Adapters.MainRecyclerViewAdapter;
import com.example.vamshi.baking.Data.Recipe;
import com.example.vamshi.baking.R;
import com.example.vamshi.baking.Retrofit.IRecipe;
import com.example.vamshi.baking.Retrofit.RetrofitBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {


    @BindView(R.id.Recipe_list)RecyclerView myRecipeList;
    public MainRecyclerViewAdapter myAdapter;

    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // CHECKING NETWORK CONNECTION
        if(!isNetworkAvailable()){
            Toast.makeText(this, "Please Connect To The Internet!", Toast.LENGTH_SHORT).show();
        }else{
            setDisplay();
        }
    }

    public void setDisplay(){

        IRecipe irecipie = RetrofitBuilder.Retrieve();
        final Call<ArrayList<Recipe>> recipie = irecipie.getRecipe();
        recipie.enqueue(new Callback<ArrayList<Recipe>>() {
            @Override
            public void onResponse(Call<ArrayList<Recipe>> call, Response<ArrayList<Recipe>> response) {
                ArrayList<Recipe> r = response.body();
                myAdapter = new MainRecyclerViewAdapter(r, MainActivity.this);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                myRecipeList.setLayoutManager(mLayoutManager);
                myRecipeList.setItemAnimator(new DefaultItemAnimator());
                myRecipeList.setAdapter(myAdapter);
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
}




