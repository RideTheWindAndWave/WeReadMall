package com.weteam.java.design.dal;

import com.weteam.java.design.dal.util.JdbcUtils;
import com.weteam.java.design.entity.permissions_entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAL {

    public static User loginDAL(String userId, String password) {
        String sql = "SELECT * FROM Users WHERE userId = ? AND password = ?;";
        return getUser(sql, userId, password);
    }

    /**
     * getUser
     *
     * @param sql
     * @param userId
     * @param password
     * @return
     */
    public static User getUser(String sql, String userId, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = JdbcUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(resultSet, preparedStatement, connection);
        }
        return user;
    }

}
