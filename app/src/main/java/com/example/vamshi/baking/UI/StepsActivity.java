package com.example.vamshi.baking.UI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.vamshi.baking.Adapters.StepsRecyclerAdapter;
import com.example.vamshi.baking.Data.Steps;
import com.example.vamshi.baking.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepsActivity extends AppCompatActivity {

    @BindView(R.id.steps_head)TextView steps_head;
    @BindView(R.id.steps_list)RecyclerView steps_list;
    public StepsRecyclerAdapter myAdapter;
    public ArrayList<Steps> steps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);
        ButterKnife.bind(this);
        steps = getIntent().getParcelableArrayListExtra("Steps");
        myAdapter = new StepsRecyclerAdapter(steps, StepsActivity.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        steps_list.setLayoutManager(mLayoutManager);
        steps_list.setItemAnimator(new DefaultItemAnimator());
        steps_list.setAdapter(myAdapter);
    }
}
