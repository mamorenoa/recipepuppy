package com.mam.recipepuppy.presentation.recipes;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.mam.recipepuppy.presentation.model.Recipe;
import com.mam.recipepuppy.injector.module.RecipesModule;
import com.mam.recipepuppy.presentation.common.BaseActivity;
import com.mam.recipepuppy.presentation.common.BaseView;
import com.mam.recipepuppy.presentation.navigator.Navigator;
import com.mam.recipepuppy.presentation.widget.dialog.DialogAbstract;
import com.mam.recipepuppy.presentation.widget.spinner.SpinnerLoading;
import com.mam.recipepuppy.utils.ImageLoader;
import com.mam.recipepuppy.utils.animations.AnimationsUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class RecipesActivity extends BaseActivity implements BaseView {

    @Inject
    Navigator navigator;
    @Inject
    SpinnerLoading spinnerLoading;
    @Inject
    DialogAbstract dialog;
    @Inject
    ImageLoader imageLoader;
    @Inject
    RecipesViewModel recipesViewModel;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerRecipes)
    RecyclerView recyclerRecipes;
    @BindView(R.id.textRecipesListEmpty)
    TextView textViewRecipesListEmpty;

    private RecipesAdapter recipesAdapter;
    private LinearLayoutManager layoutManager;
    private SearchView viewSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolBar();
        observeViewModel(recipesViewModel, "");
    }

    @Override
    protected void setModule() {
        getComponentsHelper().getAppComponent().plus(new RecipesModule(this)).inject(this);
    }

    @Override
    public int getActivityLayout() {
        return R.layout.activity_recipes;
    }

    public void showRecipes(List<Recipe> recipes) {
        textViewRecipesListEmpty.setVisibility(View.GONE);
        recyclerRecipes.setVisibility(View.VISIBLE);
        recyclerRecipes.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
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
                    observeViewModel(recipesViewModel, newText);
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

    private void observeViewModel(RecipesViewModel viewModel, String query) {
        viewModel.getRecipesObservable(query).observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(@Nullable List<Recipe> recipes) {
                if ((recipes != null) && (!recipes.isEmpty())) {
                    showRecipes(recipes);
                } else {
                    showRecipeListEmpty();
                }
            }
        });
    }

    private void initToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle(getString(R.string.app_name));
    }
}
