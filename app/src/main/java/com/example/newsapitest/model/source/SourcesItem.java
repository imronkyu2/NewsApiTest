package com.example.newsapitest.model.source;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;

public class SourcesItem {

    @SerializedName("country")
    private String country;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("language")
    private String language;

    @SerializedName("id")
    private String id;

    @SerializedName("category")
    private String category;

    @SerializedName("url")
    private String url;

    protected SourcesItem(Parcel in) {
        country = in.readString();
        name = in.readString();
        description = in.readString();
        language = in.readString();
        id = in.readString();
        category = in.readString();
        url = in.readString();
    }


    public String getCountry() {
        return country;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLanguage() {
        return language;
    }

    public String getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getUrl() {
        return url;
    }

}