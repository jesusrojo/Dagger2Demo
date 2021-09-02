package com.jesusrojo.dagger2demo;

import android.app.Application;

import com.jesusrojo.dagger2demo.di.DaggerMyAppComponent;
import com.jesusrojo.dagger2demo.di.MyAppComponent;
import com.jesusrojo.dagger2demo.di.MyAppModule;
import com.jesusrojo.dagger2demo.di.smartphonedi.MemoryCardModule;

public class MyApp extends Application {
    private static MyApp myApp;
    private MyAppComponent myAppComponent;
//    private SmartPhoneComponent smartPhoneComponent;
//    private ContactAppComponent contactAppComponent;

    public static MyApp getMyApp() {
        return myApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;

        myAppComponent = DaggerMyAppComponent.builder()
                .memoryCardModule(new MemoryCardModule(100))
                .myAppModule(new MyAppModule(this))
                .build();

//        myAppComponent = DaggerMyAppComponent.builder()
//                .memoryCardModule(new MemoryCardModule(100))
//                .applicationModule(new ApplicationModule(this))
//                .build();

//        smartPhoneComponent = DaggerSmartPhoneComponent.builder()
//                .memoryCardModule(new MemoryCardModule(100))
//                .build();
//
//        contactAppComponent = DaggerContactAppComponent.builder()
//                .applicationModule(new ApplicationModule(this))
//                .build();
    }


    public static MyApp getApp() {
        return myApp;
    }

    public MyAppComponent getMyAppComponent() {
        return myAppComponent;
    }
//    public SmartPhoneComponent getSmartPhoneComponent() {
//        return smartPhoneComponent;
//    }
//
//    public ContactAppComponent getContactAppComponent() {
//        return contactAppComponent;
//    }
}
