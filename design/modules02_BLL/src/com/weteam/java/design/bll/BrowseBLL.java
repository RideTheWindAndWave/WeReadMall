package com.weteam.java.design.bll;

import com.weteam.java.design.dal.BrowseDAL;
import com.weteam.java.design.entity.Book;

import java.util.LinkedHashMap;

public class BrowseBLL {

    public static LinkedHashMap<String, Book> getBookLinkedHashMap(int showNumber) {
        return BrowseDAL.getBookLinkedHashMap(showNumber);
    }

}
