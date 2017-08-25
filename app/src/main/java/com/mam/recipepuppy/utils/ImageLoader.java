package com.mam.recipepuppy.utils;

import android.widget.ImageView;

public interface ImageLoader {
    void loadImage(String url, ImageView imageView, int placeholder, boolean caching);
}