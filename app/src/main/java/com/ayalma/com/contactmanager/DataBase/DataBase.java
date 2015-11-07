package com.ayalma.com.contactmanager.DataBase;

import java.util.List;

import com.ayalma.com.contactmanager.Models.Contact;

/**
 * Created by ali on 11/7/15.
 */
public interface DataBase {


    public int Insert(Contact contact);
    public boolean Delete(int ContactIc);
    public Contact GetContact(int contactId);
    public boolean UpdateContact(Contact contact);
    public List<Contact> GetContacts();
    public  int[] InsertContacts(List<Contact> contacts);

}
