package io.fusionx.googlemusic.model;

import java.util.List;

public class Track { // implements Parcelable {

    /*public static final Parcelable.Creator<Track> CREATOR = new Parcelable.Creator<Track>() {
        public Track createFromParcel(Parcel in) {
            return new Track(in);
        }

        public Track[] newArray(int size) {
            return new Track[size];
        }
    };*/

    public String kind;

    public String id;

    public String clientId;

    public String creationTimestamp;

    public String lastModifiedTimestamp;

    public String recentTimestamp;

    public boolean deleted;

    public String title;

    public String artist;

    public String composer;

    public String album;

    public String albumArtist;

    public int year;

    public String comment;

    public int trackNumber;

    public String genre;

    public String durationMillis;

    public int beatsPerMinute;

    public List<AlbumArt> albumArtRef;

    public List<AlbumArtistPhoto> artistArtRef;

    public int playCount;

    public int totalTrackCount;

    public int discNumber;

    public int totalDiscCount;

    public String rating;

    public String estimatedSize;

    public String trackType;

    public String storeId;

    public String albumId;

    public List<String> artistId;

    public String nid;

    public String contentType;

    public Track() {
    }

    /*
    private Track(Parcel in) {
        databaseid = in.readLong();

        kind = in.readString();
        id = in.readString();
        clientId = in.readString();
        creationTimestamp = in.readString();
        lastModifiedTimestamp = in.readString();
        recentTimestamp = in.readString();
        deleted = in.readInt() == 1;
        title = in.readString();
        artist = in.readString();
        composer = in.readString();
    }*/

    @Override
    public String toString() {
        return title;
    }

    public int describeContents() {
        return 0;
    }

    /*
    public void writeToParcel(Parcel out, int flags) {
        out.writeLong(databaseid);

        out.writeString(kind);
        out.writeString(id);
        out.writeString(clientId);
        out.writeString(creationTimestamp);
        out.writeString(lastModifiedTimestamp);
        out.writeString(recentTimestamp);
        out.writeInt(deleted ? 1 : 0);
        out.writeString(title);
        out.writeString(artist);
        out.writeString(composer);
    }*/

    public String getTitle() {
        return title;
    }
}