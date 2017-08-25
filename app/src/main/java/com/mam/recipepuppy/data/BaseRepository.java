package com.mam.recipepuppy.data;

import com.mam.recipepuppy.data.api.services.ApiService;

public abstract class BaseRepository {

    protected ApiService apiService;

    public BaseRepository(ApiService apiService) {
        this.apiService = apiService;
    }

}
