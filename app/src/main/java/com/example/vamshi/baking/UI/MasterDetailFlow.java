package com.example.vamshi.baking.UI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.devbrackets.android.exomedia.listener.OnPreparedListener;
import com.devbrackets.android.exomedia.ui.widget.VideoView;
import com.example.vamshi.baking.Adapters.MasterDetailFlowAdapter;
import com.example.vamshi.baking.Data.Steps;
import com.example.vamshi.baking.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MasterDetailFlow extends AppCompatActivity implements OnPreparedListener {

    @BindView(R.id.steps_head)TextView steps_head;
    @BindView(R.id.steps_list)RecyclerView steps_list;
    public static VideoView myPlayer;
    public MasterDetailFlowAdapter myAdapter;
    public ArrayList<Steps> steps;
    public static TextView stepHeading;
    public static TextView shortDescription;
    public static TextView longDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_detail_flow);
        ButterKnife.bind(this);
        myPlayer = (VideoView)findViewById(R.id.video_player);
        myPlayer.setOnPreparedListener(this);
        stepHeading = (TextView)findViewById(R.id.stepHeading);
        shortDescription = (TextView)findViewById(R.id.short_description);
        longDescription = (TextView)findViewById(R.id.long_description);
        steps = getIntent().getParcelableArrayListExtra("Steps");
        myAdapter = new MasterDetailFlowAdapter(steps, MasterDetailFlow.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        steps_list.setLayoutManager(mLayoutManager);
        steps_list.setItemAnimator(new DefaultItemAnimator());
        steps_list.setAdapter(myAdapter);
    }




    @Override
    protected void onStop() {
      myPlayer.stopPlayback();
        myPlayer.release();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        myPlayer.stopPlayback();
        myPlayer.release();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
       myPlayer.stopPlayback();
        myPlayer.release();
        super.onPause();
    }

    @Override
    public void onPrepared() {
        myPlayer.start();
    }
}
