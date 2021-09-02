package com.jesusrojo.dagger2demo.bookapp;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.jesusrojo.dagger2demo.R;
import com.jesusrojo.dagger2demo.bookapp.model.Book;
import com.jesusrojo.dagger2demo.databinding.BookAddAndEditLayoutBinding;

public class AddAndEditActivity extends AppCompatActivity {

    private Book book;
    public static final String BOOK_ID="bookId";
    public static final String BOOK_NAME="bookName";
    public static final String UNIT_PRICE="unitPrice";
    private BookAddAndEditLayoutBinding activityAddAndEditBinding;
    private AddAndEditActivityClickHandlers addAndEditActivityClickHandlers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_add_and_edit_layout);

        book=new Book();
        activityAddAndEditBinding= DataBindingUtil.setContentView(this,R.layout.book_add_and_edit_layout);
        activityAddAndEditBinding.setBook(book);

        addAndEditActivityClickHandlers = new AddAndEditActivityClickHandlers(this);
        activityAddAndEditBinding.setClickHandler(addAndEditActivityClickHandlers);

        Intent intent=getIntent();
        if(intent.hasExtra(BOOK_ID)){

             setTitle("Edit Book");
             book.setBookName(intent.getStringExtra(BOOK_NAME));
             book.setUnitPrice(intent.getStringExtra(UNIT_PRICE));

        }else{
             setTitle("Add New Book");

        }


    }

    public class AddAndEditActivityClickHandlers{
        Context context;

        public AddAndEditActivityClickHandlers(Context context) {
            this.context = context;
        }

        public void onSubmitButtonClicked(View view){
            if(book.getBookName()==null){
                Toast.makeText(context,"Name field cannot be empty",Toast.LENGTH_LONG).show();
            }else{
                Intent intent=new Intent();
                intent.putExtra(BOOK_NAME,book.getBookName());
                intent.putExtra(UNIT_PRICE,book.getUnitPrice());
                setResult(RESULT_OK,intent);
                finish();
            }
        }
    }
}
