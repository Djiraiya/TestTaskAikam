package org.example.TestTaskAikam.Parse;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.TestTaskAikam.DB.SqlQueries;
import org.example.TestTaskAikam.Model.CustomerForStat;
import org.example.TestTaskAikam.Model.ResultForStat;
import org.json.JSONObject;

import java.io.*;
import java.sql.Date;
import java.sql.SQLException;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class FileStatReader {

    public void statRead(String inputPath, String outputPath) {

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

        List<CustomerForStat> customersForStats = new ArrayList<>();

        if (jsonObject.has("startDate")) {
            Date startDate = Date.valueOf(jsonObject.getString("startDate"));
            Date endDate = Date.valueOf(jsonObject.getString("endDate"));
            Period days = Period.between(startDate.toLocalDate(), endDate.toLocalDate());
            int totalDays = days.getDays() + 1;

            if (totalDays < 0) {
                new FileErrorWriter().writeError("Неправильный диапозон дат", outputPath);
            } else {
                try {
                    customersForStats = new SqlQueries().findByDate(startDate, endDate);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                ResultForStat resultForStats = new ResultForStat(totalDays, customersForStats);

                ObjectMapper mapper = new ObjectMapper();
                File file = new File(outputPath);
                try {
                    mapper.writerWithDefaultPrettyPrinter().writeValue(file, resultForStats);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            new FileErrorWriter().writeError("Неправильная структура входного JSON файла", outputPath);
        }
    }
}
