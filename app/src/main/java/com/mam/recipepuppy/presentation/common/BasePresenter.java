package com.mam.recipepuppy.presentation.common;

public interface BasePresenter {
	void onViewAttached(BaseView view);
	void onViewDetached();
}
