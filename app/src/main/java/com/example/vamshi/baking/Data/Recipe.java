package com.example.vamshi.baking.Data;

import java.util.List;

/**
 * Created by Vamshi on 6/16/2017.
 */

public class Recipe {

    private String id;
    private String name;
//    public static List<Ingredients> ingredients;
//    private List<Steps> steps;
    private String servings;

    public Recipe(String id, String name,  String servings) {
        this.id = id;
        this.name = name;
//        this.ingredients = ingredients;
//        this.steps = steps;
        this.servings = servings;
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

//    public List<Ingredients> getIngredients() {
//        return ingredients;
//    }
//
//    public void setIngredients(List<Ingredients> ingredients) {
//        this.ingredients = ingredients;
//    }
//
//    public List<Steps> getSteps() {
//        return steps;
//    }
//
//    public void setSteps(List<Steps> steps) {
//        this.steps = steps;
//    }

    public String getServings() {
        return servings;
    }

    public void setServings(String servings) {
        this.servings = servings;
    }
}
