package io.fusionx.googlemusic.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Device {

    public int date;

    public String id;

    public String name;

    public String type;

    public String model;

    public String manufacturer;

    public String carrier;

    public long lastUsedMs;

    public static Device fromJson(final ObjectMapper mapper, final JsonNode node) throws
            JsonProcessingException {
        final Device device = mapper.treeToValue(node, Device.class);
        // This line below is due to the server sending back 0x<device_id>
        device.id = device.id.substring(2);
        return device;
    }
}
