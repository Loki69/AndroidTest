package ru.mapkittest.web.tasks;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.yandex.yandexmapkit.utils.GeoPoint;

/**
 * Created by Павел on 04.10.2015.
 */
public class Task {
    public final GeoPoint location;
    public List<Price> prices = new ArrayList<Price>();
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

    private Task(GeoPoint location, String ID, String locationText, int price, String title, String longText, Boolean translation, String text, int zoomLevel, Date date, Date endDate, List<Price> prices) {
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
        this.prices = prices;
    }

    private Task() {
        this.location = new GeoPoint(-1, -1);
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
        this.prices = new ArrayList<Price>();
    }

    public static Task getInit(JSONObject task) {
        try {

            GeoPoint location = (task.has("lon") && task.has("lat")) ? (new GeoPoint(task.getDouble("lon"), task.getDouble("lat"))) : (new GeoPoint(-1, -1));
            String ID = (task.has("ID")) ? (task.getString("ID")) : ("");
            String locationText = (task.has("locationText")) ? (task.getString("locationText")) : ("");
            int price = (task.has("price")) ? (task.getInt("price")) : (-1);
            String title = (task.has("title")) ? (task.getString("title")) : ("");
            String longText = (task.has("longText")) ? (task.getString("longText")) : ("");
            boolean translation = (task.has("translation")) ? (task.getBoolean("translation")) : (false);
            String text = (task.has("text")) ? (task.getString("text")) : ("");
            int zoomLevel = (task.has("zoomLevel")) ? (task.getInt("zoomLevel")) : (9);
            Date date = (task.has("date")) ? (new Date(task.getLong("date"))) : (new Date());
            Date endDate = (task.has("endDate")) ? (new Date(task.getLong(("endDate")))) : (new Date());
            List<Price> prices = (task.has("prices")) ? (getPrices(task.getJSONArray("prices"))) : (new ArrayList<Price>());

            return new Task(location, ID, locationText, price, title, longText, translation, text, zoomLevel, date, endDate, prices);
        } catch (JSONException e) {
            e.printStackTrace();
            return new Task();
        }
    }

    private static List<Price> getPrices(JSONArray listPrises) {
        List<Price> prices = new ArrayList<Price>();
        for (int i = 0; i < listPrises.length(); i++) {
            JSONObject jsonObject = null;
            try {
                jsonObject = listPrises.getJSONObject(i);
                Price prise = Price.getInit(jsonObject);
                if (!prise.isEmpty()) {
                    prices.add(prise);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.e("Json", jsonObject.toString());
        }
        return prices;
    }

    public boolean isEmpty() {
        return new GeoPoint(-1, -1).equals(this.location) && "".equals(this.ID) && (prices.size() == -1) && (price == -1);
    }
}