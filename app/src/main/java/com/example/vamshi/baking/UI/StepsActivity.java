package com.example.vamshi.baking.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vamshi.baking.Adapters.SecondScreenRecyclerViewAdapter;
import com.example.vamshi.baking.Adapters.StepsRecyclerAdapter;
import com.example.vamshi.baking.Data.Recipe;
import com.example.vamshi.baking.Data.Steps;
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

public class StepsActivity extends AppCompatActivity {

    @BindView(R.id.steps_head)TextView steps_head;
    @BindView(R.id.steps_list)RecyclerView steps_list;
    public StepsRecyclerAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);
        ButterKnife.bind(this);
        Intent in = getIntent();
        final int pos = Integer.parseInt(in.getStringExtra("position"));
        IRecipe iRecipe = RetrofitBuilder.Retrieve();
        Call<ArrayList<Recipe>> r = iRecipe.getRecipe();
        r.enqueue(new Callback<ArrayList<Recipe>>() {
            @Override
            public void onResponse(Call<ArrayList<Recipe>> call, Response<ArrayList<Recipe>> response) {
                List<Recipe> rr = response.body();
                List<Steps> steps = rr.get(pos).getSteps();
                myAdapter = new StepsRecyclerAdapter(steps, StepsActivity.this);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                steps_list.setLayoutManager(mLayoutManager);
                steps_list.setItemAnimator(new DefaultItemAnimator());
                steps_list.setAdapter(myAdapter);

            }

            @Override
            public void onFailure(Call<ArrayList<Recipe>> call, Throwable t) {

            }
        });

    }
}
