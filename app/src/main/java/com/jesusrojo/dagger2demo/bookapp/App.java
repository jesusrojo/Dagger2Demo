//package com.jesusrojo.dagger2demo.ebook;
//
//import android.app.Application;
//
//
//public class App extends Application {
//
//    private  static App app;
//    private EBookShopComponent eBookShopComponent;
//
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        app = this;
//
//        eBookShopComponent = DaggerEBookShopComponent
//        .builder()
//        .appModule(new AppModule(this))
//        .build();
//    }
//
//    public static App getApp() {
//        return app;
//    }
//
//    public EBookShopComponent geteBookShopComponent() {
//        return eBookShopComponent;
//    }
//}
