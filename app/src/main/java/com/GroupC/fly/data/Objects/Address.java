package com.GroupC.fly.data.Objects;

/**
 * This class handles the address data type that will be users for regular users as well as pages.
 *
 * **/
public class Address {

    /** Data Members **/
    private String country, region, city, street, streetNumber;


    /** Methods **/
    public Address(String country, String region, String city, String street, String streetNumber){
        this.country = country;
        this.region = region;
        this.city = city;
        this.street = street;
        this.streetNumber = streetNumber;
    }
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
        return streetNumber;
    }

    public void set_streetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }
}
