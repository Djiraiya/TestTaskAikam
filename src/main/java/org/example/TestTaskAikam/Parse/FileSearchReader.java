package org.example.TestTaskAikam.Parse;

import org.example.TestTaskAikam.DB.SqlQueries;
import org.example.TestTaskAikam.Model.Customers;
import org.example.TestTaskAikam.Model.Result;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FileSearchReader {

    public void searchRead(String inputPath, String outputPath) {

        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(inputPath));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = new JSONObject(stringBuilder.toString());

        if (jsonObject.has("criterias")) {

            JSONArray criterias = jsonObject.getJSONArray("criterias");
            List<Result> criteriasList = new ArrayList<>();

            for (Object object : criterias) {
                JSONObject criteria = (JSONObject) object;

                if (criteria.keySet().contains("lastName")) {
                    String lastName = criteria.getString("lastName");
                    List<Customers> results = null;
                    try {
                        results = new SqlQueries().findByLastName(lastName);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    criteriasList.add(new Result(criteria, results));
                }

                if (criteria.keySet().contains("productName")) {
                    String productName = criteria.getString("productName");
                    int minTimes = criteria.getInt("minTimes");
                    List<Customers> customersList = null;
                    try {
                        customersList = new SqlQueries().findByProductNameAndMinTimes(productName, minTimes);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    criteriasList.add(new Result(criteria, customersList));
                }

                if (criteria.keySet().contains("minExpenses")) {
                    int minExpenses = criteria.getInt("minExpenses");
                    int maxExpenses = criteria.getInt("maxExpenses");
                    List<Customers> customersList = null;
                    try {
                        customersList = new SqlQueries().findByBetweenSum(minExpenses, maxExpenses);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    criteriasList.add(new Result(criteria, customersList));
                }

                if (criteria.keySet().contains("badCustomers")) {
                    int badCustomers = criteria.getInt("badCustomers");
                    List<Customers> customersList = null;
                    try {
                        customersList = new SqlQueries().findByBadCustomers(badCustomers);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    criteriasList.add(new Result(criteria, customersList));
                }
            }
            JSONObject result = new JSONObject();
            result.put("type", "search");
            result.put("results", criteriasList);
            String json = result.toString(1);
            try {
                FileWriter file = new FileWriter(outputPath);
                file.write(json);
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            new FileErrorWriter().writeError("Неправильная структура входного JSON файла", outputPath);
        }
    }

}
