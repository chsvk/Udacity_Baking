package com.example.vamshi.baking.Widget.Service;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.widget.RemoteViewsService;

import com.example.vamshi.baking.Widget.WidgetViewAdapter;

/**
 * Created by Vamshi on 7/27/2017.
 */

public class WidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        int appWidgetID = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);
        return (new WidgetViewAdapter(this.getApplicationContext(), intent));
    }
}
