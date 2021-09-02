package com.jesusrojo.dagger2demo.di;

import com.jesusrojo.dagger2demo.contacts.ContactsActivity;
import com.jesusrojo.dagger2demo.di.contactsdi.RoomModule;
import com.jesusrojo.dagger2demo.di.ebookdi.RepositoryModule;
import com.jesusrojo.dagger2demo.di.smartphonedi.NCBatteryModule;
import com.jesusrojo.dagger2demo.bookapp.BooksActivity;
import com.jesusrojo.dagger2demo.smartphone.SmartPhoneActivity;
import com.jesusrojo.dagger2demo.di.smartphonedi.MemoryCardModule;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {MemoryCardModule.class, NCBatteryModule.class,
        MyAppModule.class, RoomModule.class,
        RepositoryModule.class})
public interface MyAppComponent {

    void inject(BooksActivity booksActivity);

    void inject(SmartPhoneActivity smartPhoneActivity);

    void inject(ContactsActivity contactsActivity);
}


//////////////////////// SmartPhone and Contacts
//@Singleton
//@Component(modules = {MemoryCardModule.class, NCBatteryModule.class,
//        ApplicationModule.class, RoomModule.class})
//public interface MyAppComponent {
//
//    void inject(SmartPhoneActivity smartPhoneActivity);
//
//    void inject(ContactsActivity contactsActivity);
//}