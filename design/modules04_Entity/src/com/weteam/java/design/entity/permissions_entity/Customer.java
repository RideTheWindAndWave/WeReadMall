package com.weteam.java.design.entity.permissions_entity;

import com.weteam.java.design.entity.Book;
import com.weteam.java.design.entity.OrderList;
import com.weteam.java.design.entity.ShoppingCart;

public interface Customer {

    /**
     * select
     */
    public Book select(String name);

    /**
     * insertShoppingCart
     *
     * @param userId
     * @param name
     * @param number
     * @return
     */
    public boolean insertShoppingCart(String userId, String name, int number);

    /**
     * getShoppingCart
     *
     * @param id
     * @return
     */
    public ShoppingCart getShoppingCart(String id);


    /**
     * checkout
     *
     * @param id
     * @param shoppingCart
     * @return
     */
    public OrderList checkout(String id, ShoppingCart shoppingCart);

    /**
     * updateShoppingCart
     *
     * @param id
     * @param name
     * @param updateNumber
     * @return
     */
    public boolean updateShoppingCart(String id, String name, int updateNumber);

    /**
     * deleteProduct
     *
     * @param userId
     * @param id
     * @return
     */
    public boolean deleteProduct(String userId, int id);

    /**
     * signOrder
     *
     * @param orderListId
     * @return
     */
    public boolean signOrder(String orderListId);

    /**
     * buyGifts
     *
     * @param exCode
     * @return
     *//*
    public Gifts buyGifts(int exCode);//买赠品*/

}
