package ru.mapkittest.web.tasks;

import ru.yandex.yandexmapkit.overlay.OverlayItem;
import ru.yandex.yandexmapkit.utils.GeoPoint;

/**
 * Created by Павел on 04.10.2015.
 */
public class TextReturn {
    public final String masseg;
    public final GeoPoint geoPoint;

    public TextReturn(GeoPoint geoPoint, String masseg) {
        this.masseg = masseg;
        this.geoPoint = geoPoint;
    }
}
