package com.example.vamshi.baking.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.vamshi.baking.Adapters.SecondScreenRecyclerViewAdapter;
import com.example.vamshi.baking.Data.Ingredients;
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


public class SecondScreenDetails extends AppCompatActivity {

    @BindView(R.id.ingredients_list)RecyclerView ingre_list;
    @BindView(R.id.steps_button)Button next_button;
    public static SecondScreenRecyclerViewAdapter myAdapter;
    public ArrayList<Ingredients> ing;
    public ArrayList<Steps> ste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen_details);
        ButterKnife.bind(this);
        ing = getIntent().getParcelableArrayListExtra("Ingredients");
        ste = getIntent().getParcelableArrayListExtra("Steps");
        setDisplay();


        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = getIntent();
                Intent i = new Intent(SecondScreenDetails.this, StepsActivity.class);
//                i.putExtra("position", in.getStringExtra("Position"));
                i.putParcelableArrayListExtra("Steps", ste);
                startActivity(i);
            }
        });
    }

    private void setDisplay() {

        myAdapter = new SecondScreenRecyclerViewAdapter(ing);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        ingre_list.setLayoutManager(mLayoutManager);
        ingre_list.setItemAnimator(new DefaultItemAnimator());
        ingre_list.setAdapter(myAdapter);

//        Intent in = getIntent();
//        final int a = Integer.parseInt(in.getStringExtra("Position"));
//        IRecipe irecipie = RetrofitBuilder.Retrieve();
//        final Call<ArrayList<Recipe>> recipie = irecipie.getRecipe();
//        recipie.enqueue(new Callback<ArrayList<Recipe>>() {
//            @Override
//            public void onResponse(Call<ArrayList<Recipe>> call, Response<ArrayList<Recipe>> response) {
//                ArrayList<Recipe> r = response.body();
//                List<Ingredients> i = r.get(a).getIngredients();
//                ArrayList<Ingredients> i = getIntent().getParcelableArrayListExtra("Ingredients");
//                myAdapter = new SecondScreenRecyclerViewAdapter(i);
//                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//                ingre_list.setLayoutManager(mLayoutManager);
//                ingre_list.setItemAnimator(new DefaultItemAnimator());
//                ingre_list.setAdapter(myAdapter);
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<Recipe>> call, Throwable t) {
//
//            }
//        });
//
//
    }
}
