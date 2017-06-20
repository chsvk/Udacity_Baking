package com.example.vamshi.baking.Adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.vamshi.baking.Data.Recipe;
import com.example.vamshi.baking.R;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Vamshi on 6/20/2017.
 */

public class ListViewAdapter extends ArrayAdapter<Recipe> implements View.OnClickListener {


    public ListViewAdapter(@NonNull Context context, @NonNull List<Recipe> objects) {
        super(context, R.layout.single_item, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.single_item, parent, false);
        String title =  getItem(position).getName();
        TextView title_text = (TextView)customView.findViewById(R.id.title_text);
        title_text.setText(title);

        return customView;
    }

    @Override
    public void onClick(View v) {

    }
}
