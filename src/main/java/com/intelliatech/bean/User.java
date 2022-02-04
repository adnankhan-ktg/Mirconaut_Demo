package com.intelliatech.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "mobile_number",unique = true)
    private String mobileNumber;
    @Column(name = "user_age")
    private int userAge;
    @Column(name = "status")
    private String status;
    @Transient
    @JsonSerialize
    @JsonDeserialize
    @JsonProperty("hello")
    private Integer serialNumber;

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User(int id, String userName, String mobileNumber, int userAge, String status) {
        this.id = id;
        this.userName = userName;
        this.mobileNumber = mobileNumber;
        this.userAge = userAge;
        this.status = status;
    }

    public User(int id, String userName, String mobileNumber, int userAge) {
        this.id = id;
        this.userName = userName;
        this.mobileNumber = mobileNumber;
        this.userAge = userAge;
    }

    public User(String userName, String mobileNumber) {
        this.userName = userName;
        this.mobileNumber = mobileNumber;
    }

    public User(int id, String userName, String mobileNumber, int userAge, String status, Integer serialNumber) {
        this.id = id;
        this.userName = userName;
        this.mobileNumber = mobileNumber;
        this.userAge = userAge;
        this.status = status;
        this.serialNumber = serialNumber;
    }

    public User() {
    }
}
