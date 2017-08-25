package com.mam.recipepuppy.injector.module;

import com.mam.recipepuppy.data.RecipesRepositoryImpl;
import com.mam.recipepuppy.data.api.services.ApiService;
import com.mam.recipepuppy.domain.interactors.common.InteractorExecutor;
import com.mam.recipepuppy.domain.interactors.common.UiThreadHandler;
import com.mam.recipepuppy.domain.interactors.recipes.GetRecipesInteractor;
import com.mam.recipepuppy.domain.repository.RecipesRepository;
import com.mam.recipepuppy.presentation.common.BaseActivity;
import com.mam.recipepuppy.presentation.recipes.RecipesPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class RecipesModule extends ActivityModule {

    public RecipesModule(BaseActivity baseActivity) {
        super(baseActivity);
    }

    @Provides
    public RecipesPresenter provideRecipesPresenter(InteractorExecutor executor, GetRecipesInteractor getRecipesInteractor) {
        return new RecipesPresenter(executor, getRecipesInteractor);
    }

    @Provides
    public GetRecipesInteractor providesGetRecipesInteractor(RecipesRepository recipesRepository, UiThreadHandler uiThreadHandler) {
        return new GetRecipesInteractor(recipesRepository, uiThreadHandler);
    }

    @Provides
    public RecipesRepository providesRecipesRepository(ApiService apiRecipes) {
        RecipesRepository recipesRepository = new RecipesRepositoryImpl(apiRecipes);
        return recipesRepository;
    }
}
