package com.mam.recipepuppy;

import android.app.Application;

import com.mam.recipepuppy.injector.component.ComponentsHelper;

public class RecipeApplication extends Application {

    private ComponentsHelper componentsHelper;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public ComponentsHelper getComponentsHelper() {
        if (componentsHelper == null) {
            componentsHelper = ComponentsHelper.createInstance(this).initialize();
        }
        componentsHelper.getAppComponent();
        return componentsHelper;
    }
}
