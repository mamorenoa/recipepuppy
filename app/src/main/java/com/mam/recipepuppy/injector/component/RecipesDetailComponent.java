package com.mam.recipepuppy.injector.component;

import com.mam.recipepuppy.injector.module.RecipeDetailModule;
import com.mam.recipepuppy.presentation.recipes.detail.RecipeDetailActivity;

import dagger.Subcomponent;

@Subcomponent(
        modules = {
                RecipeDetailModule.class
        }
)
public interface RecipesDetailComponent {
    void inject(RecipeDetailActivity recipeDetailActivity);
}
