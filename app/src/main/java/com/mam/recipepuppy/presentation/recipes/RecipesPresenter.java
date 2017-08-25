package com.mam.recipepuppy.presentation.recipes;


import com.mam.recipepuppy.domain.interactors.common.InteractorExecutor;
import com.mam.recipepuppy.domain.interactors.recipes.GetRecipesInteractor;
import com.mam.recipepuppy.domain.model.Recipe;
import com.mam.recipepuppy.presentation.common.AbstractPresenter;
import com.mam.recipepuppy.presentation.common.BaseView;

import java.util.List;

public class RecipesPresenter extends AbstractPresenter implements GetRecipesInteractor.Callback {

    private InteractorExecutor executor;
    private RecipesView recipesView;
    private GetRecipesInteractor getRecipesInteractor;

    public RecipesPresenter(InteractorExecutor executor, GetRecipesInteractor getRecipesInteractor) {
        this.executor = executor;
        this.getRecipesInteractor = getRecipesInteractor;
    }

    public void onViewAttached(BaseView view) {
        recipesView = (RecipesView) view;
    }

    public void onViewDetached() {
        recipesView = null;
    }

    public void getRecipes(String value) {
        recipesView.showLoading();
        getRecipesInteractor.setCallback(this);
        getRecipesInteractor.setQuery(value);
        executor.execute(getRecipesInteractor);
    }

    @Override
    public void connectionError() {
        recipesView.hideLoading();
        recipesView.showConnectionError();
    }

    @Override
    public void defaultError() {
        recipesView.hideLoading();
        recipesView.showDefaultError();
    }

    @Override
    public void onGetRecipesSuccess(List<Recipe> recipes) {
        recipesView.hideLoading();
        if (recipes.size() > 0) {
            recipesView.showRecipes(recipes);
        } else {
            recipesView.showRecipeListEmpty();
        }
    }

    @Override
    public void onGetRecipesError() {
        recipesView.hideLoading();
        recipesView.showDefaultError();
    }
}
