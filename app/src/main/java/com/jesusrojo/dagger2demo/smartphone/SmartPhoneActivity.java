package com.jesusrojo.dagger2demo.smartphone;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jesusrojo.dagger2demo.MyApp;
import com.jesusrojo.dagger2demo.R;
import com.jesusrojo.dagger2demo.bookapp.BooksActivity;
import com.jesusrojo.dagger2demo.contacts.ContactsActivity;
import com.jesusrojo.dagger2demo.smartphone.model.Battery;
import com.jesusrojo.dagger2demo.smartphone.model.SmartPhone;

import javax.inject.Inject;


public class SmartPhoneActivity extends AppCompatActivity {

    @Inject //2
    SmartPhone smartPhone;
    @Inject Battery battery;//2 we can add more if we need

    private TextView tv;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.smartphone_activity_layout);
        initUi();

        // HAND INJECTION
        //smartPhone = HandInjection.getInstanceSmartPhone();

        // DAGGER2
        //SmartPhoneComponent smartPhoneComponent = DaggerSmartPhoneComponent.create();
//        SmartPhoneComponent smartPhoneComponent = DaggerSmartPhoneComponent.builder()
//                .memoryCardModule(new MemoryCardModule(100))
//                .build();
        //INJECTION FROM Application class
//       MyApp.getMyApp().getSmartPhoneComponent().inject(this);
       MyApp.getMyApp().getMyAppComponent().inject(this);
        //1
        //smartPhone = smartPhoneComponent.getSmartPhone();
        //2 use injector
//        smartPhoneComponent.inject(this);

        //
        Log.d("##", "**********");
        String textUi = smartPhone.makeACall();
        textUi += "\n" + smartPhone.getBatteryType();
        //2
        textUi += "\nInjected " + battery.showType();
        if (tv != null) tv.setText(textUi);
    }

    private void initUi() {
        tv = findViewById(R.id.tv_main);
        Button btn= findViewById(R.id.btn_second_activity);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SmartPhoneActivity.this, SecondActivity.class);
                startActivity(i);
            }
        });
        Button btn2= findViewById(R.id.btn_contacts_activity);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SmartPhoneActivity.this,
                        ContactsActivity.class);
                startActivity(i);
            }
        });
        Button btn3= findViewById(R.id.btn_books_activity);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SmartPhoneActivity.this,
                        BooksActivity.class);
                startActivity(i);
            }
        });
    }
}
