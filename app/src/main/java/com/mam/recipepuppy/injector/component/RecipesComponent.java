package com.mam.recipepuppy.injector.component;


import com.mam.recipepuppy.injector.module.RecipesModule;
import com.mam.recipepuppy.presentation.recipes.RecipesActivity;
import com.mam.recipepuppy.presentation.recipes.RecipesPresenter;

import dagger.Subcomponent;

@Subcomponent(
        modules = {
                RecipesModule.class
        }
)
public interface RecipesComponent {
    void inject(RecipesActivity heroesActivity);

    RecipesPresenter getRecipesPresenter();
}
