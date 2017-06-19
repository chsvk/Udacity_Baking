package com.example.vamshi.baking.UI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vamshi.baking.Data.DownloadTask;
import com.example.vamshi.baking.Data.Ingredients;
import com.example.vamshi.baking.Data.Recipe;
import com.example.vamshi.baking.Data.Steps;
import com.example.vamshi.baking.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {

    public static List<Recipe> mRecipies;
    public static List<Ingredients> mIngredients;
    public static List<Steps> mSteps;
    public String TAG = "Error Message";
    private String url = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
    public static TextView text;


    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecipies = new ArrayList<>();
        mIngredients = new ArrayList<>();
        mSteps = new ArrayList<>();
        text = (TextView)findViewById(R.id.text);
//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder().url(url).build();
//        getData(client, request);
        DownloadTask n = new DownloadTask();
        n.execute(url);





    }

//    private void getData(OkHttpClient client, Request request) {
//
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.i(TAG, e.getMessage());
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                try {
//                    JSONArray rootArray = new JSONArray(response.body().string());
//                    Log.i("LENGHT", String.valueOf(rootArray.length()));
//                    for (int i = 0; i < rootArray.length(); i++) {
//
//                        JSONObject tempObject = rootArray.getJSONObject(i);
//                        JSONArray ingredients = tempObject.getJSONArray("ingredients");
//                        for (int j = 0; j < ingredients.length(); j++) {
//                            JSONObject tempIngredient = ingredients.getJSONObject(j);
//                            Ingredients newIngredient = new Ingredients(tempIngredient.getString("quantity"),
//                                    tempIngredient.getString("measure"),
//                                    tempIngredient.getString("ingredient"));
//                            mIngredients.add(newIngredient);
//                        }
//                        JSONArray steps = tempObject.getJSONArray("steps");
//                        for (int k = 0; k < steps.length(); k++) {
//                            JSONObject tempStep = steps.getJSONObject(k);
//                            Steps newStep = new Steps(tempStep.getString("id"), tempStep.getString("shortDescription"),
//                                    tempStep.getString("description"), tempStep.getString("videoURL"));
//                            mSteps.add(newStep);
//                        }
//                        Recipe newRecipe = new Recipe(tempObject.getString("id"), tempObject.getString("name"), tempObject.getString("servings"));
//                        mRecipies.add(newRecipe);
//                        Log.i("SIZE", String.valueOf(mRecipies.size()));
//                    }
//
//                } catch (JSONException e) {
//                    Log.i("TAG", e.getMessage());
//                }
//            }
//        });
//
//    }
}
