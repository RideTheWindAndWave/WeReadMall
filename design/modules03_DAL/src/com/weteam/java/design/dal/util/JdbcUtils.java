package com.weteam.java.design.dal.util;

import com.weteam.java.design.entity.Book;
import com.weteam.java.design.entity.Product;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class JdbcUtils {

    private static String url;
    private static String user;
    private static String password;
    private static String driver;

    static {
        ClassLoader classLoader = JdbcUtils.class.getClassLoader();
        URL resource = classLoader.getResource("jdbc.properties");
        String path = resource.getPath();
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(path));
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            driver = properties.getProperty("driver");
            Class.forName(driver);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    static int connCloseNumb = 0, prepCloseNumb = 0, resCloseNumb = 0;

    /**
     * getConnection
     *
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * close
     *
     * @param preparedStatement
     * @param connection
     */
    public static void close(PreparedStatement preparedStatement, Connection connection) {
        close(null, preparedStatement, connection);
    }

    /**
     * close
     *
     * @param resultSet
     * @param preparedStatement
     * @param connection
     */
    public static void close(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                resCloseNumb++;
            }
        } else {
            System.out.println("The ResultSet is null at " + ++resCloseNumb + "st time!");
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                prepCloseNumb++;
            }
        } else {
            System.out.println("The PreparedStatement is null at " + ++prepCloseNumb + "st time!");
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                connCloseNumb++;
            }
        } else {
            System.out.println("The Connection is null at " + ++connCloseNumb + "st time!");
        }
    }

    public static boolean updateBookDAL(int number, int id, String sql) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int count = 0;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, number);
            preparedStatement.setInt(2, id);
            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(preparedStatement, connection);
        }
        return count > 0;
    }

    public static Book selectBook(String name) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Book book = null;
        String sql = "SELECT * FROM books WHERE name = ?;";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                book = new Book(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5),
                        resultSet.getDouble(6)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(resultSet, preparedStatement, connection);
        }
        return book;
    }

    public static ArrayList<Product> getProductArrayList(String id) {
        ArrayList<Product> productArrayList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT prodId, prodName, prodPrice, prodNumber FROM orders WHERE orderlistId = ?";
        try {
            connection = JdbcUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                productArrayList.add(new Product(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getInt(4)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(resultSet, preparedStatement, connection);
        }
        return productArrayList;
    }
}