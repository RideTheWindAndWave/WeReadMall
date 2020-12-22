package com.weteam.java.design.entity;

public class Book extends Product {

    private String author;
    private String pubTime;//出版时间
    private int inventory;//库存

    public Book() {
    }

    public Book(int id, String name, String author, String pubTime, int inventory, double price) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.pubTime = pubTime;
        this.inventory = inventory;
        this.price = price;
    }

    public Book(int id, String name, String author, String pubTime, int inventory, double price, int buyNumber) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.pubTime = pubTime;
        this.inventory = inventory;
        this.price = price;
        this.buyNumber = buyNumber;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPubTime() {
        return pubTime;
    }

    public void setPubTime(String pubTime) {
        this.pubTime = pubTime;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + this.id +
                ", name='" + this.name + '\'' +
                ", author='" + author + '\'' +
                ", pubTime='" + pubTime + '\'' +
                ", inventory=" + inventory +
                ", price=" + this.price +
                '}';
    }

}
