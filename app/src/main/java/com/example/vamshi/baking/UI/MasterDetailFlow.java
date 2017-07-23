package com.example.vamshi.baking.UI;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.vamshi.baking.Adapters.MasterDetailFlowAdapter;
import com.example.vamshi.baking.Adapters.StepsRecyclerAdapter;
import com.example.vamshi.baking.Data.Steps;
import com.example.vamshi.baking.R;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MasterDetailFlow extends AppCompatActivity {

    @BindView(R.id.steps_head)TextView steps_head;
    @BindView(R.id.steps_list)RecyclerView steps_list;
    public MasterDetailFlowAdapter myAdapter;
    public ArrayList<Steps> steps;
    public static SimpleExoPlayerView myExoPlayer;
    public static SimpleExoPlayer myPlayer;
    public static TextView stepHeading;
    public static TextView shortDescription;
    public static TextView longDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_detail_flow);
        ButterKnife.bind(this);
        myExoPlayer = (SimpleExoPlayerView)findViewById(R.id.video_player);
        stepHeading = (TextView)findViewById(R.id.stepHeading);
        shortDescription = (TextView)findViewById(R.id.short_description);
        longDescription = (TextView)findViewById(R.id.long_description);
        steps = getIntent().getParcelableArrayListExtra("Steps");
        TrackSelector tSelector = new DefaultTrackSelector();
        myPlayer = ExoPlayerFactory.newSimpleInstance(this, tSelector);
        myExoPlayer.setPlayer(myPlayer);
        myAdapter = new MasterDetailFlowAdapter(steps, MasterDetailFlow.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        steps_list.setLayoutManager(mLayoutManager);
        steps_list.setItemAnimator(new DefaultItemAnimator());
        steps_list.setAdapter(myAdapter);
    }
}
