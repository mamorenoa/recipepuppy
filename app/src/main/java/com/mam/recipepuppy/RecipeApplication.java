package com.mam.recipepuppy;

import android.app.Application;

import com.mam.recipepuppy.injector.component.ComponentsHelper;

public class RecipeApplication extends Application {

    private ComponentsHelper componentsHelper;
    public static RecipeApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    static public RecipeApplication getInstance() {
        return mInstance;
    }

    public ComponentsHelper getComponentsHelper() {
        if (componentsHelper == null) {
            componentsHelper = ComponentsHelper.createInstance(this).initialize();
        }
        componentsHelper.getAppComponent();
        return componentsHelper;
    }
}
