package com.example.yash.onlinedatabaseapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yash on 4/12/16.
 */

public class  ContactAdapter extends ArrayAdapter
{
    //List to hold the objects of contacts
    List list = new ArrayList();

    //Add a constructor
    public ContactAdapter(Context context, int resource)
    {
        super(context, resource);
    }


    public void add(Contacts object) {
       // super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }



    @Override
    public Contacts getItem(int position) {
        return (Contacts) list.get(position);
    }


    //This method returns the view then given its position and parent in contactAdapter
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View row;
        row = convertView;
        //object of static class
        ContactHolder contactHolder;

        //Check whether the row exists

        if(row == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout,parent,false);
            contactHolder = new ContactHolder();
            contactHolder.tx_name = (TextView) row.findViewById(R.id.tx_name);
            contactHolder.tx_email = (TextView) row.findViewById(R.id.tx_email);
            contactHolder.tx_mobile = (TextView) row.findViewById(R.id.tx_mobile);
            row.setTag(contactHolder);

        }

        else
        {
            contactHolder = (ContactHolder) row.getTag();

        }


        Contacts contacts = (Contacts)this.getItem(position);
        //Setting up the values in the inflated layout
        contactHolder.tx_name.setText(contacts.getName());
        contactHolder.tx_email.setText(contacts.getEmail());
        contactHolder.tx_mobile.setText(contacts.getMobile());

        return row;

    }

    static class ContactHolder
    {
         TextView tx_name, tx_email, tx_mobile;


    }
}
