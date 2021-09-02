package com.jesusrojo.dagger2demo.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MyAppModule {
    private final Application application;

    public MyAppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application providesApplication(){
        return application;
    }
}