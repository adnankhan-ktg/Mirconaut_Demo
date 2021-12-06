package com.intelliatech.bean;


import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String userName;

    private int userAge;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Address address;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    //Constructors
    public User(int userId, String userName, int userAge, Address address) {
        this.userId = userId;
        this.userName = userName;
        this.userAge = userAge;
        this.address = address;
    }


    public User(int userId, String userName, int userAge) {
        this.userId = userId;
        this.userName = userName;
        this.userAge = userAge;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userAge=" + userAge +
                ", address=" + address +
                '}';
    }
}
