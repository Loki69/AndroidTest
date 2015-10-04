package ru.mapkittest.web.tasks;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Павел on 04.10.2015.
 */
public class Price {
    private Integer price;
    private String description;
    private Integer mediaType;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMediaType() {
        return mediaType;
    }

    public void setMediaType(Integer mediaType) {
        this.mediaType = mediaType;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}

