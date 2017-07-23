package com.example.vamshi.baking.UI;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import com.example.vamshi.baking.Adapters.MainRecyclerViewAdapter;
import com.example.vamshi.baking.Adapters.SecondScreenRecyclerViewAdapter;
import com.example.vamshi.baking.Data.Ingredients;
import com.example.vamshi.baking.Data.Recipe;
import com.example.vamshi.baking.Data.Steps;
import com.example.vamshi.baking.R;
import com.example.vamshi.baking.Retrofit.IRecipe;
import com.example.vamshi.baking.Retrofit.RetrofitBuilder;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SecondScreenDetails extends AppCompatActivity {

    @BindView(R.id.ingredients_list)RecyclerView ingre_list;
    @BindView(R.id.steps_button)Button next_button;
    public static SecondScreenRecyclerViewAdapter myAdapter;
    public ArrayList<Ingredients> ing;
    public ArrayList<Steps> ste;
    public Boolean isTablet = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen_details);
        ButterKnife.bind(this);
        ing = getIntent().getParcelableArrayListExtra("Ingredients");
        ste = getIntent().getParcelableArrayListExtra("Steps");
        if(ing==null){
            setDisplay();
        }else{
            myAdapter = new SecondScreenRecyclerViewAdapter(ing);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            ingre_list.setLayoutManager(mLayoutManager);
            ingre_list.setItemAnimator(new DefaultItemAnimator());
            ingre_list.setAdapter(myAdapter);
            DisplayMetrics metrics = new DisplayMetrics();
            this.getWindowManager().getDefaultDisplay().getMetrics(metrics);
            float yInches= metrics.heightPixels/metrics.ydpi;
            float xInches= metrics.widthPixels/metrics.xdpi;
            double diagonalInches = Math.sqrt(xInches*xInches + yInches*yInches);
            if (diagonalInches>=6.5){
                // 6.5inch device or bigger
                isTablet = true;
            }else{
                // smaller device
                isTablet = false;
            }
        }



        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isTablet){
                    Intent i = new Intent(SecondScreenDetails.this, MasterDetailFlow.class);
                    i.putParcelableArrayListExtra("Steps", ste);
                    startActivity(i);
                }else {
                    Intent i = new Intent(SecondScreenDetails.this, StepsActivity.class);
                    i.putParcelableArrayListExtra("Steps", ste);
                    startActivity(i);
                }

            }
        });
    }

    public void setDisplay(){
        next_button.setVisibility(View.GONE);
        IRecipe irecipie = RetrofitBuilder.Retrieve();
        final Call<ArrayList<Recipe>> recipie = irecipie.getRecipe();
        recipie.enqueue(new Callback<ArrayList<Recipe>>() {
            @Override
            public void onResponse(Call<ArrayList<Recipe>> call, Response<ArrayList<Recipe>> response) {
                ArrayList<Recipe> r = response.body();
                ing = r.get(0).getIngredients();
                myAdapter = new SecondScreenRecyclerViewAdapter(ing);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                ingre_list.setLayoutManager(mLayoutManager);
                ingre_list.setItemAnimator(new DefaultItemAnimator());
                ingre_list.setAdapter(myAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Recipe>> call, Throwable t) {

            }
        });

    }

    //Saving the scroll position

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable("KeyForLayoutManagerState", ingre_list.getLayoutManager().onSaveInstanceState());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if(savedInstanceState != null)
        {
            Parcelable savedRecyclerLayoutState = savedInstanceState.getParcelable("KeyForLayoutManagerState");
            ingre_list.getLayoutManager().onRestoreInstanceState(savedRecyclerLayoutState);
        }
        super.onRestoreInstanceState(savedInstanceState);
    }

}
