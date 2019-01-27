package com.company;

import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Address {
    public Address(){}
    public Address(String street, int zipCode, int homeNumber){
        this.street = street;
        this.zipCode = zipCode;
        this.homeNumber = homeNumber;
    }

    @XmlElement
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @XmlElement
    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    @XmlElement
    public int getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(int homeNumber) {
        this.homeNumber = homeNumber;
    }

    private String street;
    private int zipCode;
    private int homeNumber;

    @Override
    public String toString() {
        return street + ", zip code: " + zipCode + ", home number: " + homeNumber;
    }
}
