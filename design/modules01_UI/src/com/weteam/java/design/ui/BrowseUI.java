package com.weteam.java.design.ui;

import com.weteam.java.design.bll.BrowseBLL;
import com.weteam.java.design.entity.Book;
import com.weteam.java.design.entity.utils.OptimizeScanner;

import java.util.LinkedHashMap;
import java.util.Objects;

public class BrowseUI {

    public static void show() {
        LinkedHashMap<String, Book> bookLinkedHashMap = null;
        boolean isBack = false;
        do {
            System.out.println("******************************************");
            System.out.println("* b. 返回  (1 ~ n). 查看页数  e. 退出 *");
            System.out.println("******************************************");
            int showPages = 0;
            String input = OptimizeScanner.getString();
            if (Objects.equals(input, "b")) {
                isBack = true;
            } else if (Objects.equals(input, "e")) {
                System.exit(0);
            } else {
                try {
                    showPages = Integer.valueOf(input);
                } catch (NumberFormatException e) {
                    System.out.println("请输入正确的单选项!");
                    continue;
                }
                bookLinkedHashMap = getBookLinkedHashMap((showPages - 1) * 10);
                if (bookLinkedHashMap.isEmpty()) {
                    System.out.println("已经浏览到尽头了, 快去看看别的吧^_^!");
                } else {
                    for (String e : bookLinkedHashMap.keySet()
                            ) {
                        Book book = bookLinkedHashMap.get(e);
                        System.out.println("书名: " + book.name + " -- 作者: " + book.getAuthor() + " -- 价格: ￥" + book.price);
                    }
                }
            }
        } while (!isBack);
    }

    private static LinkedHashMap<String, Book> getBookLinkedHashMap(int showNumber) {
        return BrowseBLL.getBookLinkedHashMap(showNumber);
    }

}
