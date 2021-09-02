package com.jesusrojo.dagger2demo.contacts.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.jesusrojo.dagger2demo.contacts.db.entity.Contact;

@Database(entities = {Contact.class},version = 1)
public abstract class ContactsAppDatabase extends RoomDatabase {

    public abstract ContactDAO getContactDAO();

}
