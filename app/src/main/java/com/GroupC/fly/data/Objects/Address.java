package com.GroupC.fly.data.Objects;

/**
 * This class handles the address data type that will be users for regular users as well as pages.
 *
 * **/



public class Address {

    /* Data Members */
    private String country, region, city, street, street_number;


    /* Functions */

    public Address(){}
    public Address(String country, String city)
    {
        this.country = country;
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet_number() {
        return street_number;
    }

    public void setStreet_number(String street_number) {
        this.street_number = street_number;
    }
}
