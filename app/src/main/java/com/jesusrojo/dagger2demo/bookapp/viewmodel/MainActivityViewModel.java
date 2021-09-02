package com.jesusrojo.dagger2demo.bookapp.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.jesusrojo.dagger2demo.bookapp.model.Book;
import com.jesusrojo.dagger2demo.bookapp.model.Category;
import com.jesusrojo.dagger2demo.bookapp.model.EBookShopRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {
    private EBookShopRepository eBookShopRepository;
    private LiveData<List<Category>> allCategories;
    private LiveData<List<Book>> booksOfASelectedCategory;

    public MainActivityViewModel(EBookShopRepository eBookShopRepository) {

        this.eBookShopRepository = eBookShopRepository;
    }

    public LiveData<List<Category>> getAllCategories() {

        allCategories=eBookShopRepository.getCategories();
        return allCategories;
    }

    public LiveData<List<Book>> getBooksOfASelectedCategory(int categoryId) {

        booksOfASelectedCategory=eBookShopRepository.getBooks(categoryId);
        return booksOfASelectedCategory;
    }

    public void addNewBook(Book book){
        eBookShopRepository.insertBook(book);
    }

    public void updateBook(Book book){
        eBookShopRepository.updateBook(book);
    }

    public void deleteBook(Book book){
        eBookShopRepository.deleteBook(book);
    }
}
