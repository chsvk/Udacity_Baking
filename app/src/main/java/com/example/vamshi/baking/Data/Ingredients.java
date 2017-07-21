package com.example.vamshi.baking.Data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Vamshi on 6/16/2017.
 */

public class Ingredients implements Parcelable {

    private String quantity;
    private String measure;
    private String ingredient;


    public Ingredients(String quantity, String measure, String ingredient) {
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }

    public Ingredients(){

    }

    public Ingredients(Parcel in){

        quantity = in.readString();
        measure = in.readString();
        ingredient = in.readString();

    }


    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(quantity);
        dest.writeString(measure);
        dest.writeString(ingredient);

    }
    public static final Parcelable.Creator<Ingredients> CREATOR = new Parcelable.Creator<Ingredients>(){

        @Override
        public Ingredients createFromParcel(Parcel source) {
            return new Ingredients(source);
        }

        @Override
        public Ingredients[] newArray(int size) {
            return new Ingredients[size];
        }
    };
}
