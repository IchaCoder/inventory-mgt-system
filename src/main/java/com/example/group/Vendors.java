package com.example.group;

public class Vendors {
    public String customerName;
    public int id;

    public Vendors(String customerName, int id) {
        this.customerName = customerName;
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getId() {
        return id;
    }
}
