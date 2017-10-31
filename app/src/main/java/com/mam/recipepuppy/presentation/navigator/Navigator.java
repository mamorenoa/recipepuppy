package com.mam.recipepuppy.presentation.navigator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;

import com.mam.recipepuppy.presentation.model.Recipe;
import com.mam.recipepuppy.presentation.recipes.RecipesActivity;
import com.mam.recipepuppy.presentation.recipes.detail.RecipeDetailActivity;

public class Navigator {

    public interface From {
        int NOT_FOLLOW = -1;
    }

    public void navigate(@NonNull Context context, int requestCode, ActivityOptionsCompat options, @Nullable Intent intent) {
        ((RecipesActivity) context).startActivityForResult(intent, requestCode, options.toBundle());
    }

    public void navigateToRecipeDetail(Context context, Recipe recipe, View viewShared) {
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, viewShared, "recipe");
        navigate(context, From.NOT_FOLLOW, options, RecipeDetailActivity.buildIntent(context, recipe));
    }
}
