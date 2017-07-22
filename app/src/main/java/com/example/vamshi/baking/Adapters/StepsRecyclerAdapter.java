package com.example.vamshi.baking.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.vamshi.baking.Data.Steps;
import com.example.vamshi.baking.R;
import com.example.vamshi.baking.UI.SingleStepItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vamshi on 7/9/2017.
 */

public class StepsRecyclerAdapter extends RecyclerView.Adapter<StepsRecyclerAdapter.MyViewHolder> {


    List<Steps> mSteps = new ArrayList<>();
    public Context context;

    public StepsRecyclerAdapter(List<Steps> m, Context context){
        this.mSteps = m;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_step_item,parent, false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.step_number.setText(String.valueOf(Integer.parseInt(mSteps.get(position).getId()) + 1));
        holder.step_short_description.setText(mSteps.get(position).getShortDescription());
        if(mSteps.get(position).getThumbnailURL().isEmpty()){
            holder.thumbnail.setVisibility(View.GONE);
        }else {
            Glide.with(context).load(mSteps.get(position).getThumbnailURL().trim()).into(holder.thumbnail);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(context, SingleStepItem.class);
                in.putExtra("id",String.valueOf(Integer.parseInt(mSteps.get(position).getId()) + 1));
                in.putExtra("short",mSteps.get(position).getShortDescription());
                in.putExtra("long", mSteps.get(position).getDescription());
                in.putExtra("video",mSteps.get(position).getVideoURL());
                context.startActivity(in);
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
