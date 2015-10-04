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
import ru.yandex.yandexmapkit.MapController;
import ru.yandex.yandexmapkit.MapView;
import ru.yandex.yandexmapkit.OverlayManager;
import ru.yandex.yandexmapkit.overlay.Overlay;
import ru.yandex.yandexmapkit.overlay.OverlayItem;
import ru.yandex.yandexmapkit.overlay.balloon.BalloonItem;
import ru.yandex.yandexmapkit.utils.GeoPoint;

public class YandexMapKitSampleActivity extends Activity {
    MapController mMapController;
    OverlayManager mOverlayManager;
    private HttpURLConnection con;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.sample2one_head);

        setContentView(R.layout.sample);

        final MapView mapView = (MapView) findViewById(R.id.map);

        mMapController = mapView.getMapController();

        mOverlayManager = mMapController.getOverlayManager();
        // Disable determining the user's location
        mOverlayManager.getMyLocation().setEnabled(false);

        // A simple implementation of map objects
        showObject();

    }

    public void showObject() {

        String url = "http://test.boloid.com:9000/tasks";
        StringBuffer response = new StringBuffer();

        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            int responseCode = con.getResponseCode();


            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            JSONObject jsonRootObject = new JSONObject(response.toString());
            JSONArray jsonArray = jsonRootObject.optJSONArray("tasks");

            //Iterate the jsonArray and print the info of JSONObjects
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
             Log.e("Json",jsonObject.toString());
            }
        }catch(JSONException e){
            e.printStackTrace();
        }
        // Load required resources
        Resources res = getResources();
        // Create a layer of objects for the map
        Overlay overlay = new Overlay(mMapController);

        // Create an object for the layer
        OverlayItem kremlin = new OverlayItem(new GeoPoint(55.752004, 37.617017), res.getDrawable(R.drawable.shop));
        // Create a balloon model for the object
        BalloonItem balloonKremlin = new BalloonItem(this, kremlin.getGeoPoint());
        balloonKremlin.setText("Московский Кремль. Здесь можно ещё много о чём написать.");
//

//        // Add the balloon model to the object
        kremlin.setBalloonItem(balloonKremlin);
        // Add the object to the layer
        overlay.addOverlayItem(kremlin);


        // Add the layer to the map
        mOverlayManager.addOverlay(overlay);

    }
}
