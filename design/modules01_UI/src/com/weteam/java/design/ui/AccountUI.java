package com.weteam.java.design.ui;

import com.weteam.java.design.bll.AccountBLL;
import com.weteam.java.design.bll.permissions_bll.AdministratorImplBLL;
import com.weteam.java.design.bll.permissions_bll.CustomerImplBLL;
import com.weteam.java.design.entity.permissions_entity.Role;
import com.weteam.java.design.entity.permissions_entity.User;
import com.weteam.java.design.entity.utils.OptimizeScanner;

public class AccountUI {

    /**
     * getEmpower(账户)
     *
     * @return Role
     */
    public static Role getEmpower() {
        boolean boo = true;
        while (boo) {
            System.out.println("*************************************");
            System.out.println("* b. 返回  1. 登录  2. 注册  e. 退出 *");
            System.out.println("*************************************");
            String radio = OptimizeScanner.getString();
            switch (radio) {
                case "b":
                    boo = false;
                    break;
                case "1":
                    Role role = login();
                    if (role != null) {
                        return role;
                    } else {
                        break;
                    }
                case "2":
                    register();
                    break;
                case "e":
                    System.exit(0);
                    break;
                default:
                    System.out.println("请输入正确的菜单项");
                    break;
            }
        }
        return null;
    }

    /**
     * register
     */
    public static void register() {
        System.out.print("请输入账  号: ");
        String userId = OptimizeScanner.getString();
        System.out.print("请输入用户名: ");
        String userName = OptimizeScanner.getString();
        System.out.print("请输入密  码: ");
        String password = OptimizeScanner.getString();
        System.out.println(AccountBLL.isRegisterBLL(new User(userId, userName, password, 0)));
    }

    /**
     * login
     *
     * @return Role
     */
    public static Role login() {
        System.out.print("请输入账号: ");
        String username = OptimizeScanner.getString();
        System.out.print("请输入密码: ");
        String password = OptimizeScanner.getString();
        User user = AccountBLL.getUser(username, password);
        if (user != null) {
            if (user.getIsAdministrator() == 1) {
                System.out.println("管理员" + user.getUsername() + "登录成功!");
                return new Role(user.getUserId(), user.getUsername(), new AdministratorImplBLL());
            } else {
                System.out.println("用户" + user.getUsername() + "登录成功!");
                return new Role(user.getUserId(), user.getUsername(), new CustomerImplBLL());
            }
        } else {
            System.out.println("用户名或密码错误!");
            return null;
        }
    }

}
