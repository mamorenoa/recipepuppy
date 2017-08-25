package com.mam.recipepuppy.data;

import com.google.gson.JsonSyntaxException;
import com.mam.recipepuppy.data.api.response.ServerRecipeResponse;
import com.mam.recipepuppy.data.api.services.ApiService;
import com.mam.recipepuppy.data.exceptions.io.ConnectionException;
import com.mam.recipepuppy.data.exceptions.recipes.GetRecipesException;
import com.mam.recipepuppy.data.mappers.RecipeMapper;
import com.mam.recipepuppy.domain.model.Recipe;
import com.mam.recipepuppy.domain.repository.RecipesRepository;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;

public class RecipesRepositoryImpl extends BaseRepository implements RecipesRepository {

    public RecipesRepositoryImpl(ApiService apiService) {
        super(apiService);
    }

    @Override
    public List<Recipe> get(String query) throws GetRecipesException, ConnectionException {
        List<Recipe> recipes;
        ServerRecipeResponse serverRecipesResponse;
        try {
            Response<ServerRecipeResponse> serverResponse = apiService.getRecipes(query).execute();
            if (serverResponse.isSuccessful()) {
                serverRecipesResponse = serverResponse.body();
                recipes = RecipeMapper.serverToDomain(serverRecipesResponse.getResults());
            } else {
                throw new GetRecipesException();
            }
        } catch (IOException ioException) {
            throw new ConnectionException();
        } catch (JsonSyntaxException jsonException) {
            throw new ConnectionException();
        }
        return recipes;
    }
}
