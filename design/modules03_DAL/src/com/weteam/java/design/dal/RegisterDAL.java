package com.weteam.java.design.dal;

import com.weteam.java.design.dal.util.JdbcUtils;
import com.weteam.java.design.entity.permissions_entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterDAL {

    /**
     * isExistDAL
     *
     * @param userId
     * @return
     */
    public static boolean isExistDAL(String userId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT userId FROM Users WHERE userId = ?;";
        boolean boo = false;
        try {
            connection = JdbcUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userId);
            resultSet = preparedStatement.executeQuery();
            boo = resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(resultSet, preparedStatement, connection);
        }
        return boo;
    }

    /**
     * isInsert
     *
     * @param user
     * @return
     */
    public static int isInsert(User user) {
        int count = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO Users VALUES (?, ?, ?, ?);";
        try {
            connection = JdbcUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUserId());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setInt(4, user.getIsAdministrator());
            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(preparedStatement, connection);
        }
        return count;
    }

}
