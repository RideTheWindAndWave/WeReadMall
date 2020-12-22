package com.weteam.java.design.entity.gifts_factory;

import com.weteam.java.design.entity.Product;

public class Gifts extends Product {

    @Override
    public double cost() {
        System.out.print("附赠品: ");
        System.out.println(this.name + ": ￥" + this.price);
        System.out.println("--------------");
        return this.price;
    }

}
