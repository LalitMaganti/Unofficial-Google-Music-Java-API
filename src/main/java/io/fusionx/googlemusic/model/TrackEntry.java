package io.fusionx.googlemusic.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TrackEntry {

    // FOR DB USE ONLY
    public long databaseid;

    // JSON
    public String kind;

    public String id;

    public String clientId;

    public String playlistId;

    public String absolutePosition;

    public String trackId;

    public String creationTimestamp;

    public String lastModifiedTimestamp;

    public boolean deleted;

    public int source;

    public static TrackEntry fromJson(final ObjectMapper mapper, final JsonNode node) throws
            JsonProcessingException {
        return mapper.treeToValue(node, TrackEntry.class);
    }
}