package com.weteam.java.design.ui;

import com.weteam.java.design.bll.permissions_bll.CustomerImplBLL;
import com.weteam.java.design.entity.OrderList;
import com.weteam.java.design.entity.permissions_entity.Role;
import com.weteam.java.design.entity.utils.OptimizeScanner;

import java.util.ArrayList;

public class CustomerUI {

    public static Role customerBusiness(Role role) {
        boolean isBack = false;
        do {
            System.out.println("**********************************************************************");
            System.out.println("* b. 退出登录  1. 浏览  2. 查找  3. 加购  4. 购物车  5. 查看订单  e. 退出 *");
            System.out.println("**********************************************************************");
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
                    select(role);
                    break;
                case "3":
                    insertShoppingCart(role);
                    break;
                case "4":
                    ShoppingCartUI.show(role);
                    break;
                case "5":
                    showOrderList(role);
                    break;
                case "e":
                    System.exit(0);
                    break;
                default:
                    System.out.println("请输入正确的菜单项");
                    break;
            }
        } while (!isBack);
        return role;
    }

    private static void showOrderList(Role role) {
        ArrayList<OrderList> orderListArrayList = CustomerImplBLL.selectOrderList(role);
        if (orderListArrayList.isEmpty()) {
            System.out.println("暂无订单信息哦, 快去下单吧^_^");
        } else {
            for (OrderList e : orderListArrayList
                    ) {
                OrderListUI.show(e);
            }
            System.out.println("是否确认收货?(yes/no)");
            String flag = OptimizeScanner.getString();
            if (flag.equalsIgnoreCase("yes")) {
                System.out.print("请输入订单号: ");
                String orderId = OptimizeScanner.getString();
                if (role.signOrder(orderId)) {
                    System.out.println("收货成功!");
                } else {
                    System.out.println("收货失败!");
                }
            }
        }
    }

    private static void select(Role role) {
        System.out.print("请输入书名: ");
        String name = OptimizeScanner.getString();
        role.select(name);
    }

    private static void insertShoppingCart(Role role) {
        System.out.print("请输入商品名称: ");
        String name = OptimizeScanner.getString();
        System.out.print("请输入购买数量: ");
        int number = OptimizeScanner.getInt();
        if (role.insertShoppingCart(role.getId(), name, number)) {
            System.out.println("加购成功");
        } else {
            System.out.println("加购失败!");
        }
    }

}
