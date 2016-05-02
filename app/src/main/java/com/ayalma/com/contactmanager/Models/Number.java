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
public class Number implements ModelUtils.DbCrudInterface{

    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_PH_NO = "phonenumber";
    public static final String KEY_CONTACT_ID = "contactId";
    public static final String TABLE_NUMBERS = "numbers";


    private int id;
    private String name;
    private String phoneNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int Insert(Object dbObject) {
        return 0;
    }



    public int Insert(Object o , int contactId){

        try {

            if (o instanceof Sqlite){

                SQLiteDatabase database = (SQLiteDatabase)((Sqlite) o).GetDataBase();
                ContentValues values = new ContentValues();
                values.put(KEY_NAME, this.getName());
                values.put(KEY_PH_NO , this.getPhoneNumber());
                values.put(KEY_CONTACT_ID , contactId);

                long AddressId = database.insert(TABLE_NUMBERS, null, values);
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
                values.put(KEY_NAME, this.getName());
                values.put(KEY_PH_NO , this.getPhoneNumber());
//              values.put(KEY_CONTACT_ID , contactId);

                database.update(TABLE_NUMBERS, values, KEY_ID+" = ?", new String[]{String.valueOf(this.getId())});
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
    public Object Get(Object o , int numberId) {

        try {

            if (o instanceof Sqlite){

                SQLiteDatabase database = (SQLiteDatabase) ((Sqlite) o).GetDataBase();

                String Query = "SELECT " +
                        KEY_ID+"," +
                        KEY_NAME+" ," +
                        KEY_PH_NO+" ," +

                        " From " +
                        TABLE_NUMBERS
                        +" WHERE " +
                        KEY_ID+"= ?";

                //Number number = new Number();
                Cursor cursor = database.rawQuery(Query, new String[]{String.valueOf(this.getId())});

                if (cursor.moveToFirst()) {

                    do {
                        setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                        setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                        setPhoneNumber(cursor.getString(cursor.getColumnIndex(KEY_PH_NO)));
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
                        KEY_NAME+" ," +
                        KEY_PH_NO+" ," +

                        " From " +
                        TABLE_NUMBERS;





                List<Number> numbers = new ArrayList<>();
                Cursor cursor = database.rawQuery(Query, null);

                if (cursor.moveToFirst()) {
                    do {
                        Number number = new Number();
                        number.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                        number.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                        number.setPhoneNumber(cursor.getString(cursor.getColumnIndex(KEY_PH_NO)));
                        numbers.add(number);

                    } while (cursor.moveToNext());
                }

                cursor.close();
                database.close();
                return (List<Object>)(List<?>)numbers;
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
                database.delete(TABLE_NUMBERS, KEY_ID+"= ?", new String[]{String.valueOf(this.getId())});
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
    public List<Object> GetAllNumbersByContactId(Object o , int contactId) {



        try {

            if (o instanceof Sqlite){

                SQLiteDatabase database = (SQLiteDatabase) ((Sqlite) o).GetDataBase();
                String Query = "SELECT " +
                        KEY_ID+"," +
                        KEY_NAME+" ," +
                        KEY_PH_NO+" ," +
                        " From " +
                        TABLE_NUMBERS
                        +" WHERE " +
                        KEY_CONTACT_ID+"= ?";

                List<Number> numbers = new ArrayList<>();
                Cursor cursor = database.rawQuery(Query, new String[]{String.valueOf(contactId)});

                if (cursor.moveToFirst()) {
                    do {
                        Number number = new Number();
                        number.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                        number.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                        number.setPhoneNumber(cursor.getString(cursor.getColumnIndex(KEY_PH_NO)));
                        numbers.add(number);

                        numbers.add(number);

                    } while (cursor.moveToNext());
                }

                cursor.close();
                database.close();
                return (List<Object>)(List<?>)numbers;
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
}
