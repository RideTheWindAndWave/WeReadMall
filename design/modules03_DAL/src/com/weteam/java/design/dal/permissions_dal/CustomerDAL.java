package com.weteam.java.design.dal.permissions_dal;

import com.weteam.java.design.dal.util.JdbcUtils;
import com.weteam.java.design.entity.Book;
import com.weteam.java.design.entity.OrderList;
import com.weteam.java.design.entity.Product;
import com.weteam.java.design.entity.ShoppingCart;
import com.weteam.java.design.entity.permissions_entity.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

public class CustomerDAL {

    public static ShoppingCart getShoppingCartDAL(String id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ShoppingCart shoppingCart = null;
        LinkedList<Product> productsLinkedList = new LinkedList<>();
        String sql = "SELECT prodId, name, price, buyNumber FROM shoppingcart WHERE userId = ?;";
        try {
            connection = JdbcUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                productsLinkedList.addFirst(new Product(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getInt(4)
                ));
            }
            shoppingCart = new ShoppingCart(id, productsLinkedList);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(resultSet, preparedStatement, connection);
        }
        return shoppingCart;
    }

    public static boolean insertShoppingCartDAL(String userId, Product product) {
        int count = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO shoppingcart VALUES (?, ?, ?, ?, ?);";
        try {
            connection = JdbcUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userId);
            preparedStatement.setInt(2, product.id);
            preparedStatement.setString(3, product.name);
            preparedStatement.setDouble(4, product.price);
            preparedStatement.setInt(5, product.buyNumber);
            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(preparedStatement, connection);
        }
        return count > 0;
    }

    public static Product getProductDAL(String name, int number) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Product product = null;
        String sql = "SELECT id, name, price FROM books WHERE name = ?;";
        try {
            connection = JdbcUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                product = new Product(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        number
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(resultSet, preparedStatement, connection);
        }
        return product;
    }

    public static boolean updateShoppingCartDAL(String id, String name, int updateNumber) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE shoppingcart SET buyNumber = ? WHERE userId = ? AND name = ?;";
        int count = 0;
        try {
            connection = JdbcUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, updateNumber);
            preparedStatement.setString(2, id);
            preparedStatement.setString(3, name);
            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(preparedStatement, connection);
        }
        return count > 0;
    }

    public static Book selectBookDAL(String name) {
        return JdbcUtils.selectBook(name);
    }

    public static boolean deleteProductDAL(String userId, int id) {
        int count = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM shoppingcart WHERE userId = ? AND prodId = ?";
        try {
            connection = JdbcUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userId);
            preparedStatement.setInt(2, id);
            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count > 0;
    }

    public static boolean insertOrderList(OrderList orderList) {
        boolean boo = insertOrders(orderList);
        int count = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO orderlist VALUES(?, ?, ?, ?, ?)";
        try {
            connection = JdbcUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, orderList.getId());
            preparedStatement.setString(2, orderList.getUserId());
            preparedStatement.setString(3, orderList.getOrderState());
            preparedStatement.setString(4, orderList.getOrderTime());
            preparedStatement.setString(5, orderList.getSuccessfulTime());
            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(preparedStatement, connection);
        }
        return boo && count > 0;
    }

    private static boolean insertOrders(OrderList orderList) {
        boolean boo = true;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        for (int i = 0; i < orderList.getProductArrayList().size(); i++) {
            String sql = "INSERT INTO orders VALUES(?, ?, ? ,?, ?)";
            try {
                connection = JdbcUtils.getConnection();
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, orderList.getId());
                preparedStatement.setInt(2, orderList.getProductArrayList().get(i).id);
                preparedStatement.setString(3, orderList.getProductArrayList().get(i).name);
                preparedStatement.setDouble(4, orderList.getProductArrayList().get(i).price);
                preparedStatement.setInt(5, orderList.getProductArrayList().get(i).buyNumber);
                if (preparedStatement.executeUpdate() <= 0) {
                    boo = false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                JdbcUtils.close(preparedStatement, connection);
            }
        }
        return boo;
    }

    public static ArrayList<OrderList> selectOrderLists(Role role) {
        ArrayList<OrderList> orderListArrayList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT id, orderState, orderTime, successfulTime FROM orderlist WHERE userId = ?";
        try {
            connection = JdbcUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, role.getId());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orderListArrayList.add(new OrderList(
                        resultSet.getString(1),
                        role.getId(),
                        JdbcUtils.getProductArrayList(resultSet.getString(1)),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(resultSet, preparedStatement, connection);
        }
        return orderListArrayList;
    }

    public static boolean updateOrderList(String orderListId, String successfulTime){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE orderlist SET successfulTime = ?, orderState = ? WHERE id = ?;";
        int count = 0;
        try {
            connection = JdbcUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, successfulTime);
            preparedStatement.setString(2, OrderList.COMPLETED);
            preparedStatement.setString(3, orderListId);
            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(preparedStatement, connection);
        }
        return count > 0;
    }

    public static String getOrderState(String id){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String orderState = "";
        String sql = "SELECT orderState FROM orderlist WHERE id = ?;";
        try {
            connection = JdbcUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                orderState = resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(resultSet, preparedStatement, connection);
        }
        return orderState;
    }
}
