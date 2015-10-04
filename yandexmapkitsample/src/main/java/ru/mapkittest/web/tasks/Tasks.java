package ru.mapkittest.web.tasks;

import android.app.Activity;
import android.content.res.Resources;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.mapkittest.R;
import ru.mapkittest.YandexMapKitSampleActivity;
import ru.mapkittest.web.connec.WebConnector;
import ru.yandex.yandexmapkit.overlay.OverlayItem;
import ru.yandex.yandexmapkit.overlay.balloon.BalloonItem;
import ru.yandex.yandexmapkit.utils.GeoPoint;

/**
 * Created by Павел on 04.10.2015.
 */
public class Tasks {
    private static Tasks singleton;
    private final String urlData = "http://test.boloid.com:9000/tasks";
    private List<Task> tasks = new ArrayList<Task>();

    private Tasks() {
        try {
            init();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void init() throws MalformedURLException, JSONException {
        WebConnector wc = new WebConnector(this.urlData);
        parsingToTasks(wc.getDataFromPage());
    }

    private void parsingToTasks(String data) throws JSONException {
        JSONObject jsonRootObject = new JSONObject(data);
        JSONArray jsonArray = jsonRootObject.optJSONArray("tasks");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Task task = Task.getInit(jsonObject);
            if (!task.isEmpty()) {
                tasks.add(task);
            }
            Log.e("Json", jsonObject.toString());
        }
    }

    public static Tasks getInit() {
        if (singleton == null) {
            singleton = new Tasks();
        }
        return singleton;
    }

    public List<OverlayItem> getArrayMapItem(YandexMapKitSampleActivity activity) {
        List<OverlayItem> result = new ArrayList();
        for (Task task : tasks) {
            result.add(generatOverlayItem(task,activity));
        }
        return result;
    }

    private OverlayItem generatOverlayItem(Task task, YandexMapKitSampleActivity activity) {
        Resources res = activity.getResources();
        OverlayItem kremlin = new OverlayItem(task.location, res.getDrawable(R.drawable.shop));
        return kremlin;
    }

    private String formatingMesseg(String title, String longText, List<Price> prises, Date date, String locationText) {
        return String.format("\t%s\n%s\n%s\nDate:%s\nLocation:", title, longText, date.toString(), locationText);
    }

    private String listPriseToString(List<Price> prises) {
        StringBuilder sb = new StringBuilder("");
        if (!prises.isEmpty()) {
            sb.append("Prices:");
            for (Price price : prises) {
                sb.append("\t\tPrice:").append(price.price);
                sb.append("\t\t\"Description:").append(price.description);
            }
        }
        return sb.toString();
    }

}
