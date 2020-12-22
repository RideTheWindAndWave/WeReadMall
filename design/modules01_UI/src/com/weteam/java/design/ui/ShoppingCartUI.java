package com.weteam.java.design.ui;

import com.weteam.java.design.entity.*;
import com.weteam.java.design.entity.permissions_entity.Role;
import com.weteam.java.design.entity.gifts_factory.GiftsFactory;
import com.weteam.java.design.entity.utils.OptimizeScanner;

public class ShoppingCartUI {

    public static void show(Role role) {
        ShoppingCart shoppingCart = role.getShoppingCart(role.getId());
        show(shoppingCart);
        boolean isBack = false;
        do {
            System.out.println("**********************************************************");
            System.out.println("* b. 返回主页  1. 结算  2. 修改数量  3. 删除商品  e. 退出 *");
            System.out.println("**********************************************************");
            String radio = OptimizeScanner.getString();
            switch (radio) {
                case "b":
                    isBack = true;
                    break;
                case "1":
                    checkout(role);
                    show(role.getShoppingCart(role.getId()));
                    break;
                case "2":
                    updateNumber(shoppingCart, role);
                    show(role.getShoppingCart(role.getId()));
                    break;
                case "3":
                    delete(role);
                    show(role.getShoppingCart(role.getId()));
                    break;
                case "e":
                    System.exit(0);
                    break;
                default:
                    System.out.println("请输入正确的菜单项");
                    break;
            }
        } while (!isBack);
    }

    private static void checkout(Role role) {
        Product product = null;
        ShoppingCart shoppingcart = (role.getShoppingCart(role.getId()));
        if (!shoppingCartIsEmpty(role)) {
            double price = shoppingcart.cost() * 100;
            System.out.println("应付金额为: " + shoppingcart.cost());
            if (price >= 150) {
                product = GiftsFactory.create(3);
            } else if (price >= 100) {
                product = GiftsFactory.create(2);
            } else if (price >= 50) {
                product = GiftsFactory.create(1);
            }
            System.out.print("输入付款金额: ");
            double money = OptimizeScanner.getDouble() * 100;
            while (price > money) {
                System.out.println("付款金额必须大于支付金额!");
                System.out.print("输入付款金额: ");
                money = OptimizeScanner.getDouble() * 100;
            }
            OrderList orderList = role.checkout(role.getId(), shoppingcart);
            if (orderList != null) {
                System.out.println("下单成功!");
                System.out.println("找零: " + (Math.ceil(money - price)) / 100.0);
                if (product != null) {
                    product.cost();
                }
                OrderListUI.show(orderList);
            } else {
                System.out.println("下单失败!");
            }
        }
    }

    private static void updateNumber(ShoppingCart shoppingCart, Role role) {
        if (!shoppingCartIsEmpty(role)) {
            System.out.print("请输入需要修改的商品名: ");
            String name = OptimizeScanner.getString();
            System.out.print("请输入需要的数量: ");
            int updateNumber = OptimizeScanner.getInt();
            if (role.updateShoppingCart(role.getId(), name, updateNumber)) {
                System.out.println("修改成功!");
            } else {
                System.out.println("修改失败!");
            }
        }
    }

    private static void delete(Role role) {
        if (!shoppingCartIsEmpty(role)) {
            System.out.print("请输入需要删除的商品名: ");
            String name = OptimizeScanner.getString();
            Book book = role.select(name);
            if (role.deleteProduct(role.getId(), book.id)) {
                System.out.println("删除成功!");
            } else {
                System.out.println("删除失败!");
            }
        }
    }

    private static void show(ShoppingCart shoppingCart) {
        if (shoppingCart != null) {
            for (Product e : shoppingCart.getProductsLinkedList()
                    ) {
                System.out.println("商品名: " + e.name + " -- 价格: ￥" + e.price + " -- 数量: " + e.buyNumber);
            }
        }
    }

    private static boolean shoppingCartIsEmpty(Role role) {
        boolean boo = role.getShoppingCart(role.getId()).getProductsLinkedList().isEmpty();
        if (boo) {
            System.out.println("亲, 购物车是空的哦!");
        }
        return boo;
    }

}
