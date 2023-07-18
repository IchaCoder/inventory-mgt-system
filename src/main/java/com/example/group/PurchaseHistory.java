package com.example.group;

public class PurchaseHistory {
    public String drugPurchased, customerName;
    public int quantity;
    public double amount;

    public PurchaseHistory(String drugPurchased, String customerName, int quantity, double amount) {
        this.drugPurchased = drugPurchased;
        this.customerName = customerName;
        this.quantity = quantity;
        this.amount = amount;
    }

    public String getDrugPurchased() {
        return drugPurchased;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getAmount() {
        return amount;
    }
}
