package com.weteam.java.design.bll.permissions_bll;

import com.weteam.java.design.dal.permissions_dal.CustomerDAL;
import com.weteam.java.design.entity.*;
import com.weteam.java.design.entity.permissions_entity.Customer;
import com.weteam.java.design.entity.permissions_entity.Role;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Random;

public class CustomerImplBLL implements Customer {

    @Override
    public Book select(String name) {
        if (CustomerDAL.selectBookDAL(name) != null) {
            Book book = CustomerDAL.selectBookDAL(name);
            if (book != null) {
                System.out.println(
                        "书名: " + book.name + " -- 作者: " + book.getAuthor() + " -- 出版时间: " + book.getPubTime()
                                + " -- 库存: " + book.getInventory() + " -- 价格: ￥" + book.price
                );
            }
            return book;
        } else {
            System.out.println("查无此书!");
            return null;
        }
    }

    @Override
    public boolean insertShoppingCart(String userId, String name, int number) {
        Product product = getProductBLL(name, number);
        return product != null && CustomerDAL.insertShoppingCartDAL(userId, product);
    }

    @Override
    public ShoppingCart getShoppingCart(String id) {
        ShoppingCart shoppingCartDAL = CustomerDAL.getShoppingCartDAL(id);
        if (shoppingCartDAL.getProductsLinkedList().isEmpty()) {
            System.out.println("购物车已经空了, 快去加购吧^_^");
        }
        return shoppingCartDAL;
    }


    @Override
    public OrderList checkout(String id, ShoppingCart shoppingCart) {
        LinkedList<Product> productsLinkedList = shoppingCart.getProductsLinkedList();
        if (isCanCheckout(productsLinkedList)){
            //生成订单号并添加订单列表
            Date date = new Date();
            String orderId = String.valueOf(date.getTime()).substring(5);
            String number = "";
            for (int i = 0; i < 4; i++) {
                number += String.valueOf(new Random().nextInt(10));
            }
            orderId = number + orderId;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ArrayList<Product> productArrayList = new ArrayList<>();
            productArrayList.addAll(productsLinkedList);
            OrderList orderList = new OrderList(orderId, id, productArrayList, OrderList.PAID, dateFormat.format(date));
            boolean boo = CustomerDAL.insertOrderList(orderList);
            //删除已结算过的购物车商品
            for (Product e : productsLinkedList
                    ) {
                CustomerDAL.deleteProductDAL(id, e.id);
            }
            return orderList;
        }else {
            return null;
        }
    }

    @Override
    public boolean updateShoppingCart(String id, String name, int updateNumber) {
        return CustomerDAL.updateShoppingCartDAL(id, name, updateNumber);
    }

    @Override
    public boolean deleteProduct(String userId, int id) {
        return CustomerDAL.deleteProductDAL(userId, id);
    }

    @Override
    public boolean signOrder(String orderListId) {
        if (!CustomerDAL.getOrderState(orderListId).equals("已发货")){
            System.out.println("亲, 还未发货哦!");
            return false;
        }else{
            Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return CustomerDAL.updateOrderList(orderListId, dateFormat.format(date));
        }
    }

    private static Product getProductBLL(String name, int number) {
        if (CustomerDAL.getProductDAL(name, number) != null) {
            return CustomerDAL.getProductDAL(name, number);
        } else {
            System.out.println("没有此商品!");
            return null;
        }
    }

    private synchronized boolean isCanCheckout(LinkedList<Product> productsLinkedList){
        boolean boo = true;
        for (Product e : productsLinkedList
                ) {
            int inventory = select(e.name).getInventory();
            if (e.buyNumber > inventory) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                System.out.println(e.name + "库存不足, 余" + inventory + "件");
                boo = false;
            }
        }
        return boo;
    }

    public static ArrayList<OrderList> selectOrderList(Role role){
        return CustomerDAL.selectOrderLists(role);
    }

    /*@Override
    public Gifts buyGifts(int exCode) {
        return null;
    }*/

}
