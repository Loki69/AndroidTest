package ru.mapkittest.web.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Павел on 04.10.2015.
 */
public class Tasks {
    private static  Tasks singleton;
    private final String urlData = "http://test.boloid.com:9000/tasks";
    private List<Task> tasks = new ArrayList<Task>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    private Tasks() {
    }

    public static Tasks getInit() {
        if(singleton == null){
            singleton = new Tasks();
        }
        return singleton;
    }

}
