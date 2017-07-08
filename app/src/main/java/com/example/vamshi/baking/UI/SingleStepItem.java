package com.example.vamshi.baking.UI;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.vamshi.baking.R;

import butterknife.BindView;

/**
 * Created by Vamshi on 7/9/2017.
 */

public class SingleStepItem extends AppCompatActivity {

    @BindView(R.id.video_player)VideoView myPLayer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_step);
        Intent in = getIntent();
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(myPLayer);
        Uri u = Uri.parse((in.getStringExtra("video")).trim());
        myPLayer.setMediaController(mediaController);
        myPLayer.setVideoURI(u);
        myPLayer.start();
        Toast.makeText(this, String.valueOf( in.getStringExtra("id")), Toast.LENGTH_SHORT).show();
    }
}
