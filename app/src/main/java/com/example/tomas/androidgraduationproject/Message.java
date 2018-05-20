package com.example.tomas.androidgraduationproject;

/**
 * Created by Tomas on 5/9/2018.
 */

public class Message {

    private String Name;
    private String Cost;
    private String Street;

    public Message(String Name, String Cost,  String Street) {
        this.Name = Name;
        this.Cost = Cost;
        this.Street = Street;
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
}