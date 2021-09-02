package com.jesusrojo.dagger2demo.bookapp.viewmodel;


import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.jesusrojo.dagger2demo.bookapp.model.EBookShopRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MainActivityViewModelFactory implements ViewModelProvider.Factory {

    private final EBookShopRepository eBookShopRepository;

    @Inject
    public MainActivityViewModelFactory(EBookShopRepository eBookShopRepository) {
        this.eBookShopRepository = eBookShopRepository;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T)new MainActivityViewModel(eBookShopRepository);
    }
}
