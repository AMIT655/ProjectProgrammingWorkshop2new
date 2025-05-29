package org.example;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            HttpResponse<String> response = Unirest.get("https://app.seker.live/fm1/get-points").asString();
            JSONArray array = new JSONArray(response.getBody());

            List<Point> points = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                int x = (int) obj.getDouble("x");
                int y = (int) obj.getDouble("y");
                boolean isWhite = obj.getBoolean("white");
                points.add(new Point(x, y, isWhite));
            }

            JFrame window = new JFrame("Maze Viewer with Image");
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.getContentPane().add(new PointPanel(points));
            window.pack();
            window.setLocationRelativeTo(null);
            window.setVisible(true);

        } catch (UnirestException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




