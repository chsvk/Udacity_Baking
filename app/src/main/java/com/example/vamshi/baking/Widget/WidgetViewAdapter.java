package com.example.vamshi.baking.Widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import android.widget.Toast;

import com.example.vamshi.baking.Data.*;
import com.example.vamshi.baking.Data.Ingredients;
import com.example.vamshi.baking.R;
import com.example.vamshi.baking.Retrofit.IRecipe;
import com.example.vamshi.baking.Retrofit.RetrofitBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Vamshi on 7/27/2017.
 */

public class WidgetViewAdapter implements RemoteViewsService.RemoteViewsFactory {

    Context context;
    ArrayList<Recipe> r;
    ArrayList<com.example.vamshi.baking.Data.Ingredients> i;
    private int appWidgetID;
    ArrayList<String> iList;

    public WidgetViewAdapter(Context c, Intent in){
        this.context = c;
        appWidgetID = in.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);
        populate();
    }

    private void populate() {
        for(int j = 0; j<=i.size(); j++){
            iList.add(i.get(0).getIngredient());
        }
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {

        IRecipe irecipie = RetrofitBuilder.Retrieve();
        final Call<ArrayList<Recipe>> recipie = irecipie.getRecipe();
        recipie.enqueue(new Callback<ArrayList<Recipe>>() {
            @Override
            public void onResponse(Call<ArrayList<Recipe>> call, Response<ArrayList<Recipe>> response) {
                r = response.body();
                i = r.get(0).getIngredients();
            }

            @Override
            public void onFailure(Call<ArrayList<Recipe>> call, Throwable t) {
                Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return iList.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        final RemoteViews remoteView = new RemoteViews(context.getPackageName(), R.layout.row);
        Ingredients in = i.get(position);
        remoteView.setTextViewText(R.id.text_for_widget, in.getIngredient());
        return remoteView;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
