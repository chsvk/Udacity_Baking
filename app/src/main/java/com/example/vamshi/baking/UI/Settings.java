package com.example.vamshi.baking.UI;

import android.appwidget.AppWidgetManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vamshi.baking.R;
import com.example.vamshi.baking.Widget.ListWidget;

public class Settings extends AppCompatActivity {

    private Button sortByButton;
    private CharSequence[] items = {"Nutella Pie", "Brownies", "Yellow Cake", "CheeseCake"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        sortByButton = (Button)findViewById(R.id.sortButton);

        sortByButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Settings.this);
                mBuilder.setTitle("Choose a Sorting Option").setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences sh = getSharedPreferences("Ingredients", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sh.edit();

                        switch (which){
                            case 0:
                                dialog.dismiss();
                                editor.putString("FAV", "0");
                                editor.apply();
                                sendBroadcast();
                                Toast.makeText(Settings.this, "Nutella Pie is Faved", Toast.LENGTH_SHORT).show();
                                break;
                            case 1:
                                dialog.dismiss();
                                editor.putString("FAV", "1");
                                editor.apply();
                                sendBroadcast();
                                Toast.makeText(Settings.this, "Brownies is Faved", Toast.LENGTH_SHORT).show();
                                break;
                            case 2:
                                dialog.dismiss();
                                editor.putString("FAV", "2");
                                editor.apply();
                                sendBroadcast();
                                Toast.makeText(Settings.this, "Yellow Cake is Faved", Toast.LENGTH_SHORT).show();
                                break;
                            case 3:
                                dialog.dismiss();
                                editor.putString("FAV", "3");
                                editor.apply();
                                sendBroadcast();
                                Toast.makeText(Settings.this, "CheeseCake is Faved", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        editor.apply();
                    }
                });
                mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });
    }

    public void sendBroadcast(){
        Intent intent = new Intent(Settings.this,ListWidget.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        int[] ids = {R.xml.list_widget_info};
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,ids);
        sendBroadcast(intent);
    }
}
