package com.example.ramesh.myapplication.database.com.example.ramesh.model;

/**
 * Created by prem on 5/8/17.
 */

public class DataModel {
    public  int billNo ;
   public String date;
    public  double price;


    public DataModel() {

    }

    public DataModel(int billNo, String date, double price) {
        this.billNo = billNo;
        this.date = date;
        this.price = price;

    }

    public int getBillNo() {
        return billNo;
    }

    public void setBillNo(int billNo) {
        this.billNo = billNo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
