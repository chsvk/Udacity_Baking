package com.example.vamshi.baking.Data;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Vamshi on 6/16/2017.
 */

public class Steps implements Parcelable{



    private String id;
    private String shortDescription;
    private String description;
    private String videoURL;

    public Steps(String id, String shortDescription, String description, String videoURL) {

        this.id = id;
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoURL = videoURL;
    }

    public Steps(){

    }

    public Steps(Parcel in){

        id = in.readString();
        shortDescription = in.readString();
        description = in.readString();
        videoURL = in.readString();

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(id);
        dest.writeString(shortDescription);
        dest.writeString(description);
        dest.writeString(videoURL);

    }

    public static final Parcelable.Creator<Steps> CREATOR = new Parcelable.Creator<Steps>(){

        @Override
        public Steps createFromParcel(Parcel source) {
            return new Steps(source);
        }

        @Override
        public Steps[] newArray(int size) {
            return new Steps[size];
        }
    };
}
