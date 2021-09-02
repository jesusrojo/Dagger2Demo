package com.jesusrojo.dagger2demo.di.ebookdi;

import android.app.Application;


import com.jesusrojo.dagger2demo.bookapp.model.EBookShopRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Singleton
    @Provides
    EBookShopRepository providesEBookShopRepository(Application application){
         return new EBookShopRepository(application);
     }
}
