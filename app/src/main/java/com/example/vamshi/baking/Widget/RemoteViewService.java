package com.example.vamshi.baking.Widget;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by Vamshi on 8/17/2017.
 */
@SuppressLint("NewApi")
public class RemoteViewService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        DataProvider dataProvider = new DataProvider(
                getApplicationContext(), intent);
        return dataProvider;
    }
}

