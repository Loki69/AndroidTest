package ru.mapkittest.web.tasks;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.yandex.yandexmapkit.utils.GeoPoint;

/**
 * Created by Павел on 04.10.2015.
 */
public class Task {
    public final GeoPoint location;
    public  List<Price> prices = new ArrayList<Price>();
    public final String ID;
    public final String locationText;
    public final int price;
    public final String title;
    public final String longText;
    public final boolean translation;
    public final String text;
    public final int zoomLevel;
    public final Date date;
    public final Date endDate;

    private Task(GeoPoint location, String ID, String locationText, int price, String title, String longText, Boolean translation, String text, int zoomLevel, Date date, Date endDate) {
        this.location = location;
        this.ID = ID;
        this.locationText = locationText;
        this.price = price;
        this.title = title;
        this.longText = longText;
        this.translation = translation;
        this.text = text;
        this.zoomLevel = zoomLevel;
        this.date = date;
        this.endDate = endDate;
    }

    private Task() {
        this.location = new GeoPoint(0, 0);
        this.ID = "";
        this.locationText = "";
        this.price = 0;
        this.title = "";
        this.longText = "";
        this.translation = false;
        this.text = "";
        this.zoomLevel = 0;
        this.date = new Date();
        this.endDate = new Date();
    }

    public static Task getInit(JSONObject task) {
        Task result = null;
        try {
            GeoPoint location = (task.has("lon") && task.has("lat")) ? (new GeoPoint(task.getDouble("lon"), task.getDouble("lat"))) : (new GeoPoint(0, 0));
            String ID = (task.has("ID")) ? (task.getString("ID")) : ("");
            String locationText = (task.has("locationText")) ? (task.getString("locationText")) : ("");
            int price = (task.has("price")) ? (task.getInt("price")) : (0);
            String title = (task.has("title")) ? (task.getString("title")) : ("");
            String longText = (task.has("longText")) ? (task.getString("longText")) : ("");
            boolean translation = (task.has("translation")) ? (task.getBoolean("translation")) : (false);
            String text = (task.has("text")) ? (task.getString("text")) : ("");
            int zoomLevel = (task.has("zoomLevel")) ? (task.getInt("zoomLevel")) : (9);
            Date date = (task.has("date")) ? (new Date(task.getString("date"))) : (new Date());
            Date endDate = (task.has("endDate")) ? (new Date(task.getString("endDate"))) : (new Date());
            result = new Task(location,ID,locationText,price,title,longText,translation,text,zoomLevel,date,endDate);
        } catch (JSONException e) {
            e.printStackTrace();
            result = new Task();
        }
        return result;
    }
    public boolean isEmpty(){
        return new GeoPoint(0,0).equals(this.location) && this.ID.isEmpty();
    }
}