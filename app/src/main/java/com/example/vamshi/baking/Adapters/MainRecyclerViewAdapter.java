package com.example.vamshi.baking.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.vamshi.baking.Data.Ingredients;
import com.example.vamshi.baking.Data.Recipe;
import com.example.vamshi.baking.Data.Steps;
import com.example.vamshi.baking.R;
import com.example.vamshi.baking.UI.MainActivity;
import com.example.vamshi.baking.UI.SecondScreenDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vamshi on 7/18/2017.
 */

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder>{

    ArrayList<Recipe> mRecipies = new ArrayList<>();
    Context context;


    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView thumbnail;
        public TextView titleText;
        public TextView servingsText;

        public ViewHolder(View itemView) {
            super(itemView);
            thumbnail = (ImageView)itemView.findViewById(R.id.title_image);
            titleText = (TextView)itemView.findViewById(R.id.title_text);
            servingsText = (TextView)itemView.findViewById(R.id.servings_text);
        }
    }

    public MainRecyclerViewAdapter(ArrayList<Recipe> mRecipies, Context context){
        this.mRecipies = mRecipies;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item,parent, false);
        return  new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        Recipe r = mRecipies.get(position);
        holder.titleText.setText(r.getName());
        holder.servingsText.setText("Servings:- " + r.getServings());
        if(r.getImage().isEmpty()){
            holder.thumbnail.setImageResource(R.drawable.temp);
        }else{
            Glide.with(context).load(r.getImage().trim()).into(holder.thumbnail);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<Ingredients> i = mRecipies.get(position).getIngredients();
                ArrayList<Steps> s = mRecipies.get(position).getSteps();
                Intent in = new Intent(context, SecondScreenDetails.class);
                in.putParcelableArrayListExtra("Ingredients", i);
                in.putParcelableArrayListExtra("Steps", s);
                context.startActivity(in);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mRecipies.size();
    }

}
