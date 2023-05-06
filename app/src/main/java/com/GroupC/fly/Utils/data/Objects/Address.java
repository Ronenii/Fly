package com.GroupC.fly.Utils.data.Objects;

import androidx.annotation.NonNull;

/**
 * Class for 'Address' data type-
 * contains Country and City.
 **/
public class Address {

    /** Data Members **/
    private String country, city;


    /** Methods **/

    // Address c'tor.
    public Address(String country, String city){
        this.country = country;
        this.city = city;
    }

    @NonNull
    @Override
    public String toString()
    {
        return city + ", " + country;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
