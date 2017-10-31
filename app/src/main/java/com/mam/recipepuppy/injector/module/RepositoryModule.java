package com.mam.recipepuppy.injector.module;

import com.mam.recipepuppy.data.RecipesRepository;
import com.mam.recipepuppy.data.RecipesRepositoryImpl;
import com.mam.recipepuppy.data.api.ApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Singleton
    @Provides
    public RecipesRepository providesRecipesRepository(ApiService apiRecipes) {
        RecipesRepository recipesRepository = new RecipesRepositoryImpl(apiRecipes);
        return recipesRepository;
    }
}
