package com.example.vamshi.baking.UI;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Vamshi on 7/9/2017.
 */

public class SingleStepItem extends AppCompatActivity {

    @BindView(R.id.video_player)SimpleExoPlayerView myExoPlayer;
    public SimpleExoPlayer myPlayer;
    @BindView(R.id.stepHeading)TextView stepHeading;
    @BindView(R.id.short_description)TextView shortDescription;
    @BindView(R.id.long_description)TextView longDescription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_step);
        ButterKnife.bind(this);
        Intent in = getIntent();
        stepHeading.setText("Step: " + in.getStringExtra("id"));
        shortDescription.setText(in.getStringExtra("short"));
        longDescription.setText(in.getStringExtra("long"));
        if (in.getStringExtra("video").trim().isEmpty()) {
            Toast.makeText(this, "Step Contains No Video", Toast.LENGTH_SHORT).show();
        } else {
            if(isInLandscapeMode(this)) {
                initializePlayer(Uri.parse(in.getStringExtra("video").trim()));
                stepHeading.setVisibility(View.GONE);
                shortDescription.setVisibility(View.GONE);
                longDescription.setVisibility(View.GONE);
            }
            if(!isInLandscapeMode(this)) {
                initializePlayer(Uri.parse(in.getStringExtra("video").trim()));
                stepHeading.setVisibility(View.VISIBLE);
                shortDescription.setVisibility(View.VISIBLE);
                longDescription.setVisibility(View.VISIBLE);
            }

        }

        }


    private void initializePlayer(Uri video) {

        if(myExoPlayer == null){
            TrackSelector tSelector = new DefaultTrackSelector();
            myPlayer = ExoPlayerFactory.newSimpleInstance(this, tSelector);
            myExoPlayer.setPlayer(myPlayer);
            String userAgent = Util.getUserAgent(this, "Baking");
            MediaSource mSource = new ExtractorMediaSource(video, new DefaultDataSourceFactory(this, userAgent), new DefaultExtractorsFactory(), null, null);
            myPlayer.prepare(mSource);
            myPlayer.setPlayWhenReady(true);
        }

    }

    private void releasePlayer(){
        myPlayer.stop();
        myPlayer.release();
        myPlayer = null;

    }


    @Override
    protected void onStop() {
        if(myPlayer!=null) {
            releasePlayer();
        }
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if(myPlayer!=null) {
            releasePlayer();
        }
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        if(myPlayer!=null) {
            releasePlayer();
        }
        super.onPause();
    }

    public boolean isInLandscapeMode(Context context ) {
        return (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE);
    }
}
