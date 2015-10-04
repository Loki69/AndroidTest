package ru.mapkittest;


import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.widget.Button;

import ru.mapkittest.web.tasks.Tasks;
import ru.yandex.yandexmapkit.MapController;
import ru.yandex.yandexmapkit.MapView;
import ru.yandex.yandexmapkit.OverlayManager;
import ru.yandex.yandexmapkit.overlay.Overlay;
import ru.yandex.yandexmapkit.overlay.OverlayItem;


public class YandexMapActivity extends Activity implements OnClickListener {
    private MapController mMapController;
    private OverlayManager mOverlayManager;
    private Overlay mOverlay;
    private Tasks tasks;
    private Button button1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.sample2one_head);
        setContentView(R.layout.sample);
        button1 = (Button) findViewById(R.id.button);
        button1.setOnClickListener(this);
        final MapView mapView = (MapView) findViewById(R.id.map);
        mMapController = mapView.getMapController();
        mOverlayManager = mMapController.getOverlayManager();
        mOverlayManager.getMyLocation().setEnabled(false);

        showListObject();
    }

    public void showListObject() {
        initData();
        mOverlay = new Overlay(mMapController);
        for (OverlayItem item : tasks.getArrayMapItem(this)) {
            mOverlay.addOverlayItem(item);
        }
        mOverlayManager.addOverlay(mOverlay);
    }

    private void initData() {
        if (tasks == null) {
            this.tasks = Tasks.getInit();
        } else {
            this.tasks.refreshData();
            mOverlayManager.destroyed();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                showListObject();
                break;
        }
    }
}
