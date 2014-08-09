package io.fusionx.googlemusic.unofficialapi.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Device {

    private int date;

    private String id;

    private String name;

    private String type;

    private String model;

    private String manufacturer;

    private String carrier;

    private long lastUsedMs;

    public static Device fromJson(final ObjectMapper mapper, final JsonNode node) throws
            JsonProcessingException {
        return mapper.treeToValue(node, Device.class);
    }

    public int getDate() {
        return date;
    }

    public String getId() {
        // This line below is due to the server sending back 0x<device_id>
        return id.substring(2);
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getModel() {
        return model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getCarrier() {
        return carrier;
    }

    public long getLastUsedMs() {
        return lastUsedMs;
    }
}
