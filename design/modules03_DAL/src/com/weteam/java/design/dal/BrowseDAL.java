package com.weteam.java.design.dal;

import com.weteam.java.design.dal.util.JdbcUtils;
import com.weteam.java.design.entity.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;

public class BrowseDAL {

    public static LinkedHashMap<String, Book> getBookLinkedHashMap(int showNumber) {
        String sql = "SELECT * FROM books LIMIT ?, ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        LinkedHashMap<String, Book> bookLinkedHashMap = new LinkedHashMap<>();
        try {
            connection = JdbcUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, showNumber);
            preparedStatement.setInt(2, 10);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("author"),
                        resultSet.getString("pubTime"),
                        resultSet.getInt("inventory"),
                        resultSet.getDouble("price"));
                bookLinkedHashMap.put(book.name, book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(resultSet, preparedStatement, connection);
        }
        return bookLinkedHashMap;
    }

}
