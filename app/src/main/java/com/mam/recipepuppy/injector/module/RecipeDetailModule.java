package com.mam.recipepuppy.injector.module;

import com.mam.recipepuppy.presentation.common.BaseActivity;

import dagger.Module;

@Module
public class RecipeDetailModule extends ActivityModule {

    public RecipeDetailModule(BaseActivity baseActivity) {
        super(baseActivity);
    }
}
