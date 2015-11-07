package com.ayalma.com.contactmanager.Models;

/**
 * Created by ali on 11/7/15.
 */
public class Address {

    private String city;
    private String Alley;
    private String Street;
    private String track;


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
}
