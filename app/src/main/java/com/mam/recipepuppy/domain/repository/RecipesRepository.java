package com.mam.recipepuppy.domain.repository;

import com.mam.recipepuppy.data.exceptions.io.ConnectionException;
import com.mam.recipepuppy.data.exceptions.recipes.GetRecipesException;
import com.mam.recipepuppy.domain.model.Recipe;

import java.util.List;

public interface RecipesRepository {
    List<Recipe> get(String query) throws ConnectionException, GetRecipesException;
}

