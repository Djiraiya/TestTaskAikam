package org.example.TestTaskAikam.Parse;

import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class FileErrorWriter {

    public void writeError(String message, String outputPath) {
        JSONObject object = new JSONObject();
        object.put("type", "error");
        object.put("message", message);
        String json = object.toString(1);
        try {
            FileWriter file = new FileWriter(outputPath);
            file.write(json);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
