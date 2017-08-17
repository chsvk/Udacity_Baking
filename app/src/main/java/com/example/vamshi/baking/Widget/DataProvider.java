package com.example.vamshi.baking.Widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.vamshi.baking.Data.Ingredients;
import com.example.vamshi.baking.Data.Recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vamshi on 8/17/2017.
 */

@SuppressLint("NewApi")
public class DataProvider implements RemoteViewsService.RemoteViewsFactory {

    List mCollections = new ArrayList();

    Context mContext = null;

    ArrayList<Recipe> r;
    ArrayList<Ingredients> i;

    public  DataProvider(Context context, Intent intent){
        this.mContext = context;
    }

    @Override
    public void onCreate() {
        getData();
    }

    private void getData() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("Ingredients", Context.MODE_PRIVATE);
        for (int b=0; b<7; b++){
            mCollections.add(sharedPreferences.getString("Ingredient"+ (String.valueOf(b)), ""));
        }
    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return mCollections.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews mView = new RemoteViews(mContext.getPackageName(),
                android.R.layout.simple_list_item_1);
        mView.setTextViewText(android.R.id.text1, (CharSequence) mCollections.get(position));

        mView.setTextColor(android.R.id.text1, Color.BLACK);
        return mView;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
