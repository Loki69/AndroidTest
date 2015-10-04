package ru.mapkittest.web.tasks;

import ru.yandex.yandexmapkit.utils.GeoPoint;

/**
 * Created by Павел on 04.10.2015.
 */
public class TextReturn {
    private final String masseg;
    private final GeoPoint gp;

    public TextReturn(String masseg, GeoPoint gp) {
        this.masseg = masseg;
        this.gp = gp;
    }
}
