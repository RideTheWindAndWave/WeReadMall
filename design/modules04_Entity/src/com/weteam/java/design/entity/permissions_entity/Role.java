package com.weteam.java.design.entity.permissions_entity;

import com.weteam.java.design.entity.Book;
import com.weteam.java.design.entity.OrderList;
import com.weteam.java.design.entity.ShoppingCart;

public class Role {

    private String id;
    private String description;// 角色名
    private Administrator administrator = null;//库管权限
    private Customer customer = null;//顾客权限

    public Role() {
    }

    public Role(String id, String description, Administrator administrator) {
        this.id = id;
        this.description = description;
        this.administrator = administrator;
    }

    public Role(String id, String description, Customer customer) {
        this.id = id;
        this.description = description;
        this.customer = customer;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public Customer getCustomer() {
        return customer;
    }

    /**
     * isNotLogin
     *
     * @return
     */
    public boolean isNotLogin() {
        boolean boo = (administrator == null && customer == null);
        if (boo) {
            System.out.println("您还没有登录，请登陆后再操作");
            return true;
        } else {
            return false;
        }
    }

    /**
     * storage
     *
     * @param id
     * @param number
     */
    public void storage(int id, int number) {
        if (administrator == null) {
            System.out.println("您没有库存管理员权限");
            return;
        }
        System.out.println(administrator.storage(id, number));
    }

    /**
     * outOfLibrary
     *
     * @param orderListId
     */
    public boolean outOfLibrary(String orderListId) {
        if (administrator == null) {
            System.out.println("您没有库存管理员权限");
            return false;
        }
        return administrator.outOfLibrary(orderListId);
    }

    /**
     * insert
     *
     * @param book
     */
    public void insert(Book book) {
        if (administrator == null) {
            System.out.println("您没有库存管理员权限");
            return;
        }
        administrator.insert(book);
    }

    /**
     * select
     */
    public Book select(String name) {
        if (isNotLogin()) {
            return null;
        } else if (customer != null) {
            return customer.select(name);
        } else {
            return administrator.select(name);
        }
    }

    /**
     * insertShoppingCart
     *
     * @param userId
     * @param name
     * @param number
     * @return
     */
    public boolean insertShoppingCart(String userId, String name, int number) {
        if (isNotLogin()) {
            return false;
        } else if (customer == null) {
            System.out.println("只有用户才能添加购物车哦!");
            return false;
        }
        return customer.insertShoppingCart(userId, name, number);
    }

    /**
     * getShoppingCart
     *
     * @param id
     * @return
     */
    public ShoppingCart getShoppingCart(String id) {
        if (isNotLogin()) {
            return null;
        } else if (customer == null) {
            System.out.println("只有用户才有购物车哦!");
            return null;
        }
        return customer.getShoppingCart(id);
    }

    /**
     * checkout
     *
     * @param id
     * @param shoppingCart
     * @return
     */
    public OrderList checkout(String id, ShoppingCart shoppingCart) {
        if (isNotLogin()) {
            return null;
        } else if (customer == null) {
            System.out.println("只有用户才能结账哦");
            return null;
        }
        return customer.checkout(id, shoppingCart);
    }

    /**
     * updateShoppingCart
     *
     * @param id
     * @param name
     * @param updateNumber
     * @return
     */
    public boolean updateShoppingCart(String id, String name, int updateNumber) {
        if (isNotLogin()) {
            return false;
        } else if (customer == null) {
            System.out.println("只有用户才能修改数量哦");
            return false;
        }
        return customer.updateShoppingCart(id, name, updateNumber);
    }

    public boolean deleteProduct(String userId, int id) {
        if (isNotLogin()) {
            return false;
        } else if (customer == null) {
            System.out.println("只有用户才能删除商品哦");
            return false;
        }
        return customer.deleteProduct(userId, id);
    }

    /**
     * signOrder
     *
     * @param orderListId
     * @return
     */
    public boolean signOrder(String orderListId) {
        if (isNotLogin()) {
            return false;
        } else if (customer == null) {
            System.out.println("只有用户才能确认收货哦");
            return false;
        }
        return customer.signOrder(orderListId);
    }

    /**
     * buyGift
     *
     * @param giftsCode
     * @return
     *//*
    public Gifts buyGift(int giftsCode) {
        if (isNotLogin()) {
            return null;
        } else if (customer == null) {
            System.out.println("只有用户才能购买哦");
            return null;
        }
        return customer.buyGifts(giftsCode);
    }*/

}
