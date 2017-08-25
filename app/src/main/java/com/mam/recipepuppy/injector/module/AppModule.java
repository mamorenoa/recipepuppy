package com.mam.recipepuppy.injector.module;

import android.app.Application;

import com.mam.recipepuppy.domain.interactors.common.InteractorExecutor;
import com.mam.recipepuppy.domain.interactors.common.InteractorExecutorImpl;
import com.mam.recipepuppy.domain.interactors.common.UiThreadHandler;
import com.mam.recipepuppy.domain.interactors.common.UiThreadHandlerImpl;
import com.mam.recipepuppy.presentation.navigator.Navigator;
import com.mam.recipepuppy.utils.ImageLoader;
import com.mam.recipepuppy.utils.ImageLoaderImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Application providesApplication() {
        return application;
    }

    @Provides
    @Singleton
    public Navigator provideNavigator() {
        Navigator navigator = new Navigator();
        return navigator;
    }

    @Provides
    @Singleton
    public UiThreadHandler provideUIThreadHandler() {
        UiThreadHandler uiThreadHandler = new UiThreadHandlerImpl();
        return uiThreadHandler;
    }

    @Provides
    @Singleton
    public InteractorExecutor provideInteractorExecutor() {
        InteractorExecutor interactorExecutor = new InteractorExecutorImpl();
        return interactorExecutor;
    }

    @Provides
    @Singleton
    public ImageLoader provideImageLoaderModule() {
        ImageLoader imageLoader = new ImageLoaderImpl(application);
        return imageLoader;
    }

}
