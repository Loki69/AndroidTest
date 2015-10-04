package ru.mapkittest.web.tasks;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Павел on 04.10.2015.
 */
class Price {
    public final int price;
    public final String description;
    public final int mediaType;

    private Price(int price, String description, int mediaType) {
        this.price = price;
        this.description = description;
        this.mediaType = mediaType;
    }

    private Price() {
        this.price = -1;
        this.description = "";
        this.mediaType = -1;
    }

    protected static Price getInit(JSONObject priceObject) {
        Price result = null;
        try {
            int price = (priceObject.has("price")) ? (priceObject.getInt("price")) : (-1);
            String description = (priceObject.has("description")) ? (priceObject.getString("description")) : ("");
            int mediaType = (priceObject.has("mediaType")) ? (priceObject.getInt("mediaType")) : (-1);
            result = new Price(price, description, mediaType);
        } catch (JSONException e) {
            e.printStackTrace();
            result = new Price();
        }
        return result;
    }

    protected boolean isEmpty() {
        return this.price == -1 && "".equals(this.description);
    }

}

