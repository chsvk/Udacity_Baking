package com.example.vamshi.baking.Data;

import java.util.List;

import io.realm.RealmObject;

/**
 * Created by Vamshi on 6/16/2017.
 */

public class Recipe extends RealmObject{

    private String id;
    private String name;
    public static List<Ingredients> ingredients;
    public static List<Steps> steps;
    private String servings;

    public Recipe(String id, String name,  String servings, List<Ingredients> ingredients, List<Steps> steps) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.servings = servings;
    }

    public Recipe(){

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

    public static List<Ingredients> getIngredients() {
        return ingredients;
    }

    public static void setIngredients(List<Ingredients> ingredients) {
        Recipe.ingredients = ingredients;
    }

    public static List<Steps> getSteps() {
        return steps;
    }

    public static void setSteps(List<Steps> steps) {
        Recipe.steps = steps;
    }

    public String getServings() {
        return servings;
    }

    public void setServings(String servings) {
        this.servings = servings;
    }
}
