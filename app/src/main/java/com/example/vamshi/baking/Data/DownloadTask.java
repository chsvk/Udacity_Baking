package com.example.vamshi.baking.Data;

import android.bluetooth.le.ScanRecord;
import android.os.AsyncTask;
import android.util.Log;

import com.example.vamshi.baking.UI.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.jar.Manifest;

import okhttp3.OkHttpClient;
import okhttp3.Request;



/**
 * Created by Vamshi on 6/20/2017.
 */

public class DownloadTask extends AsyncTask<String,Void,String> {

    String result;
    @Override
    protected String doInBackground(String... params) {

        result = "";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json").build();
        try {
            result = client.newCall(request).execute().body().string();
            JSONArray rootArray = new JSONArray(result);
            Log.i("LENGHT", String.valueOf(rootArray.length()));
            for (int i = 0; i < rootArray.length(); i++) {

                JSONObject tempObject = rootArray.getJSONObject(i);
                JSONArray ingredients = tempObject.getJSONArray("ingredients");
                for (int j = 0; j < ingredients.length(); j++) {
                    JSONObject tempIngredient = ingredients.getJSONObject(j);
                    Ingredients newIngredient = new Ingredients(tempIngredient.getString("quantity"),
                            tempIngredient.getString("measure"),
                            tempIngredient.getString("ingredient"));
                    MainActivity.mIngredients.add(newIngredient);
                }
                JSONArray steps = tempObject.getJSONArray("steps");
                for (int k = 0; k < steps.length(); k++) {
                    JSONObject tempStep = steps.getJSONObject(k);
                    Steps newStep = new Steps(tempStep.getString("id"), tempStep.getString("shortDescription"),
                            tempStep.getString("description"), tempStep.getString("videoURL"));
                    MainActivity.mSteps.add(newStep);
                }
                Recipe newRecipe = new Recipe(tempObject.getString("id"), tempObject.getString("name"), tempObject.getString("servings"));
                MainActivity.mRecipies.add(newRecipe);
                Log.i("SIZE", String.valueOf(MainActivity.mRecipies.size()));
            }

        } catch (Exception e) {
            Log.i("TAG", e.getMessage());
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {

        MainActivity.myList.setAdapter(MainActivity.myAdapter);
        super.onPostExecute(s);
    }
}
