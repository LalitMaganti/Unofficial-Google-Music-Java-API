package io.fusionx.googlemusic.unofficialapi.model;

import java.util.List;

public class Track {

    private String kind;

    private String id;

    private String clientId;

    private String creationTimestamp;

    private String lastModifiedTimestamp;

    private String recentTimestamp;

    private boolean deleted;

    private String title;

    private String artist;

    private String composer;

    private String album;

    private String albumArtist;

    private int year;

    private String comment;

    private int trackNumber;

    private String genre;

    private String durationMillis;

    private int beatsPerMinute;

    private List<AlbumArt> albumArtRef;

    private List<AlbumArtistPhoto> artistArtRef;

    private int playCount;

    private int totalTrackCount;

    private int discNumber;

    private int totalDiscCount;

    private String rating;

    private String estimatedSize;

    private String trackType;

    private String storeId;

    private String albumId;

    private List<String> artistId;

    private String nid;

    private String contentType;

    public String getKind() {
        return kind;
    }

    public String getId() {
        return id;
    }

    public String getClientId() {
        return clientId;
    }

    public String getCreationTimestamp() {
        return creationTimestamp;
    }

    public String getLastModifiedTimestamp() {
        return lastModifiedTimestamp;
    }

    public String getRecentTimestamp() {
        return recentTimestamp;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getComposer() {
        return composer;
    }

    public String getAlbum() {
        return album;
    }

    public String getAlbumArtist() {
        return albumArtist;
    }

    public int getYear() {
        return year;
    }

    public String getComment() {
        return comment;
    }

    public int getTrackNumber() {
        return trackNumber;
    }

    public String getGenre() {
        return genre;
    }

    public String getDurationMillis() {
        return durationMillis;
    }

    public int getBeatsPerMinute() {
        return beatsPerMinute;
    }

    public List<AlbumArt> getAlbumArtRef() {
        return albumArtRef;
    }

    public List<AlbumArtistPhoto> getArtistArtRef() {
        return artistArtRef;
    }

    public int getPlayCount() {
        return playCount;
    }

    public int getTotalTrackCount() {
        return totalTrackCount;
    }

    public int getDiscNumber() {
        return discNumber;
    }

    public int getTotalDiscCount() {
        return totalDiscCount;
    }

    public String getRating() {
        return rating;
    }

    public String getEstimatedSize() {
        return estimatedSize;
    }

    public String getTrackType() {
        return trackType;
    }

    public String getStoreId() {
        return storeId;
    }

    public String getAlbumId() {
        return albumId;
    }

    public List<String> getArtistId() {
        return artistId;
    }

    public String getNid() {
        return nid;
    }

    public String getContentType() {
        return contentType;
    }
}