package com.mam.recipepuppy.injector.module;

import android.arch.lifecycle.ViewModelProviders;

import com.mam.recipepuppy.data.RecipesRepository;
import com.mam.recipepuppy.presentation.model.RecipesViewModel;
import com.mam.recipepuppy.presentation.recipes.RecipesActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class RecipesModule extends ActivityModule {

    private RecipesActivity activity;

    public RecipesModule(RecipesActivity baseActivity) {
        super(baseActivity);
        this.activity = baseActivity;
    }

    @Provides
    public RecipesViewModel provideRecipesViewModel(RecipesRepository recipesRepository) {
        RecipesViewModel recipesViewModel = ViewModelProviders.of(this.activity).get(RecipesViewModel.class);
        recipesViewModel.setRecipesRepository(recipesRepository);
        return recipesViewModel;
    }
}
