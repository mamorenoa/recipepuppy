package com.mam.recipepuppy.data.mappers;

import com.mam.recipepuppy.data.model.ServerRecipe;
import com.mam.recipepuppy.presentation.model.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeMapper {

    public static List<Recipe> serverToDomain(List<ServerRecipe> serverRecipes) {
        List<Recipe> recipes = new ArrayList<>();
        for (ServerRecipe serverRecipe : serverRecipes) {
            recipes.add(serverToDomain(serverRecipe));
        }
        return recipes;
    }

    public static Recipe serverToDomain(ServerRecipe serverRecipe) {
        Recipe recipe = new Recipe();
        recipe.setTitle(serverRecipe.getTitle());
        recipe.setHref(serverRecipe.getHref());
        recipe.setIngredients(serverRecipe.getIngredients());
        recipe.setThumbnail(serverRecipe.getThumbnail());
        return recipe;
    }
}

