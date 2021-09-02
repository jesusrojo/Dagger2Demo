package com.jesusrojo.dagger2demo.smartphone.model;

import androidx.annotation.NonNull;

import javax.inject.Inject;

public class SIMCard {

    @NonNull private ServiceProvider serviceProvider;

    @Inject
    public SIMCard(@NonNull ServiceProvider serviceProvider) {
        this.serviceProvider=serviceProvider;
    }
}