package com.jesusrojo.dagger2demo.bookapp;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.jesusrojo.dagger2demo.MyApp;
import com.jesusrojo.dagger2demo.R;
import com.jesusrojo.dagger2demo.bookapp.model.Book;
import com.jesusrojo.dagger2demo.bookapp.model.Category;
import com.jesusrojo.dagger2demo.bookapp.viewmodel.MainActivityViewModel;
import com.jesusrojo.dagger2demo.bookapp.viewmodel.MainActivityViewModelFactory;
import com.jesusrojo.dagger2demo.databinding.BooksActivityLayoutBinding;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class BooksActivity extends AppCompatActivity {

    private MainActivityViewModel mainActivityViewModel;
    private ArrayList<Category> categoriesList;
    private ArrayList<Book> booksList;
    private BooksActivityLayoutBinding activityMainBinding;
    private BooksActivityClickHandlers handlers;
    private Category selectedCategory;
    private RecyclerView booksRecyclerView;
    private BooksAdapter booksAdapter;
    private int selectedBookId;

    public static final int ADD_BOOK_REQUEST_CODE = 1;
    public static final int EDIT_BOOK_REQUEST_CODE = 2;

    @Inject
    public MainActivityViewModelFactory mainActivityViewModelFactory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.books_activity_layout);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.books_activity_layout);
        handlers = new BooksActivityClickHandlers();
        activityMainBinding.setClickHandlers(handlers);

        MyApp.getMyApp().getMyAppComponent().inject(this);

       // mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mainActivityViewModel = ViewModelProviders.of(this,mainActivityViewModelFactory)
                .get(MainActivityViewModel.class);

        mainActivityViewModel.getAllCategories().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(@Nullable List<Category> categories) {

                categoriesList = (ArrayList<Category>) categories;
                for (Category c : categories) {
                    Log.i("MyTAG", c.getCategoryName());
                }
                showOnSpinner();
            }
        });
    }


    private void showOnSpinner() {
        ArrayAdapter<Category> categoryArrayAdapter = new ArrayAdapter<Category>(this, R.layout.book_spinner_item, categoriesList);
        categoryArrayAdapter.setDropDownViewResource(R.layout.book_spinner_item);
        activityMainBinding.setSpinnerAdapter(categoryArrayAdapter);
    }

    private void loadBooksArrayList(int categoryId) {
        mainActivityViewModel.getBooksOfASelectedCategory(categoryId).observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(@Nullable List<Book> books) {
                booksList = (ArrayList<Book>) books;
                loadRecyclerView();
            }
        });

    }

    private void loadRecyclerView() {

        booksRecyclerView = activityMainBinding.secondaryLayout.rvBooks;
        booksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        booksRecyclerView.setHasFixedSize(true);

        booksAdapter = new BooksAdapter();
        booksRecyclerView.setAdapter(booksAdapter);

        booksAdapter.setBooks(booksList);

        booksAdapter.setListener(new BooksAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Book book) {
                selectedBookId = book.getBookId();

                Intent intent = new Intent(BooksActivity.this, AddAndEditActivity.class);
                intent.putExtra(AddAndEditActivity.BOOK_ID, selectedBookId);
                intent.putExtra(AddAndEditActivity.BOOK_NAME, book.getBookName());
                intent.putExtra(AddAndEditActivity.UNIT_PRICE, book.getUnitPrice());
                startActivityForResult(intent, EDIT_BOOK_REQUEST_CODE);
            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                Book bookToDelete = booksList.get(viewHolder.getAdapterPosition());
                mainActivityViewModel.deleteBook(bookToDelete);
            }
        }).attachToRecyclerView(booksRecyclerView);


    }

    public class BooksActivityClickHandlers {

        public void onFABClicked(View view) {
            Intent intent = new Intent(BooksActivity.this, AddAndEditActivity.class);
            startActivityForResult(intent, ADD_BOOK_REQUEST_CODE);
        }

        public void onSelectItem(AdapterView<?> parent, View view, int pos, long id) {
            selectedCategory = (Category) parent.getItemAtPosition(pos);
            String message = " id is " + selectedCategory.getId() + "\n name is " + selectedCategory.getCategoryName() + "\n email is " + selectedCategory.getCategoryDescription();
            loadBooksArrayList(selectedCategory.getId());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        int selectedCategoryId = selectedCategory.getId();
        if (requestCode == ADD_BOOK_REQUEST_CODE && resultCode == RESULT_OK) {

            Book book = new Book();
            book.setCategoryId(selectedCategoryId);
            book.setBookName(data.getStringExtra(AddAndEditActivity.BOOK_NAME));
            book.setUnitPrice(data.getStringExtra(AddAndEditActivity.UNIT_PRICE));
            mainActivityViewModel.addNewBook(book);

        } else if (requestCode == EDIT_BOOK_REQUEST_CODE && resultCode == RESULT_OK) {
            Book book = new Book();
            book.setCategoryId(selectedCategoryId);
            book.setBookName(data.getStringExtra(AddAndEditActivity.BOOK_NAME));
            book.setUnitPrice(data.getStringExtra(AddAndEditActivity.UNIT_PRICE));
            book.setBookId(selectedBookId);
            mainActivityViewModel.updateBook(book);
        }
    }
}
