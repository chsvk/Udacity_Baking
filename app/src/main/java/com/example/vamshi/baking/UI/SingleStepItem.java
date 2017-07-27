package com.example.vamshi.baking.UI;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.devbrackets.android.exomedia.listener.OnPreparedListener;
import com.devbrackets.android.exomedia.ui.widget.VideoView;
import com.example.vamshi.baking.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Vamshi on 7/9/2017.
 */

public class SingleStepItem extends AppCompatActivity implements OnPreparedListener {

    @BindView(R.id.video_player)VideoView myVideoPlayer;
    @BindView(R.id.stepHeading)TextView stepHeading;
    @BindView(R.id.short_description)TextView shortDescription;
    @BindView(R.id.long_description)TextView longDescription;
    public Boolean landScape = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_step);
        ButterKnife.bind(this);
        myVideoPlayer.setOnPreparedListener(this);
        Intent in = getIntent();
        checkOrientation();
        stepHeading.setText("Step: " + in.getStringExtra("id"));
        shortDescription.setText(in.getStringExtra("short"));
        longDescription.setText(in.getStringExtra("long"));
        if (in.getStringExtra("video").trim().isEmpty()) {
            checkOrientation();
            Toast.makeText(this, "Step Contains No Video", Toast.LENGTH_SHORT).show();
            myVideoPlayer.setVisibility(View.GONE);
        } else {
            checkOrientation();
            if(landScape) {
                stepHeading.setVisibility(View.GONE);
                shortDescription.setVisibility(View.GONE);
                longDescription.setVisibility(View.GONE);
                myVideoPlayer.setVisibility(View.VISIBLE);
                myVideoPlayer.getLayoutParams().height = 4000;
                myVideoPlayer.requestLayout();
                myVideoPlayer.findFocus();
                myVideoPlayer.setVideoURI(Uri.parse(in.getStringExtra("video").trim()));
                myVideoPlayer.getVideoControls();
                myVideoPlayer.showControls();
            }
            if(!landScape) {
                myVideoPlayer.setVideoURI(Uri.parse(in.getStringExtra("video").trim()));
                stepHeading.setVisibility(View.VISIBLE);
                shortDescription.setVisibility(View.VISIBLE);
                longDescription.setVisibility(View.VISIBLE);
            }

        }

        }

    private void checkOrientation() {
        if(isInLandscapeMode(this)){
            landScape = true;
        }
        if(!isInLandscapeMode(this)){
            landScape = false;
        }
    }


    @Override
    protected void onStop() {
        myVideoPlayer.stopPlayback();
        myVideoPlayer.release();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        myVideoPlayer.stopPlayback();
        myVideoPlayer.release();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        myVideoPlayer.stopPlayback();
        myVideoPlayer.release();
        super.onPause();
    }

    public boolean isInLandscapeMode(Context context ) {
        return (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE);
    }

    @Override
    public void onPrepared() {
        myVideoPlayer.start();
    }
}
