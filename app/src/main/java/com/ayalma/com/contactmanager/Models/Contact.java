package com.ayalma.com.contactmanager.Models;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ayalma.com.contactmanager.DataBase.CouchBase;
import com.ayalma.com.contactmanager.DataBase.Snappy;
import com.ayalma.com.contactmanager.DataBase.Sqlite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ali on 11/7/15.
 */
public class Contact implements ModelUtils.DbCrudInterface{


    public static final String KEY_ID = "id";
    public static final String  KEY_FIRSTNAME = "firstName";
    public static final String KEY_LASTNAME = "lastName";
    public static String KEY_IMAGE = "image";
    public static String KEY_EMAIL = "email";
    public static String KEY_DESCRIPTION = "description";
    public static final String TABLE_CONTACTS = "contacts";


    private int id;
    private String firstName;
    private String lastName;
    private List<Number> numbers;
    private Address address;
    private byte[] image;
    private String email;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Number> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Number> numbers) {
        this.numbers = numbers;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public int Insert(Object o) {

        try {


            if (o instanceof Sqlite){

                SQLiteDatabase database = (SQLiteDatabase)((Sqlite) o).GetDataBase();
                ContentValues values = new ContentValues();
                values.put(KEY_FIRSTNAME, getFirstName());
                values.put(KEY_LASTNAME , getLastName());
                values.put(KEY_EMAIL , getEmail());
                values.put(KEY_DESCRIPTION , getDescription());
                values.put(KEY_IMAGE , getImage());

                long AddressId = database.insert(TABLE_CONTACTS, null, values);
                return (int) AddressId;
            }

            else if (o instanceof CouchBase){

                return 1;
            }
            else if (o instanceof Snappy){

                return 1;
            }
            return 1;

        }catch (Exception e)
        {
            return -1;
        }
    }

    @Override
    public boolean Update(Object o) {
        try {

            if (o instanceof Sqlite){

                SQLiteDatabase database = (SQLiteDatabase) ((Sqlite) o).GetDataBase();


                ContentValues values = new ContentValues();
                values.put(KEY_FIRSTNAME, getFirstName());
                values.put(KEY_LASTNAME , getLastName());
                values.put(KEY_EMAIL , getEmail());
                values.put(KEY_DESCRIPTION , getDescription());
                values.put(KEY_IMAGE , getImage());

                database.update(TABLE_CONTACTS, values, KEY_ID+" = ?", new String[]{String.valueOf(this.getId())});
                database.close();

            }

            else if (o instanceof CouchBase){

                return true;
            }
            else if (o instanceof Snappy){

                return true;
            }
            return true;

        }catch (Exception e)
        {
            return false;
        }

    }

    @Override
    public Object Get(Object o , int contactId) {

        try {

            if (o instanceof Sqlite){

                SQLiteDatabase database = (SQLiteDatabase) ((Sqlite) o).GetDataBase();

                String Query = "SELECT " +
                        KEY_ID+"," +
                        KEY_FIRSTNAME+" ," +
                        KEY_LASTNAME+" ," +
                        KEY_EMAIL+", " +
                        KEY_DESCRIPTION+" ," +
                        KEY_IMAGE+" ," +

                        " From " +
                        TABLE_CONTACTS

                        +" WHERE " +
                        KEY_ID+"= ?";


                Cursor cursor = database.rawQuery(Query, new String[]{String.valueOf(contactId)});

                if (cursor.moveToFirst()) {
                    do {
                        setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                        setFirstName(cursor.getString(cursor.getColumnIndex(KEY_FIRSTNAME)));
                        setLastName(cursor.getString(cursor.getColumnIndex(KEY_LASTNAME)));
                        setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL)));
                        setDescription(cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION)));
                        setImage(cursor.getBlob(cursor.getColumnIndex(KEY_IMAGE))); //todo get byte array problem
                        Number number = new Number();
                        setNumbers((List<Number>)(List<?>)number.GetAllNumbersByContactId(new Sqlite()  , getId()));   //todo here is problem cast Problem
                        setAddress(address.GetContactAddress(new Sqlite(), getId()));

                    } while (cursor.moveToNext());
                }

                cursor.close();
                database.close();

                return this;
            }

            else if (o instanceof CouchBase){

                return true;
            }
            else if (o instanceof Snappy){

                return true;
            }
            return true;

        }catch (Exception e)
        {
            return false;
        }
    }

    @Override
    public List<Object> GetAll(Object o) {
        try {

            if (o instanceof Sqlite){

                SQLiteDatabase database = (SQLiteDatabase) ((Sqlite) o).GetDataBase();

                String Query = "SELECT " +
                        KEY_ID+"," +
                        KEY_FIRSTNAME+" ," +
                        KEY_LASTNAME+" ," +
                        KEY_EMAIL+", " +
                        KEY_DESCRIPTION+" ," +
                        KEY_IMAGE+" ," +

                        " From " +
                        TABLE_CONTACTS;

                List<Contact> contacts = new ArrayList<>();
                Cursor cursor = database.rawQuery(Query, new String[]{String.valueOf(this.getId())});

                if (cursor.moveToFirst()) {
                    do {
                        Contact contact =new Contact();
                        contact.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                        contact.setFirstName(cursor.getString(cursor.getColumnIndex(KEY_FIRSTNAME)));
                        contact.setLastName(cursor.getString(cursor.getColumnIndex(KEY_LASTNAME)));
                        contact.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL)));
                        contact.setDescription(cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION)));
                        contact.setImage(cursor.getBlob(cursor.getColumnIndex(KEY_IMAGE))); //todo get byte array problem
                        Number number = new Number();
                        contact.setNumbers((List<Number>)(List<?>)number.GetAllNumbersByContactId(new Sqlite(), getId()));   //todo here is problem cast Problem
                        contact.setAddress(address.GetContactAddress(new Sqlite(), getId()));

                        contacts.add(contact);

                    } while (cursor.moveToNext());
                }

                cursor.close();
                database.close();
                return (List<Object>)(List<?>)contacts; //todo here is problem
            }

            else if (o instanceof CouchBase){

                return null;
            }
            else if (o instanceof Snappy){

                return null;
            }
            return null;

        }catch (Exception e)
        {
            return null;
        }
    }

    @Override
    public boolean Delete(Object o) {

        try {

            if (o instanceof Sqlite){


                SQLiteDatabase database = (SQLiteDatabase) ((Sqlite) o).GetDataBase();
                database.delete(TABLE_CONTACTS, KEY_ID+" = ?", new String[]{String.valueOf(this.getId())});
                database.close();


            }

            else if (o instanceof CouchBase){

                return true;
            }
            else if (o instanceof Snappy){

                return true;
            }
            return true;

        }catch (Exception e)
        {
            return false;
        }
    }
}
