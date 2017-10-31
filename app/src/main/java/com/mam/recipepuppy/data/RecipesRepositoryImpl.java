package com.mam.recipepuppy.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.mam.recipepuppy.data.api.ApiService;
import com.mam.recipepuppy.data.mappers.RecipeMapper;
import com.mam.recipepuppy.data.model.ServerRecipeResponse;
import com.mam.recipepuppy.presentation.model.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipesRepositoryImpl extends BaseRepository implements RecipesRepository {

    public RecipesRepositoryImpl(ApiService apiService) {
        super(apiService);
    }

    @Override
    public LiveData<List<Recipe>> get(String query) {
        final MutableLiveData<List<Recipe>> data = new MutableLiveData<>();
        apiService.getRecipes(query).enqueue(new Callback<ServerRecipeResponse>() {
            @Override
            public void onResponse(Call<ServerRecipeResponse> call, Response<ServerRecipeResponse> response) {
                ServerRecipeResponse serverResponse = response.body();
                data.setValue(RecipeMapper.serverToDomain(serverResponse.getResults()));
            }

            @Override
            public void onFailure(Call<ServerRecipeResponse> call, Throwable t) {
            }
        });
        return data;
    }
}
