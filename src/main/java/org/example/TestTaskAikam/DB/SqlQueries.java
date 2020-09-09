package org.example.TestTaskAikam.DB;

import org.example.TestTaskAikam.Model.CustomerForStat;
import org.example.TestTaskAikam.Model.Customers;
import org.example.TestTaskAikam.Model.Purchases;
import org.example.TestTaskAikam.Model.ResultForStat;

import java.sql.*;
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

    public List<CustomerForStat> findByDate(Date startDate, Date endDate) throws SQLException {

        List<CustomerForStat> customerForStats = new ArrayList<>();
        Connection connection = DBConnect.getConnect();

        PreparedStatement statement = connection.prepareStatement("SELECT customers.customer_id, customers.lastname, customers.firstName, SUM(products.price) AS total\n" +
                "FROM products\n" +
                "JOIN purchases ON purchases.product_id = products.product_id\n" +
                "JOIN customers ON customers.customer_id = purchases.customer_id\n" +
                "AND purchases.date BETWEEN ? AND ? AND extract('ISODOW' from date) < 6\n" +
                "GROUP BY customers.customer_id, customers.lastName, customers.firstName\n" +
                "ORDER BY total DESC;");

        statement.setDate(1, startDate);
        statement.setDate(2, endDate);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int customerId = resultSet.getInt("customer_id");
            CustomerForStat customers = new CustomerForStat(resultSet.getString("lastname"), resultSet.getString("firstname"));
            int totalExpenses = resultSet.getInt("total");
            customers.setPurchases(findPurchasesByCustomerId(customerId, startDate, endDate));
            customers.setTotalExpenses(totalExpenses);
            customerForStats.add(customers);
        }
        resultSet.close();
        statement.close();
        return customerForStats;
    }

    public List<Purchases> findPurchasesByCustomerId(int customerId, Date startDate, Date endDate) throws SQLException {

        List<Purchases> purchasesList = new ArrayList<>();
        Connection connection = DBConnect.getConnect();

        PreparedStatement statement = connection.prepareStatement("SELECT products.name, SUM(products.price) AS expenses\n" +
                "FROM  products\n" +
                "JOIN purchases ON purchases.product_id = products.product_id\n" +
                "WHERE purchases.customer_id = ?\n" +
                "AND purchases.date BETWEEN ? AND ? AND extract('ISODOW' from date) < 6\n" +
                "GROUP BY products.name\n" +
                "ORDER BY expenses DESC;");

        statement.setInt(1, customerId);
        statement.setDate(2, startDate);
        statement.setDate(3, endDate);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Purchases purchases = new Purchases(resultSet.getString("name"), resultSet.getInt("expenses"));
            purchasesList.add(purchases);
        }
        resultSet.close();
        statement.close();
        return purchasesList;
    }

}
