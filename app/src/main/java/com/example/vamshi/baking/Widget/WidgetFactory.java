package com.example.vamshi.baking.Widget;

import android.content.Context;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

/**
 * Created by Vamshi on 8/13/2017.
 */

public class WidgetFactory implements RemoteViewsService.RemoteViewsFactory {

    public Context context;

    public WidgetFactory(Context m){
        this.context = m;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        // Runs everytime this class is created or notified to update the data
        // Run the retrofit data retrieval here

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        return null;
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
