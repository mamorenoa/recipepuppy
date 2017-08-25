package com.mam.recipepuppy.presentation.recipes;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mam.recipepuppy.R;
import com.mam.recipepuppy.domain.model.Recipe;
import com.mam.recipepuppy.injector.module.RecipesModule;
import com.mam.recipepuppy.presentation.common.BaseActivity;
import com.mam.recipepuppy.presentation.navigator.Navigator;
import com.mam.recipepuppy.presentation.widget.dialog.DialogAbstract;
import com.mam.recipepuppy.presentation.widget.spinner.SpinnerLoading;
import com.mam.recipepuppy.utils.ImageLoader;
import com.mam.recipepuppy.utils.animations.AnimationsUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

public class RecipesActivity extends BaseActivity implements RecipesView {

    @Inject
    Navigator navigator;
    @Inject
    RecipesPresenter recipesPresenter;
    @Inject
    SpinnerLoading spinnerLoading;
    @Inject
    DialogAbstract dialog;
    @Inject
    ImageLoader imageLoader;

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recyclerRecipes)
    RecyclerView recyclerRecipes;
    @Bind(R.id.textRecipesListEmpty)
    TextView textViewRecipesListEmpty;

    private RecipesAdapter recipesAdapter;
    private SearchView viewSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recipesPresenter.onViewAttached(this);
        initToolBar();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        recipesPresenter.onViewDetached();
    }

    @Override
    protected void setModule() {
        getComponentsHelper().getAppComponent().plus(new RecipesModule(this)).inject(this);
    }

    @Override
    public int getActivityLayout() {
        return R.layout.activity_recipes;
    }

    @Override
    public void showRecipes(List<Recipe> recipes) {
        textViewRecipesListEmpty.setVisibility(View.GONE);
        recyclerRecipes.setVisibility(View.VISIBLE);
        recyclerRecipes.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerRecipes.setLayoutManager(layoutManager);
        recipesAdapter = new RecipesAdapter(recipes, imageLoader);
        recipesAdapter.setClickListener(new RecipesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Recipe recipe, ImageView imageShared) {
                navigator.navigateToRecipeDetail(RecipesActivity.this, recipe, imageShared);
            }
        });
        recyclerRecipes.setAdapter(recipesAdapter);
        AnimationsUtils.animateRecycler(recyclerRecipes);
    }

    @Override
    public void showRecipeListEmpty() {
        textViewRecipesListEmpty.setVisibility(View.VISIBLE);
        recyclerRecipes.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.seach_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.search);
        viewSearch = (SearchView) menuItem.getActionView();
        viewSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)) {
                    recipesAdapter.clear();
                    showRecipeListEmpty();
                } else {
                    recipesPresenter.getRecipes(newText);
                }
                return true;
            }
        });

        return true;
    }

    @Override
    public void showLoading() {
        spinnerLoading.show();
    }

    @Override
    public void hideLoading() {
        spinnerLoading.dismiss();
    }

    @Override
    public void showConnectionError() {
        dialog.showSimple(this, getString(R.string.common_info_dialog), getString(R.string.common_connection_error_dialog), getString(R.string.common_continue_dialog));
    }

    @Override
    public void showDefaultError() {
        dialog.showSimple(this, getString(R.string.common_info_dialog), getString(R.string.common_default_error_dialog), getString(R.string.common_continue_dialog));
    }

    private void initToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle(getString(R.string.app_name));
    }
}
