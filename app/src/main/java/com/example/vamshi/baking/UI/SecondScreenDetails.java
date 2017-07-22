package com.example.vamshi.baking.UI;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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
        myAdapter = new SecondScreenRecyclerViewAdapter(ing);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        ingre_list.setLayoutManager(mLayoutManager);
        ingre_list.setItemAnimator(new DefaultItemAnimator());
        ingre_list.setAdapter(myAdapter);



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
