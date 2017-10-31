package com.mam.recipepuppy.presentation.recipes;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mam.recipepuppy.R;
import com.mam.recipepuppy.presentation.model.Recipe;
import com.mam.recipepuppy.utils.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolderRecipe> {

    public interface OnItemClickListener {
        void onItemClick(Recipe recipe, ImageView image);
    }

    private OnItemClickListener clickListener;
    private List<Recipe> recipes;
    private ImageLoader imageLoader;

    public RecipesAdapter(List<Recipe> recipes, ImageLoader imageLoader) {
        this.recipes = recipes;
        this.imageLoader = imageLoader;
    }

    @Override
    public ViewHolderRecipe onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe, parent, false);
        ViewHolderRecipe heroeViewHolder = new ViewHolderRecipe(view);
        return heroeViewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolderRecipe holder, int position) {
        final Recipe recipe = recipes.get(position);
        holder.textTitle.setText(recipe.getTitle());
        holder.textIngredients.setText(recipe.getIngredients());
        holder.textHref.setText(recipe.getHref());
        if (!TextUtils.isEmpty(recipe.getThumbnail())) {
            imageLoader.loadImage(recipe.getThumbnail(), holder.imageRecipe, R.drawable.recipeplaceholder, true);
        }
        holder.cardRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onItemClick(recipe, holder.imageRecipe);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void clear() {
        int size = recipes.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                recipes.remove(0);
            }
            this.notifyItemRangeRemoved(0, size);
        }
    }

    class ViewHolderRecipe extends RecyclerView.ViewHolder {
        @BindView(R.id.cardRecipe)
        protected CardView cardRecipe;
        @BindView(R.id.imageRecipe)
        protected ImageView imageRecipe;
        @BindView(R.id.textTitle)
        protected TextView textTitle;
        @BindView(R.id.textIngredients)
        protected TextView textIngredients;
        @BindView(R.id.textHref)
        protected TextView textHref;

        ViewHolderRecipe(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
