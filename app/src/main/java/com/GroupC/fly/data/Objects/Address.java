package com.GroupC.fly.data.Objects;

/**
 * This class handles the address data type that will be users for regular users as well as pages.
 *
 * **/



public class Address {

    /* Data Members */
    private String m_country, m_region, m_city, m_street, m_street_number;


    /* Functions */

    // Address c'tor.
    public Address(String i_country, String i_region, String i_city, String i_street, String i_streetNumber){
        m_country = i_country;
        m_region = i_region;
        m_city = i_city;
        m_street = i_street;
        m_street_number = i_streetNumber;
    }
    public Address(String i_country, String i_city)
    {
        this.m_country = i_country;
        this.m_city = i_city;
    }

    public String getCountry() {
        return m_country;
    }

    public void setCountry(String i_country) {
        this.m_country = i_country;
    }

    public String getRegion() {
        return m_region;
    }

    public void setRegion(String i_region) {
        this.m_region = i_region;
    }

    public String getCity() {
        return m_city;
    }

    public void setCity(String i_city) {
        this.m_city = i_city;
    }

    public String getStreet() {
        return m_street;
    }

    public void setStreet(String i_street) {
        this.m_street = i_street;
    }

    public String getStreet_number() {
        return m_street_number;
    }

    public void set_streetNumber(String i_streetNumber) {
        this.m_street_number = i_streetNumber;
    }
}
