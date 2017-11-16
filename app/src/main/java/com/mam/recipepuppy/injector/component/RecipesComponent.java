package com.mam.recipepuppy.injector.component;

import com.mam.recipepuppy.presentation.recipes.RecipesViewModel;
import com.mam.recipepuppy.injector.module.RecipesModule;
import com.mam.recipepuppy.presentation.recipes.RecipesActivity;

import dagger.Subcomponent;

@Subcomponent(
        modules = {
                RecipesModule.class
        }
)
public interface RecipesComponent {
    void inject(RecipesActivity heroesActivity);

    RecipesViewModel getRecipesViewModel();
}
