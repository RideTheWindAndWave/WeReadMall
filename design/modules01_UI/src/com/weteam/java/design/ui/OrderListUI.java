package com.weteam.java.design.ui;

import com.weteam.java.design.entity.OrderList;
import com.weteam.java.design.entity.Product;

public class OrderListUI {

    public static void show(OrderList orderList) {
        double totalPrice = 0;
        System.out.println("订单编号: " + orderList.getId() + "\t\t" + orderList.getOrderState());
        System.out.println("下单时间: " + orderList.getOrderTime());
        if (orderList.getSuccessfulTime() != null) {
            System.out.println("完成时间: " + orderList.getSuccessfulTime());
        }
        for (Product e : orderList.getProductArrayList()
                ) {
            System.out.println(e.name + "\t\t￥" + e.price + "\tx" + e.buyNumber);
            totalPrice += e.price * e.buyNumber;
        }
        System.out.println("支付总额: " + totalPrice);
        System.out.println();
    }

}
