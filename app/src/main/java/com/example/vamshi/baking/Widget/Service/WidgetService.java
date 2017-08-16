package com.example.vamshi.baking.Widget.Service;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.widget.RemoteViewsService;

import com.example.vamshi.baking.Widget.WidgetViewAdapter;

import java.util.ArrayList;

/**
 * Created by Vamshi on 7/27/2017.
 */

public class WidgetService extends IntentService {

    public static String FROM_ACTIVITY_INGREDIENTS_LIST ="FROM_ACTIVITY_INGREDIENTS_LIST";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @ Used to name the worker thread, important only for debugging.
     */
    public WidgetService() {
        super("WidgetService");
    }

    public static void startBakingService(Context context, ArrayList<String> fromActivityIngredientsList) {
        Intent intent = new Intent(context, WidgetService.class);
        intent.putExtra(FROM_ACTIVITY_INGREDIENTS_LIST,fromActivityIngredientsList);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            ArrayList<String> fromActivityIngredientsList = intent.getExtras().getStringArrayList(FROM_ACTIVITY_INGREDIENTS_LIST);
            handleActionUpdateBakingWidgets(fromActivityIngredientsList );

        }
    }
    private void handleActionUpdateBakingWidgets(ArrayList<String> fromActivityIngredientsList) {
        Intent intent = new Intent("android.appwidget.action.APPWIDGET_UPDATE2");
        intent.setAction("android.appwidget.action.APPWIDGET_UPDATE2");
        intent.putExtra(FROM_ACTIVITY_INGREDIENTS_LIST,fromActivityIngredientsList);
        sendBroadcast(intent);
    }
}
