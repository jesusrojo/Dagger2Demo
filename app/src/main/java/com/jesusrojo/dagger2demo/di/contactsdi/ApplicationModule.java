//package com.jesusrojo.dagger2demo.di.contactsdi;
//
//import android.app.Application;
//
//import javax.inject.Singleton;
//
//import dagger.Module;
//import dagger.Provides;
//
//@Module
//public class ApplicationModule {
//    private final Application application;
//
//    public ApplicationModule(Application application) {
//        this.application = application;
//    }
//
//    @Provides
//    @Singleton
//    Application providesApplication(){
//        return application;
//    }
//}