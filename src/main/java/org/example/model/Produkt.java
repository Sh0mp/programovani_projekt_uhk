package org.example.model;

public class Produkt {
    private String name;
    private int quantity;
    private double price;
    private int id;
    private static int nextId = 1;

    public Produkt() {

    }

    public Produkt(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.id = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String toString() {
        return "ID-" + id + " " + name + " " + price + "Kč " + quantity + "Ks";
    }


}
