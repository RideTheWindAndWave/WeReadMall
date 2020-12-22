package com.weteam.java.design.bll.permissions_bll;

import com.weteam.java.design.dal.permissions_dal.AdministratorDAL;
import com.weteam.java.design.dal.util.JdbcUtils;
import com.weteam.java.design.entity.Book;
import com.weteam.java.design.entity.Product;
import com.weteam.java.design.entity.permissions_entity.Administrator;

import java.util.ArrayList;

public class AdministratorImplBLL implements Administrator {

    @Override
    public String storage(int id, int number) {
        if (AdministratorDAL.isHasDAL(id)) {
            if (AdministratorDAL.updateNumberUpDAL(number, id)) {
                return "入库成功!";
            } else {
                return "入库失败!";
            }
        } else {
            return "查无此书, 请先添加书!";
        }
    }

    @Override
    public boolean outOfLibrary(String orderListId) {
        boolean boo = true;
        for (Product e : JdbcUtils.getProductArrayList(orderListId)
                ) {
            if (!AdministratorDAL.updateNumberDownDAL(e.buyNumber, e.id)) {
                boo = false;
            }
        }
        if (boo) {
            return AdministratorDAL.updateOrderState(orderListId);
        } else {
            return false;
        }
    }

    @Override
    public void insert(Book book) {
        if (AdministratorDAL.insertDAL(book)) {
            System.out.println("添加成功!");
        } else {
            System.out.println("添加失败!");
        }
    }

    @Override
    public Book select(String name) {
        if (AdministratorDAL.selectBookDAL(name) != null) {
            Book book = AdministratorDAL.selectBookDAL(name);
            if (book != null) {
                System.out.println(
                        "书号: " + book.id + " -- 书名: " + book.name
                                + " -- 库存: " + book.getInventory()
                );
            }
            return book;
        } else {
            System.out.println("查无此书!");
            return null;
        }
    }

    public static ArrayList<String> getOrderListIds() {
        return AdministratorDAL.getOrderListIds();
    }

}
