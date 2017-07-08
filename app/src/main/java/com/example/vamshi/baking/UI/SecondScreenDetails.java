package com.example.vamshi.baking.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.vamshi.baking.Adapters.SecondScreenRecyclerViewAdapter;
import com.example.vamshi.baking.Data.Ingredients;
import com.example.vamshi.baking.Data.Recipe;
import com.example.vamshi.baking.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
public class SecondScreenDetails extends AppCompatActivity {

//    public Realm realm;
    public List<Recipe> mRecipies;
    @BindView(R.id.ingredients_list)RecyclerView ingre_list;
    @BindView(R.id.steps_button)Button next_button;
    public int position;
    public static SecondScreenRecyclerViewAdapter myAdapter;
    public List<Ingredients> tIngre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen_details);
//        Realm.init(this);
        ButterKnife.bind(this);
//        realm = Realm.getDefaultInstance();
        mRecipies = new ArrayList<>();
        setDisplay();


        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void setDisplay() {

        tIngre = mRecipies.get(position).getIngredients();
        Toast.makeText(this, tIngre.get(0).getIngredient().toString(), Toast.LENGTH_SHORT).show();
        myAdapter = new SecondScreenRecyclerViewAdapter(tIngre);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        ingre_list.setLayoutManager(mLayoutManager);
        ingre_list.setItemAnimator(new DefaultItemAnimator());
        ingre_list.setAdapter(myAdapter);

    }
}
