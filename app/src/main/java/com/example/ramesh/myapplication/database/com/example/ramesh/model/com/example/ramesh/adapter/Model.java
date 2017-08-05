package com.example.ramesh.myapplication.database.com.example.ramesh.model.com.example.ramesh.adapter;

/**
 * Created by prem on 4/8/17.
 */

public class Model {

    int quantity;
    double amount;

    public Model(int quantity, double amount) {
        this.quantity = quantity;
        this.amount = amount;
    }

    public Model() {
    }

    public int getQuantity() {
        return quantity;
    }

    public double getAmount() {
        return amount;

    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}

