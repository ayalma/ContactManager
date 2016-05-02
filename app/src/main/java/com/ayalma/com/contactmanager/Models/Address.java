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
public class Address implements ModelUtils.DbCrudInterface{




    public static final String KEY_ID = "id";
    public static final String KEY_CITY = "city";
    public static final String KEY_ALLEY = "Alley";
    public static final String KEY_STREET = "Street";
    public static final String KEY_TRACK = "track";
    public static final String KEY_CONTACTID = "contactId";
    public static final String TABLE_ADDRESSS = "addresss";

    private int id;
    private String city;
    private String Alley;
    private String Street;
    private String track;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAlley() {
        return Alley;
    }

    public void setAlley(String alley) {
        Alley = alley;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }


    public int Insert(Object o ,int contactId) {


       try {


           if (o instanceof Sqlite){

               SQLiteDatabase database = (SQLiteDatabase)((Sqlite) o).GetDataBase();
               ContentValues values = new ContentValues();
               values.put(KEY_CITY, this.getCity());
               values.put(KEY_ALLEY , this.getAlley());
               values.put(KEY_STREET , this.getStreet());
               values.put(KEY_TRACK , this.getTrack());
               values.put(KEY_CONTACTID , contactId);

               long AddressId = database.insert(TABLE_ADDRESSS, null, values);
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
                values.put(KEY_CITY, this.getCity());
                values.put(KEY_ALLEY , this.getAlley());
                values.put(KEY_STREET , this.getStreet());
                values.put(KEY_TRACK , this.getTrack());

                database.update(TABLE_ADDRESSS, values, KEY_ID+" = ?", new String[]{String.valueOf(this.getId())});
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
    public Object Get(Object o , int addressId) {



        try {

            if (o instanceof Sqlite){

                SQLiteDatabase database = (SQLiteDatabase) ((Sqlite) o).GetDataBase();

                String Query = "SELECT " +
                        KEY_ID+"," +
                        KEY_CITY+" ," +
                        KEY_ALLEY+" ," +
                        KEY_STREET+", " +
                        KEY_TRACK+" ," +

                        " From " +
                        TABLE_ADDRESSS
                        +" WHERE " +
                        KEY_ID+"= ?";

                Address address = new Address();
                Cursor cursor = database.rawQuery(Query, new String[]{String.valueOf(addressId)});

                if (cursor.moveToFirst()) {
                    do {
                        address.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                        address.setCity(cursor.getString(cursor.getColumnIndex(KEY_CITY)));
                        address.setAlley(cursor.getString(cursor.getColumnIndex(KEY_ALLEY)));
                        address.setStreet(cursor.getString(cursor.getColumnIndex(KEY_STREET)));
                        address.setTrack(cursor.getString(cursor.getColumnIndex(KEY_TRACK)));

                    } while (cursor.moveToNext());
                }

                cursor.close();
                database.close();

                return address;
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
                        KEY_CITY+" ," +
                        KEY_ALLEY+" ," +
                        KEY_STREET+", " +
                        KEY_TRACK+" ," +

                        " From " +
                        TABLE_ADDRESSS +
                        " WHERE " +
                        KEY_CONTACTID+"= ?";

                List<Address> addresses = new ArrayList<>();
                Cursor cursor = database.rawQuery(Query, new String[]{String.valueOf(this.getId())});

                if (cursor.moveToFirst()) {
                    do {
                        Address address = new Address();
                        address.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                        address.setCity(cursor.getString(cursor.getColumnIndex(KEY_CITY)));
                        address.setAlley(cursor.getString(cursor.getColumnIndex(KEY_ALLEY)));
                        address.setStreet(cursor.getString(cursor.getColumnIndex(KEY_STREET)));
                        address.setTrack(cursor.getString(cursor.getColumnIndex(KEY_TRACK)));

                        addresses.add(address);

                    } while (cursor.moveToNext());
                }

                cursor.close();
                database.close();
                return (List<Object>)(List<?>)addresses; //todo here is problem
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
                database.delete(TABLE_ADDRESSS, KEY_ID+" = ?", new String[]{String.valueOf(this.getId())});
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


    public Address GetContactAddress(Object o, int contactId) {



        try {

            if (o instanceof Sqlite){

                SQLiteDatabase database = (SQLiteDatabase) ((Sqlite) o).GetDataBase();

                String Query = "SELECT " +
                        KEY_ID+"," +
                        KEY_CITY+" ," +
                        KEY_ALLEY+" ," +
                        KEY_STREET+", " +
                        KEY_TRACK+" ," +

                        " From " +
                        TABLE_ADDRESSS +
                        " WHERE " +
                        KEY_CONTACTID+"= ?";
                Address address = new Address();

                Cursor cursor = database.rawQuery(Query, new String[]{String.valueOf(contactId)});

                if (cursor.moveToFirst()) {
                    do {

                        address.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                        address.setCity(cursor.getString(cursor.getColumnIndex(KEY_CITY)));
                        address.setAlley(cursor.getString(cursor.getColumnIndex(KEY_ALLEY)));
                        address.setStreet(cursor.getString(cursor.getColumnIndex(KEY_STREET)));
                        address.setTrack(cursor.getString(cursor.getColumnIndex(KEY_TRACK)));



                    } while (cursor.moveToNext());
                }

                cursor.close();
                database.close();
                return address;
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
    public int Insert(Object dbObject) {
        return 0;
    }

}
