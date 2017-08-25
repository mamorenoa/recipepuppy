package com.mam.recipepuppy.injector.component;

import com.mam.recipepuppy.RecipeApplication;
import com.mam.recipepuppy.injector.module.AppModule;
import com.mam.recipepuppy.injector.module.NetworkModule;

public class ComponentsHelper {

    private RecipeApplication application;

    private AppComponent appComponent;

    public static ComponentsHelper createInstance(RecipeApplication application) {
        return new ComponentsHelper(application);
    }

    private ComponentsHelper(RecipeApplication application) {
        this.application = application;
    }

    public ComponentsHelper initialize() {
        this.buildAppComponent();
        return this;
    }

    private void buildAppComponent() {
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(application))
                .networkModule(new NetworkModule())
                .build();
    }

    public AppComponent getAppComponent() {
        if (appComponent == null) {
            initialize();
        }
        return appComponent;
    }
}