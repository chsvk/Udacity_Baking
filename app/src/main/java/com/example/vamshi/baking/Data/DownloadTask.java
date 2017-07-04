package com.example.vamshi.baking.Data;

import android.os.AsyncTask;
import android.util.Log;

import com.example.vamshi.baking.UI.MainActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import okhttp3.OkHttpClient;
import okhttp3.Request;



/**
 * Created by Vamshi on 6/20/2017.
 */

public class DownloadTask extends AsyncTask<String,Void,String> {


    private RealmList<Recipe> realmRecipe = new RealmList<>();
    String result;

    @Override
    protected String doInBackground(String... params) {
        result = "";
        Realm realm = null;
            realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url("https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json").build();
                    try {
                        result = client.newCall(request).execute().body().string();
                        Log.i("RESULT", result);
                        JSONArray rootArray = new JSONArray(result);
                        for (int i = 0; i < rootArray.length(); i++) {
                            JSONObject tempObject = rootArray.getJSONObject(i);
                            JSONArray jIngredients = tempObject.getJSONArray("ingredients");
                            JSONArray jSteps = tempObject.getJSONArray("steps");

                            // Get the ingredients
                            List<Ingredients> ingredients = new ArrayList<>();
                            for (int j = 0; j < jIngredients.length(); j++) {
                                JSONObject tempIngredient = jIngredients.getJSONObject(j);
                                Ingredients nIngredient = realm.createObject(Ingredients.class);
                                nIngredient.setIngredient(tempIngredient.getString("ingredient"));
                                nIngredient.setMeasure(tempIngredient.getString("measure"));
                                nIngredient.setQuantity(tempIngredient.getString("quantity"));
//                                Ingredients newIngredient = new Ingredients(tempIngredient.getString("quantity"),
//                                        tempIngredient.getString("measure"),
//                                        tempIngredient.getString("ingredient"));
//                                ingredients.add(newIngredient);
                                ingredients.add(nIngredient);
                            }

                            // Get the steps
                            List<Steps> steps = new ArrayList<>();
                            for (int j = 0; j < jSteps.length(); j++) {
                                JSONObject tempStep = jSteps.getJSONObject(j);
                                Steps nStep = realm.createObject(Steps.class);
                                nStep.setDescription(tempStep.getString("description"));
                                nStep.setId(tempStep.getString("id"));
                                nStep.setShortDescription(tempStep.getString("shortDescription"));
                                nStep.setVideoURL(tempStep.getString("videoURL"));
                                steps.add(nStep);
//                                Steps newStep = new Steps(tempStep.getString("id"), tempStep.getString("shortDescription"),
//                                        tempStep.getString("description"), tempStep.getString("videoURL"));
//                                steps.add(newStep);
                            }

                            // Create the recipe

                            Recipe nRecipe = realm.createObject(Recipe.class);
                            nRecipe.setId(tempObject.getString("id"));
                            nRecipe.setName(tempObject.getString("name"));
                            nRecipe.setServings(tempObject.getString("servings"));
                            nRecipe.setIngredients(ingredients);
                            nRecipe.setSteps(steps);
                            realmRecipe.add(nRecipe);
//                            Recipe newRecipe = new Recipe(tempObject.getString("id"), tempObject.getString("name"), tempObject.getString("servings"), ingredients, steps);
//                            MainActivity.mRecipies.add(newRecipe);

                        }
                    }catch (Exception e){
                        Log.i("Error Message", e.getMessage());
                    }

                }
            });


        return null;
    }

    @Override
    protected void onPostExecute(String s) {

            super.onPostExecute(s);
        }
    }

