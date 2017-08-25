package com.mam.recipepuppy.presentation.recipes;


import com.mam.recipepuppy.domain.model.Recipe;
import com.mam.recipepuppy.presentation.common.BaseView;

import java.util.List;

public interface RecipesView extends BaseView {

    void showRecipes(List<Recipe> recipes);

    void showRecipeListEmpty();

    void showDefaultError();

    void showConnectionError();
}
