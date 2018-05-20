package com.example.tomas.androidgraduationproject;

/**
 * Created by Tomas on 5/9/2018.
 */

public class Messages {

    private String Number;
    private String Name;
    private String Cost;
    private String Street;
    private String CID;

    public Messages(String number, String name, String cost, String street, String CID) {
        Number = number;
        Name = name;
        Cost = cost;
        Street = street;
        this.CID = CID;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCost() {
        return Cost;
    }

    public void setCost(String cost) {
        Cost = cost;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getCID() {
        return CID;
    }

    public void setCID(String CID) {
        this.CID = CID;
    }





}