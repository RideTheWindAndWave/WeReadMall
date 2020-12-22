package com.weteam.java.design.ui;

import com.weteam.java.design.entity.permissions_entity.Role;
import com.weteam.java.design.entity.utils.OptimizeScanner;

public class DosUI {

    public static void main(String[] args) {
        System.out.println("欢迎使用WeReadMall图书商城系统!(Powered by WeTeam)");
        Role role = new Role();
        while (true) {
            System.out.println("***********************************************");
            System.out.println("* 1. 浏览  2. 购物车  3. 加购  4. 账户  e. 退出 *");
            System.out.println("***********************************************");
            String radio = OptimizeScanner.getString();
            switch (radio) {
                case "1":
                    BrowseUI.show();
                    break;
                case "2":
                    role.getShoppingCart(role.getId());
                    break;
                case "3":
                    System.out.print("请输入商品名称: ");
                    String name = OptimizeScanner.getString();
                    System.out.print("请输入购买数量: ");
                    int number = OptimizeScanner.getInt();
                    role.insertShoppingCart(role.getId(), name, number);
                    break;
                case "4":
                    role = AccountUI.getEmpower();//获取权限角色
                    if (role != null) {
                        if (role.getAdministrator() != null) {
                            role = AdministratorUI.AdministratorBusiness(role);
                        } else if (role.getCustomer() != null) {
                            role = CustomerUI.customerBusiness(role);
                        }
                    } else {
                        System.out.println("未登录!");
                    }
                    break;
                case "e":
                    System.exit(0);
                    break;
                default:
                    System.out.println("请输入正确的菜单项");
                    break;
            }
        }
    }

}