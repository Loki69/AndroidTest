package ru.mapkittest.web.tasks;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.mapkittest.web.connec.WebConnector;

/**
 * Created by Павел on 04.10.2015.
 */
public class Tasks {
    private static Tasks singleton;
    private final String urlData = "http://test.boloid.com:9000/tasks";
    private List<Task> tasks = new ArrayList<Task>();

    private Tasks() {

    }

    private void init() throws MalformedURLException, JSONException {
        WebConnector wc = new WebConnector(this.urlData);
        parsingToTasks(wc.getDataFromPage());
    }

    private void parsingToTasks(String data) throws JSONException {
        JSONObject jsonRootObject = new JSONObject(data);
        JSONArray jsonArray = jsonRootObject.optJSONArray("tasks");
        for (int i = 0; i < jsonArray.length(); i++) {
            tasks.add();
            Log.e("Json", jsonObject.toString());
        }
    }

    public static Tasks getInit() {
        if (singleton == null) {
            singleton = new Tasks();
        }
        return singleton;
    }

}
