package com.intelliatech.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String cityName;
    private String cityZipcode;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityZipcode() {
        return cityZipcode;
    }

    public void setCityZipcode(String cityZipcode) {
        this.cityZipcode = cityZipcode;
    }


    public Address(int id, String cityName, String cityZipcode) {
        this.id = id;
        this.cityName = cityName;
        this.cityZipcode = cityZipcode;
    }

    public Address() {
    }


    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", cityName='" + cityName + '\'' +
                ", cityZipcode='" + cityZipcode + '\'' +
                '}';
    }
}
