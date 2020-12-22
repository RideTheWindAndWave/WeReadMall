package com.weteam.java.design.entity;

import java.util.LinkedList;

public class ShoppingCart {

    private String userId;
    private LinkedList<Product> productsLinkedList;

    public ShoppingCart(String userId, LinkedList<Product> productsLinkedList) {
        this.userId = userId;
        this.productsLinkedList = productsLinkedList;
    }

    public double cost() {
        double price = 0;
        for (Product e : productsLinkedList
                ) {
            price += e.price * e.buyNumber;
        }
        return price;
    }

    public void show() {
        for (Product e : productsLinkedList
                ) {
            e.cost();
        }
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LinkedList<Product> getProductsLinkedList() {
        return productsLinkedList;
    }

    public void setProductsLinkedList(LinkedList<Product> productsLinkedList) {
        this.productsLinkedList = productsLinkedList;
    }

}
