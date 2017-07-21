package com.example.vamshi.baking.Data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vamshi on 6/16/2017.
 */

public class Recipe implements Parcelable{

    private String id;
    private String name;
    private ArrayList<Ingredients> ingredients = new ArrayList<>();
    private ArrayList<Steps> steps = new ArrayList<>();
    private String servings;
    private String image;

    public Recipe(String id, String name,  String servings, ArrayList<Ingredients> ingredients, ArrayList<Steps> steps, String image) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.servings = servings;
        this.image = image;
    }

    public Recipe(){

    }

    public Recipe(Parcel in){

        id = in.readString();
        name = in.readString();
        ingredients = new ArrayList<>();
        in.readList(ingredients,null);
        steps = new ArrayList<>();
        in.readList(steps,null);
        servings = in.readString();
        image = in.readString();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Ingredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<Steps> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<Steps> steps) {
        this.steps = steps;
    }

    public String getServings() {
        return servings;
    }

    public void setServings(String servings) {
        this.servings = servings;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(id);
        dest.writeString(name);
        dest.writeList(ingredients);
        dest.writeList(steps);
        dest.writeString(servings);
        dest.writeString(image);

    }

    public static final Parcelable.Creator<Recipe> CREATOR = new Parcelable.Creator<Recipe>(){

        @Override
        public Recipe createFromParcel(Parcel source) {
            return new Recipe(source);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };
}
