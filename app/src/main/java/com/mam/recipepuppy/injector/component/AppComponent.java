package com.mam.recipepuppy.injector.component;

import android.app.Application;

import com.mam.recipepuppy.data.api.services.ApiService;
import com.mam.recipepuppy.domain.interactors.common.InteractorExecutor;
import com.mam.recipepuppy.domain.interactors.common.UiThreadHandler;
import com.mam.recipepuppy.injector.module.AppModule;
import com.mam.recipepuppy.injector.module.NetworkModule;
import com.mam.recipepuppy.injector.module.RecipeDetailModule;
import com.mam.recipepuppy.injector.module.RecipesModule;
import com.mam.recipepuppy.presentation.navigator.Navigator;
import com.mam.recipepuppy.utils.ImageLoader;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        NetworkModule.class}
)
public interface AppComponent {

    //Submodules
    RecipesComponent plus(RecipesModule module);

    RecipesDetailComponent plus(RecipeDetailModule module);

    //App general modules
    Application getApplication();

    Navigator getNavigator();

    InteractorExecutor getInteractorExecutor();

    UiThreadHandler getUIThreadHandler();

    ApiService getApiService();

    ImageLoader getImageLoader();
}
