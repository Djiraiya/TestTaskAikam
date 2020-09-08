package org.example.TestTaskAikam;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;


public class Main {
    public static void main(String[] args) throws IOException {

        String path = "src\\main\\resources\\input.json";

        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        StringBuilder stringBuilder = new StringBuilder();
        String line = "";
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }

        JSONObject jsonObject = new JSONObject(stringBuilder.toString());

        JSONArray criterias = jsonObject.getJSONArray("criterias");

        for (Object object : criterias) {
            JSONObject criteria = (JSONObject) object;

            if (criteria.keySet().contains("lastName")) {
                String lastName = criteria.getString("lastName");
                System.out.println(lastName);
            }

            if (criteria.keySet().contains("productName")) {
                String productName = criteria.getString("productName");
                int minTimes = criteria.getInt("minTimes");
                System.out.println(productName + " " + minTimes);
            }

            if (criteria.keySet().contains("minExpenses")) {
                int minExpenses = criteria.getInt("minExpenses");
                int maxExpenses = criteria.getInt("maxExpenses");
                System.out.println(minExpenses + " " + maxExpenses);
            }

            if (criteria.keySet().contains("badCustomers")) {
                int badCustomers = criteria.getInt("badCustomers");
                System.out.println(badCustomers);
            }
        }
    }
}
