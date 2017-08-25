package com.mam.recipepuppy.presentation.recipes.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.mam.recipepuppy.R;
import com.mam.recipepuppy.domain.model.Recipe;
import com.mam.recipepuppy.injector.module.RecipeDetailModule;
import com.mam.recipepuppy.presentation.common.BaseActivity;
import com.mam.recipepuppy.utils.ImageLoader;

import javax.inject.Inject;

import butterknife.Bind;

public class RecipeDetailActivity extends BaseActivity {

    public static final String KEY_RECIPE_DETAIL = "com.mam.recipepuppy.presentation.recipes.detail.RECIPE_DETAIL";

    @Inject
    ImageLoader imageLoader;

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.textTitle)
    TextView textName;
    @Bind(R.id.textIngredientsValue)
    TextView textIngredients;
    @Bind(R.id.textReferenceValue)
    TextView textReference;
    @Bind(R.id.imageRecipe)
    ImageView imageRecipe;

    public static Intent buildIntent(Context context, Recipe recipe) {
        Intent intentServiceDetail = new Intent(context, RecipeDetailActivity.class);
        Bundle bundleData = new Bundle();
        bundleData.putSerializable(KEY_RECIPE_DETAIL, recipe);
        intentServiceDetail.putExtras(bundleData);
        return intentServiceDetail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRecipeData(getDataFromBundle());
    }

    @Override
    protected void setModule() {
        getComponentsHelper().getAppComponent().plus(new RecipeDetailModule(this)).inject(this);
    }

    @Override
    public int getActivityLayout() {
        return R.layout.activity_recipe_detail;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void setRecipeData(Recipe recipe) {
        if (recipe != null) {
            initToolBar("");
            textName.setText(recipe.getTitle());
            textIngredients.setText(recipe.getIngredients());
            textReference.setText(recipe.getHref());
            if (!TextUtils.isEmpty(recipe.getThumbnail())) {
                imageLoader.loadImage(recipe.getThumbnail(), imageRecipe, R.drawable.recipeplaceholder, true);
            }
        }
    }

    private void initToolBar(String title) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitle(title);
    }

    private Recipe getDataFromBundle() {
        Bundle bundleData = getIntent().getExtras();
        Recipe recipe = null;
        if (bundleData != null) {
            recipe = (Recipe) bundleData.getSerializable(KEY_RECIPE_DETAIL);
        }
        return recipe;
    }
}
