package io.fusionx.googlemusic.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;

public class Playlist {

    // JSON
    // FOR DB USE ONLY
    public String kind;

    public String id;

    public String creationTimestamp;

    public String lastModifiedTimestamp;

    public String recentTimestamp;

    public boolean deleted;

    public String name;

    public String type;

    public String shareToken;

    public String ownerName;

    public String ownerProfilePhotoUrl;

    public boolean accessControlled;

    public List<AlbumArtistPhoto> albumArtRef;

    public String description;

    // Internal use
    public HashMap<String, TrackEntry> tracks = new HashMap<String, TrackEntry>();

    public static Playlist fromJson(final ObjectMapper mapper, final JsonNode node) throws
            JsonProcessingException {
        return mapper.treeToValue(node, Playlist.class);
    }
}