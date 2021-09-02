package com.jesusrojo.dagger2demo.contacts.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jesusrojo.dagger2demo.R;
import com.jesusrojo.dagger2demo.contacts.ContactsActivity;
import com.jesusrojo.dagger2demo.contacts.db.entity.Contact;

import java.util.ArrayList;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Contact> contactssList;
    private ContactsActivity contactsActivity;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView emil;


        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            emil = view.findViewById(R.id.email);

        }
    }


    public ContactsAdapter(Context context, ArrayList<Contact> contacts, ContactsActivity contactsActivity) {
        this.context = context;
        this.contactssList = contacts;
        this.contactsActivity = contactsActivity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item_layout, parent, false);

        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,
                                 @SuppressLint("RecyclerView") final int position) {


        final Contact contact = contactssList.get(position);

        holder.name.setText(contact.getName());
        holder.emil.setText(contact.getEmail());

        holder.itemView.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                contactsActivity.addAndEditContacts(true, contact, position);
            }
        });

    }

    @Override
    public int getItemCount() {

        return contactssList.size();
    }


}

