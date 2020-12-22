package com.weteam.java.design.dal.permissions_dal;

import com.weteam.java.design.dal.util.JdbcUtils;
import com.weteam.java.design.entity.Book;
import com.weteam.java.design.entity.OrderList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdministratorDAL {

    public static boolean isHasDAL(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean boo = false;
        String sql = "SELECT name FROM books WHERE id = ?";
        try {
            connection = JdbcUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            boo = resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(resultSet, preparedStatement, connection);
        }
        return boo;
    }

    public static boolean insertDAL(Book book) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int count = 0;
        if(!isHaving(book.name)){
            String sql = "INSERT INTO books(name, author, pubTime, inventory, price) VALUES (?, ?, ?, ?, ?)";
            try {
                connection = JdbcUtils.getConnection();
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, book.name);
                preparedStatement.setString(2, book.getAuthor());
                preparedStatement.setString(3, book.getPubTime());
                preparedStatement.setInt(4, book.getInventory());
                preparedStatement.setDouble(5, book.price);
                count = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                JdbcUtils.close(preparedStatement, connection);
            }
        }else{
            String sql = "UPDATE books SET inventory = inventory + ? WHERE name = ?";
            try {
                connection = JdbcUtils.getConnection();
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, book.getInventory());
                preparedStatement.setString(2, book.name);
                count = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                JdbcUtils.close(preparedStatement, connection);
            }
        }
        return count > 0;
    }

    private static boolean isHaving(String name){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int count = 0;
        String sql = "SELECT * FROM books WHERE name = ? ";
        try {
            connection = JdbcUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                count = 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(preparedStatement, connection);
        }
        return count > 0;
    }

    public static boolean updateNumberUpDAL(int number, int id) {
        String sql = "UPDATE books SET inventory = inventory + ? WHERE id = ?";
        return JdbcUtils.updateBookDAL(number, id, sql);
    }

    public static boolean updateNumberDownDAL(int number, int id) {
        String sql = "UPDATE books SET inventory = inventory - ? WHERE id = ?";
        return JdbcUtils.updateBookDAL(number, id, sql);
    }

    public static int selectIntDAL(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int inventory = 0;
        String sql = "SELECT inventory FROM books WHERE id = ?";
        try {
            connection = JdbcUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                inventory = resultSet.getInt("inventory");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventory;
    }

    public static Book selectBookDAL(String name) {
        return JdbcUtils.selectBook(name);
    }

    public static ArrayList<String> getOrderListIds() {
        ArrayList<String> arrayList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT id FROM orderlist WHERE orderState = ?";
        try {
            connection = JdbcUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, OrderList.PAID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                arrayList.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(resultSet, preparedStatement, connection);
        }
        return arrayList;
    }

    public static boolean updateOrderState(String id) {
        int count = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE orderlist SET orderState = ? WHERE id = ?";
        try {
            connection = JdbcUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, OrderList.SHIPPED);
            preparedStatement.setString(2, id);
            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count > 0;
    }

}
