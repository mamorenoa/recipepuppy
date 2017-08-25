package com.mam.recipepuppy.data.api.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ServerRecipeResponse {
    @SerializedName("title")
    private String title;
    @SerializedName("href")
    private String href;
    @SerializedName("results")
    private List<ServerRecipe> results;

    public String getTitle() {
        return title;
    }

    public String getHref() {
        return href;
    }

    public List<ServerRecipe> getResults() {
        return results;
    }
}
