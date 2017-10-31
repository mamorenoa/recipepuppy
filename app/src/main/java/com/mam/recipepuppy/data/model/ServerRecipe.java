package com.mam.recipepuppy.data.model;

import com.google.gson.annotations.SerializedName;

public class ServerRecipe {
    @SerializedName("title")
    private String title;
    @SerializedName("href")
    private String href;
    @SerializedName("ingredients")
    private String ingredients;
    @SerializedName("thumbnail")
    private String thumbnail;

    public String getTitle() {
        return title;
    }

    public String getHref() {
        return href;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getThumbnail() {
        return thumbnail;
    }
}


