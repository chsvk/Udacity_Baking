package com.example.vamshi.baking.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vamshi.baking.Data.Steps;
import com.example.vamshi.baking.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vamshi on 7/9/2017.
 */

public class StepsRecyclerAdapter extends RecyclerView.Adapter<StepsRecyclerAdapter.MyViewHolder> {


    List<Steps> mSteps = new ArrayList<>();

    public StepsRecyclerAdapter(List<Steps> m){
        this.mSteps = m;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_step_item,parent, false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.step_number.setText(String.valueOf(Integer.parseInt(mSteps.get(position).getId()) + 1));
        holder.step_short_description.setText(mSteps.get(position).getShortDescription());
    }

    @Override
    public int getItemCount() {
        return mSteps.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView step_number;
        TextView step_short_description;

        public MyViewHolder(View itemView) {
            super(itemView);
            step_number = (TextView)itemView.findViewById(R.id.step_number);
            step_short_description = (TextView)itemView.findViewById(R.id.step_short_description);

        }
    }



}
