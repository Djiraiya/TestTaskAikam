package org.example.TestTaskAikam.DB;

import org.example.TestTaskAikam.Model.Customers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlQueries {

    public List<Customers> findByLastName(String lastName) throws SQLException {
        List<Customers> customers = new ArrayList<>();

        Connection connection = DBConnect.getConnect();

        PreparedStatement statement = connection.prepareStatement("SELECT firstname, lastname FROM customers WHERE lastName = ?");

        statement.setString(1, lastName);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Customers customers1 = new Customers(resultSet.getString("firstname"), resultSet.getString("lastname"));
            customers.add(customers1);
        }
        resultSet.close();
        statement.close();

        return customers;
    }

    public List<Customers> findByProductNameAndMinTimes(String productName, int minTimes) throws SQLException {

        List<Customers> customers = new ArrayList<>();

        Connection connection = DBConnect.getConnect();

        PreparedStatement statement = connection.prepareStatement("SELECT products.name, customers.firstname," +
                " customers.lastname\n" +
                "FROM products\n" +
                "JOIN purchases ON products.product_id = purchases.product_id\n" +
                "JOIN customers ON customers.customer_id = purchases.customer_id\n" +
                "WHERE products.name = ?\n" +
                "GROUP BY products.name, purchases.customer_id, customers.firstname, customers.lastname\n" +
                "HAVING COUNT(purchases.product_id) >= ?;");

        statement.setString(1, productName);
        statement.setInt(2, minTimes);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Customers customers1 = new Customers(resultSet.getString("firstname"), resultSet.getString("lastname"));
            customers.add(customers1);
        }
        resultSet.close();
        statement.close();
        return customers;
    }

    public List<Customers> findByBetweenSum(int minExpenses, int maxExpenses) throws SQLException {
        List<Customers> customers = new ArrayList<>();

        Connection connection = DBConnect.getConnect();

        PreparedStatement statement = connection.prepareStatement("SELECT customers.firstname, customers.lastname\n" +
                "FROM products\n" +
                "JOIN purchases ON products.product_id = purchases.product_id\n" +
                "JOIN customers ON customers.customer_id = purchases.customer_id\n" +
                "GROUP BY customers.firstname, customers.lastname\n" +
                "HAVING SUM(price) BETWEEN ? AND ?;");

        statement.setInt(1, minExpenses);
        statement.setInt(2, maxExpenses);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Customers customers1 = new Customers(resultSet.getString("firstname"), resultSet.getString("lastname"));
            customers.add(customers1);
        }
        resultSet.close();
        statement.close();
        return customers;
    }

    public List<Customers> findByBadCustomers(int badCustomers) throws SQLException {
        List<Customers> customers = new ArrayList<>();

        Connection connection = DBConnect.getConnect();

        PreparedStatement statement = connection.prepareStatement("select COUNT(products.product_id) as counter, customers.firstname, customers.lastname\n" +
                "FROM products\n" +
                "RIGHT JOIN purchases ON products.product_id = purchases.product_id\n" +
                "RIGHT JOIN customers ON customers.customer_id = purchases.customer_id\n" +
                "GROUP BY customers.firstname, customers.lastname\n" +
                "ORDER BY counter LIMIT ?");

        statement.setInt(1, badCustomers);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Customers customers1 = new Customers(resultSet.getString("firstname"), resultSet.getString("lastname"));
            customers.add(customers1);
        }
        resultSet.close();
        statement.close();
        return customers;
    }

}
