package com.weteam.java.design.bll;

import com.weteam.java.design.dal.LoginDAL;
import com.weteam.java.design.dal.RegisterDAL;
import com.weteam.java.design.entity.permissions_entity.User;

public class AccountBLL {

    /**
     * isRegisterBLL
     *
     * @param user
     * @return
     */
    public static String isRegisterBLL(User user) {
        if (RegisterDAL.isExistDAL(user.getUserId())) {
            return ("亲" + user.getUserId() + "已经被注册了!");
        } else {
            if (RegisterDAL.isInsert(user) > 0) {
                return "注册成功";
            } else {
                return "注册失败";
            }
        }
    }

    /**
     * getUser(验证登录) -- login(登录) -- getEmpower(账户)
     *
     * @param username
     * @param password
     * @return
     */
    public static User getUser(String username, String password) {
        return loginBLL(username, password);
    }

    /**
     * loginBLL
     *
     * @param username
     * @param password
     * @return
     */
    public static User loginBLL(String username, String password) {
        return LoginDAL.loginDAL(username, password);
    }

}
