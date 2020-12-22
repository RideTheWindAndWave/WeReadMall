package com.weteam.java.design.entity.permissions_entity;

import com.weteam.java.design.entity.Book;

public interface Administrator {

    /**
     * storage
     *
     * @param id
     * @param number
     */
    public String storage(int id, int number);

    /**
     * outOfLibrary
     *
     * @param orderListId
     * @return
     */
    public boolean outOfLibrary(String orderListId);

    /**
     * insert
     *
     * @param book
     */
    public void insert(Book book);

    /**
     * select
     *
     * @return
     */
    public Book select(String name);

}
