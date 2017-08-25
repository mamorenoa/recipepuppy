package com.mam.recipepuppy.domain.interactors.common;

public interface UiThreadHandler {
    void post(Runnable r);
}
