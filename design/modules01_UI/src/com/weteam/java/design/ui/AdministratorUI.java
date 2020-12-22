package com.weteam.java.design.ui;

import com.weteam.java.design.bll.permissions_bll.AdministratorImplBLL;
import com.weteam.java.design.entity.Book;
import com.weteam.java.design.entity.permissions_entity.Role;
import com.weteam.java.design.entity.utils.OptimizeScanner;

public class AdministratorUI {

    public static Role AdministratorBusiness(Role role) {
        boolean isBack = false;
        do {
            System.out.println("*******************************************************************");
            System.out.println("* b. 退出登录  1. 浏览  2. 入库  3. 出库  4. 添加  5. 查找  e. 退出 *");
            System.out.println("*******************************************************************");
            String radio = OptimizeScanner.getString();
            switch (radio) {
                case "b":
                    isBack = true;
                    role = new Role();
                    break;
                case "1":
                    BrowseUI.show();
                    break;
                case "2":
                    storage(role);
                    break;
                case "3":
                    outOfLibrary(role);
                    break;
                case "4":
                    insert(role);
                    break;
                case "5":
                    System.out.print("请输入书名: ");
                    String name = OptimizeScanner.getString();
                    role.select(name);
                    break;
                case "e":
                    System.exit(0);
                    break;
                default:
                    System.out.print("请输入正确的菜单项");
                    break;
            }
        } while (!isBack);
        return role;
    }

    private static void storage(Role role) {
        System.out.print("请输入入库书号: ");
        int id = OptimizeScanner.getInt();
        System.out.print("请输入入库数量: ");
        int number = OptimizeScanner.getInt();
        while (number <= 0) {
            System.out.println("入库数量必须大于0");
            number = OptimizeScanner.getInt();
        }
        role.storage(id, number);
    }

    private static void outOfLibrary(Role role) {
        System.out.println("****** 待出库的订单号 ******");
        for (String e : AdministratorImplBLL.getOrderListIds()
                ) {
            System.out.println(e);
        }
        System.out.print("请输入本次出库的订单号: ");
        String orderListId = OptimizeScanner.getString();
        if (role.outOfLibrary(orderListId)) {
            System.out.println("出库成功!");
        } else {
            System.out.println("出库失败!");
        }
    }

    private static void insert(Role role) {
        System.out.print("请输入书名: ");
        String name = OptimizeScanner.getString();
        System.out.print("请输入作者: ");
        String author = OptimizeScanner.getString();
        System.out.print("请输入出版日期: ");
        String pubTime = OptimizeScanner.getString();
        System.out.print("请输入库存: ");
        int inventory = OptimizeScanner.getInt();
        System.out.print("请输入价格: ");
        double price = OptimizeScanner.getDouble();
        Book book = new Book(0, name, author, pubTime, inventory, price);
        role.insert(book);
    }

}
