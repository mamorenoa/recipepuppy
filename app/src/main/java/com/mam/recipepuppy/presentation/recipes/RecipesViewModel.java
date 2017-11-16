package com.mam.recipepuppy.presentation.recipes;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.mam.recipepuppy.data.RecipesRepository;
import com.mam.recipepuppy.presentation.model.Recipe;

import java.util.List;


public class RecipesViewModel extends ViewModel {

    private RecipesRepository recipesRepository;

    public RecipesViewModel() {
    }

    public void setRecipesRepository(RecipesRepository recipesRepository) {
        this.recipesRepository = recipesRepository;
    }

    public LiveData<List<Recipe>> getRecipesObservable(String query) {
        return recipesRepository.get(query);
    }
}
