package com.example.vamshi.baking.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vamshi.baking.Data.Ingredients;
import com.example.vamshi.baking.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vamshi on 6/21/2017.
 */

public class SecondScreenRecyclerViewAdapter extends RecyclerView.Adapter<SecondScreenRecyclerViewAdapter.MyViewHolder> {

    private List<Ingredients> rIngredients = new ArrayList<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView ingredient_name;
        public TextView quantity;

        public MyViewHolder(View view) {
            super(view);
            ingredient_name = (TextView)view.findViewById(R.id.ingredient_name);
            quantity = (TextView)view.findViewById(R.id.quantity);
        }
    }

    public SecondScreenRecyclerViewAdapter(List<Ingredients> mIngredients){
        this.rIngredients = mIngredients;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item_ingredient,parent, false);
        return  new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SecondScreenRecyclerViewAdapter.MyViewHolder holder, int position) {

        Ingredients i = rIngredients.get(position);
        holder.ingredient_name.setText(i.getIngredient());
        String q = i.getQuantity() + i.getMeasure() + "(s)";
        holder.quantity.setText(q);

    }


    @Override
    public int getItemCount() {
        return rIngredients.size();
    }
}
