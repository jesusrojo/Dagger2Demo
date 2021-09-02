package com.jesusrojo.dagger2demo.di.contactsdi;


import android.app.Application;

import androidx.room.Room;

import com.jesusrojo.dagger2demo.contacts.db.ContactsAppDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {

    @Provides
    @Singleton
    ContactsAppDatabase provideContactsAppDatabase(Application application){
        return Room.databaseBuilder(application,
                ContactsAppDatabase.class,"ContactDB")
                .build();
    }
}