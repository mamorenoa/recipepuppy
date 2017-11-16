package com.mam.recipepuppy.injector.module;

import android.app.Application;

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
    public ImageLoader provideImageLoaderModule() {
        ImageLoader imageLoader = new ImageLoaderImpl(application);
        return imageLoader;
    }

}
