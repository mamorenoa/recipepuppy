
package com.mam.recipepuppy.domain.interactors.recipes;

import com.mam.recipepuppy.data.exceptions.io.ConnectionException;
import com.mam.recipepuppy.data.exceptions.recipes.GetRecipesException;
import com.mam.recipepuppy.domain.interactors.common.BaseInteractor;
import com.mam.recipepuppy.domain.interactors.common.BaseInteractorCallback;
import com.mam.recipepuppy.domain.interactors.common.UiThreadHandler;
import com.mam.recipepuppy.domain.model.Recipe;
import com.mam.recipepuppy.domain.repository.RecipesRepository;

import java.util.List;

public class GetRecipesInteractor extends BaseInteractor {

    private RecipesRepository recipesRepository;
    private Callback callback;
    private String query;

    public interface Callback extends BaseInteractorCallback {
        void onGetRecipesSuccess(List<Recipe> recipes);

        void onGetRecipesError();
    }

    public GetRecipesInteractor(RecipesRepository recipesRepository, UiThreadHandler uiThreadHandler) {
        super(uiThreadHandler);
        this.recipesRepository = recipesRepository;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public void executeInteractor() {
        try {
            List<Recipe> recipes = recipesRepository.get(query);
            if (recipes != null) {
                notifyGetRecipesSuccess(recipes);
            } else {
                onDefaultError(callback);
            }
        } catch (GetRecipesException getRecipesException) {
            notifyGetRecipesError();
        } catch (ConnectionException connectionException) {
            onConnectionError(callback);
        }
    }

    private void notifyGetRecipesSuccess(final List<Recipe> recipes) {
        uiThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onGetRecipesSuccess(recipes);
            }
        });
    }

    private void notifyGetRecipesError() {
        uiThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onGetRecipesError();
            }
        });
    }
}
