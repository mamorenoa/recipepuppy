package com.mam.recipepuppy.data;

import android.arch.lifecycle.LiveData;

import com.mam.recipepuppy.presentation.model.Recipe;

import java.util.List;

public interface RecipesRepository {
    LiveData<List<Recipe>> get(String query);
}

