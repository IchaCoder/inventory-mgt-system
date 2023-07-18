package com.example.group;

public class Drugs {
    private String name, category;
    private int id, quantity;

    public Drugs(String name, int id, String category, int quantity) {
        this.name = name;
        this.category = category;
        this.id = id;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }
}
