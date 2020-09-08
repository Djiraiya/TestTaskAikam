package org.example.TestTaskAikam;

import org.example.TestTaskAikam.DB.SqlQueries;
import org.example.TestTaskAikam.Model.Customers;
import org.example.TestTaskAikam.Model.Result;
import org.example.TestTaskAikam.Parse.FileSearchReader;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        new FileSearchReader().searchRead("src\\main\\resources\\input.json", "src\\main\\resources\\output.json");
    }
}
