package com.jesusrojo.dagger2demo.contacts;


import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jesusrojo.dagger2demo.MyApp;
import com.jesusrojo.dagger2demo.R;
import com.jesusrojo.dagger2demo.contacts.adapter.ContactsAdapter;
import com.jesusrojo.dagger2demo.contacts.db.ContactsAppDatabase;
import com.jesusrojo.dagger2demo.contacts.db.entity.Contact;

import java.util.ArrayList;

import javax.inject.Inject;

public class ContactsActivity extends AppCompatActivity {
    @Inject
    public ContactsAppDatabase contactsAppDatabase;

    private ContactsAdapter contactsAdapter;
    private final ArrayList<Contact> contactArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts_activity_layout);
        MyApp.getMyApp().getMyAppComponent().inject(this);
//        MyApp.getApp().getContactAppComponent().inject(this);
        //contactsAppDatabase= Room.databaseBuilder(getApplicationContext(),ContactsAppDatabase.class,"ContactDB").build();

        initUi();

        new GetAllContactsAsyncTask().execute();
    }

    private void initUi() {

        RecyclerView recyclerView = findViewById(R.id.recycler_view_contacts);

        contactsAdapter = new ContactsAdapter(this, contactArrayList, ContactsActivity.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(contactsAdapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addAndEditContacts(false, null, -1);
            }
        });
    }

    public void addAndEditContacts(final boolean isUpdate, final Contact contact, final int position) {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getApplicationContext());
        View view = layoutInflaterAndroid.inflate(R.layout.contact_add_layout, null);

        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(ContactsActivity.this);
        alertDialogBuilderUserInput.setView(view);

        TextView contactTitle = view.findViewById(R.id.new_contact_title);
        final EditText newContact = view.findViewById(R.id.name);
        final EditText contactEmail = view.findViewById(R.id.email);

        contactTitle.setText(!isUpdate ? "Add New Contact" : "Edit Contact");

        if (isUpdate && contact != null) {
            newContact.setText(contact.getName());
            contactEmail.setText(contact.getEmail());
        }

        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton(isUpdate ? "Update" : "Save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                    }
                })
                .setNegativeButton("Delete",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                if (isUpdate) {
                                    deleteContact(contact, position);
                                } else {
                                    dialogBox.cancel();
                                }
                            }
                        });


        final AlertDialog alertDialog = alertDialogBuilderUserInput.create();
        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if (TextUtils.isEmpty(newContact.getText().toString())) {
                    Toast.makeText(ContactsActivity.this, "Enter contact name!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    alertDialog.dismiss();
                }
                if (isUpdate && contact != null) {
                    updateContact(newContact.getText().toString(), contactEmail.getText().toString(), position);
                } else {
                    createContact(newContact.getText().toString(), contactEmail.getText().toString());
                }
            }
        });
    }

    private void deleteContact(Contact contact, int position) {
        contactArrayList.remove(position);
        new DeleteContactAsyncTask().execute(contact);
    }

    private void updateContact(String name, String email, int position) {
        Contact contact = contactArrayList.get(position);
        contact.setName(name);
        contact.setEmail(email);

        new UpdateContactAsyncTask().execute(contact);
        contactArrayList.set(position, contact);
    }

    private void createContact(String name, String email) {
        new CreateContactAsyncTask().execute(new Contact(0, name, email));
    }

    private class GetAllContactsAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override protected Void doInBackground(Void... voids) {
            contactArrayList.addAll(contactsAppDatabase.getContactDAO().getContacts());
            return null;
        }

        @Override protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            contactsAdapter.notifyDataSetChanged();
        }
    }


    private class CreateContactAsyncTask extends AsyncTask<Contact, Void, Void> {

        @Override protected Void doInBackground(Contact... contacts) {
            long id = contactsAppDatabase.getContactDAO().addContact(contacts[0]);
            Contact contact = contactsAppDatabase.getContactDAO().getContact(id);
            if (contact != null) {
                contactArrayList.add(0, contact);
            }
            return null;
        }

        @Override protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            contactsAdapter.notifyDataSetChanged();
        }
    }

    private class UpdateContactAsyncTask extends AsyncTask<Contact, Void, Void> {

        @Override protected Void doInBackground(Contact... contacts) {
            contactsAppDatabase.getContactDAO().updateContact(contacts[0]);
            return null;
        }

        @Override protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            contactsAdapter.notifyDataSetChanged();
        }
    }

    private class DeleteContactAsyncTask extends AsyncTask<Contact, Void, Void> {

        @Override protected Void doInBackground(Contact... contacts) {
            contactsAppDatabase.getContactDAO().deleteContact(contacts[0]);
            return null;
        }
        @Override protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            contactsAdapter.notifyDataSetChanged();
        }
    }
}
