package com.mam.recipepuppy.data.api;


import com.mam.recipepuppy.data.model.ServerRecipeResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiService {

    //Endpoint
    String API = "http://www.recipepuppy.com/api/";

    //Headers
    String CONTENT_TYPE = "Content-Type: application/json";
    String ACCEPT = "Accept: application/json";

    @Headers({CONTENT_TYPE, ACCEPT})
    @GET(".")
    Call<ServerRecipeResponse> getRecipes(@Query("q") String query);
}