package com.example.vamshi.baking.UI;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.vamshi.baking.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Vamshi on 7/9/2017.
 */

public class SingleStepItem extends AppCompatActivity {

    @BindView(R.id.video_player)VideoView myPLayer;
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
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(myPLayer);
        if(in.getStringExtra("video").trim().isEmpty()){
            myPLayer.setVisibility(View.GONE);
            Toast.makeText(this, "No Video Available", Toast.LENGTH_SHORT).show();
        }else{
            Uri u = Uri.parse((in.getStringExtra("video")).trim());
            myPLayer.setMediaController(mediaController);
            myPLayer.setVideoURI(u);
            myPLayer.start();
        }
    }
}
