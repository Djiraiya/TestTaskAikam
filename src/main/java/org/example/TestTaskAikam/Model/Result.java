package org.example.TestTaskAikam.Model;

import org.example.TestTaskAikam.Model.Customers;
import org.json.JSONObject;

import java.util.List;

public class Result {
    private JSONObject criteria;
    private List<Customers> results;

    public Result(JSONObject criteria, List<Customers> results) {
        this.criteria = criteria;
        this.results = results;
    }

    public JSONObject getCriteria() {
        return criteria;
    }

    public List<Customers> getResults() {
        return results;
    }
}
