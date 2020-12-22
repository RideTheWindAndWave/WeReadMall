package com.weteam.java.design.entity;

public class Product {

    public int id;
    public String name;
    public double price;
    public int buyNumber;

    public double cost() {
        System.out.println("-------小票-------");
        System.out.println(name + ": ￥" + price);
        return price;
    }

    public Product() {
    }

    public Product(int id, String name, double price, int buyNumber) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.buyNumber = buyNumber;
    }

}
