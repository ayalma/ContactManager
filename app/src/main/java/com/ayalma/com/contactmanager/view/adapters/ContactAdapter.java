package com.ayalma.com.contactmanager.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ayalma.com.contactmanager.Models.Contact;
import com.ayalma.com.contactmanager.R;

import java.util.List;

import carbon.widget.RecyclerView;

/**
 * Created by alimohammadi on 11/13/15.
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyHoder,String> {

    private List<Contact> rowItems;

    public List<Contact> getRowItems() {
        return rowItems;
    }

    public void setRowItems(List<Contact> rowItems) {
        this.rowItems = rowItems;
    }

    public ContactAdapter(List<Contact> rowItems) {
        this.rowItems = rowItems;
    }

    @Override
    public String getItem(int position) {
        return "";
    }

    @Override
    public MyHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_setting_recycler_row,parent,false);
        return new MyHoder(rootView);
    }

    @Override
    public void onBindViewHolder(final MyHoder holder, final int position) {
        super.onBindViewHolder(holder, position);



    }
    @Override
    public int getItemCount() {
        return rowItems.size();
    }

    public class MyHoder extends RecyclerView.ViewHolder{


        public MyHoder(View itemView) {
            super(itemView);
        }
    }
}