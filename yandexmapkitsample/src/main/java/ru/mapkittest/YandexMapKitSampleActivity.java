package ru.mapkittest;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ru.mapkittest.balloonoverlay.one.ImageBalloonItem;
import ru.mapkittest.web.tasks.Tasks;
import ru.yandex.yandexmapkit.MapController;
import ru.yandex.yandexmapkit.MapView;
import ru.yandex.yandexmapkit.OverlayManager;
import ru.yandex.yandexmapkit.overlay.Overlay;
import ru.yandex.yandexmapkit.overlay.OverlayItem;
import ru.yandex.yandexmapkit.overlay.balloon.BalloonItem;
import ru.yandex.yandexmapkit.utils.GeoPoint;

public class YandexMapKitSampleActivity extends Activity {
    private MapController mMapController;
    private OverlayManager mOverlayManager;
    private HttpURLConnection con;
    private Tasks tasks;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.sample2one_head);
        setContentView(R.layout.sample);
        Tasks tasks = Tasks.getInit();
        final MapView mapView = (MapView) findViewById(R.id.map);
        mMapController = mapView.getMapController();
        mOverlayManager = mMapController.getOverlayManager();
        mOverlayManager.getMyLocation().setEnabled(false);

        showListObject();

    }

    public void showListObject() {
        Resources res = getResources();
        Overlay overlay = new Overlay(mMapController);
        try {
            tasks.getArrayMapItem(this);
            for (OverlayItem item : tasks.getArrayMapItem(this)) {
                overlay.addOverlayItem(item);
            }
            mOverlayManager.addOverlay(overlay);
        }catch (Error e ){
            e.printStackTrace();
        }

    }
}
