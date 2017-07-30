package com.example.vamshi.baking.Adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.devbrackets.android.exomedia.listener.OnPreparedListener;
import com.example.vamshi.baking.Data.Steps;
import com.example.vamshi.baking.R;
import com.example.vamshi.baking.UI.MasterDetailFlow;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vamshi on 7/23/2017.
 */

public class MasterDetailFlowAdapter extends RecyclerView.Adapter<MasterDetailFlowAdapter.MyViewHolder>  {

    List<Steps> mSteps = new ArrayList<>();
    public Context context;

    public MasterDetailFlowAdapter(List<Steps> m, Context context){
        this.mSteps = m;
        this.context = context;
    }

    @Override
    public MasterDetailFlowAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_master_detail_flow_item,parent, false);
        return new MasterDetailFlowAdapter.MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(final MasterDetailFlowAdapter.MyViewHolder holder, final int position) {
        holder.step_number.setText(String.valueOf(Integer.parseInt(mSteps.get(position).getId()) + 1));
        MasterDetailFlow.myPlayer.setVisibility(View.GONE);
        holder.step_short_description.setText(mSteps.get(position).getShortDescription());
        if(mSteps.get(position).getThumbnailURL().isEmpty()){
            holder.thumbnail.setVisibility(View.GONE);
        }else {
            Glide.with(context).load(mSteps.get(position).getThumbnailURL().trim()).into(holder.thumbnail);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MasterDetailFlow.stepHeading.setText("Step:- " + String.valueOf(Integer.parseInt(mSteps.get(position).getId()) + 1));
                MasterDetailFlow.shortDescription.setText(mSteps.get(position).getShortDescription());
                MasterDetailFlow.longDescription.setText(mSteps.get(position).getDescription());
                if (mSteps.get(position).getVideoURL().trim().isEmpty()) {
                    Toast.makeText(context, "Step Contains No Video", Toast.LENGTH_SHORT).show();
                    MasterDetailFlow.myPlayer.setVisibility(View.GONE);
                } else {
                    MasterDetailFlow.myPlayer.setVisibility(View.VISIBLE);
                    MasterDetailFlow.myPlayer.setVideoURI(Uri.parse(mSteps.get(position).getVideoURL().trim()));
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return mSteps.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView step_number;
        TextView step_short_description;
        ImageView thumbnail;

        public MyViewHolder(View itemView) {
            super(itemView);
            step_number = (TextView)itemView.findViewById(R.id.step_number);
            step_short_description = (TextView)itemView.findViewById(R.id.step_short_description);
            thumbnail = (ImageView)itemView.findViewById(R.id.thumbnail);

        }
    }
}
