package com.weteam.java.design.entity;

import java.util.ArrayList;

public class OrderList {

    public static final String PAID = "已支付";
    public static final String SHIPPED = "已发货";
    public static final String COMPLETED = "已完成";
    private String id;
    private String userId;
    private ArrayList<Product> productArrayList;
    private String orderState;
    private String orderTime;
    private String successfulTime;

    public OrderList(String id, String userId, ArrayList<Product> productArrayList, String orderState, String orderTime) {
        this.id = id;
        this.userId = userId;
        this.productArrayList = productArrayList;
        this.orderState = orderState;
        this.orderTime = orderTime;
    }

    public OrderList(String id, String userId, ArrayList<Product> productArrayList, String orderState, String orderTime, String successfulTime) {
        this.id = id;
        this.userId = userId;
        this.productArrayList = productArrayList;
        this.orderState = orderState;
        this.orderTime = orderTime;
        this.successfulTime = successfulTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ArrayList<Product> getProductArrayList() {
        return productArrayList;
    }

    public void setProductArrayList(ArrayList<Product> productArrayList) {
        this.productArrayList = productArrayList;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getSuccessfulTime() {
        return successfulTime;
    }

    public void setSuccessfulTime(String successfulTime) {
        this.successfulTime = successfulTime;
    }
}
