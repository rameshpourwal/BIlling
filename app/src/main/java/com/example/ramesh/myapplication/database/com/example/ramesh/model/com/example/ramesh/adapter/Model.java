package com.example.ramesh.myapplication.database.com.example.ramesh.model.com.example.ramesh.adapter;

/**
 * Created by prem on 4/8/17.
 */

public class Model {

    String name ;
    int qnty;
    long id;

    public Model(int qnty) {
        this.qnty = qnty;
    }

    public Model(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    double price ,amount;

    public Model(String name, int qnty, long id, double price, double amount) {
        this.name = name;
        this.qnty = qnty;
        this.id = id;
        this.price = price;
        this.amount = amount;
    }

    public Model() {
    }

    public Model(String name, double price, double amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public Model(String name, int qnty, double price, double amount) {
        this.name = name;
        this.qnty = qnty;
        this.price = price;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQnty() {
        return qnty;
    }

    public void setQnty(int qnty) {
        this.qnty = qnty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}

