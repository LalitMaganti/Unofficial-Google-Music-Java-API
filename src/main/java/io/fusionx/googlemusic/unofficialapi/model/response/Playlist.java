package io.fusionx.googlemusic.unofficialapi.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Playlist {

    @JsonProperty("kind")
    private String mKind;

    @JsonProperty("name")
    private String mName;

    @JsonProperty("deleted")
    private boolean mDeleted;

    @JsonProperty("type")
    private String mType;

    @JsonProperty("lastModifiedTimestamp")
    private String mLastModifiedTimestamp;

    @JsonProperty("recentTimestamp")
    private String mRecentTimestamp;

    @JsonProperty("shareToken")
    private String mShareToken;

    @JsonProperty("ownerProfilePhotoUrl")
    private String mOwnerProfilePhotoUrl;

    @JsonProperty("ownerName")
    private String mOwnerName;

    @JsonProperty("accessControlled")
    private boolean mAccessControlled;

    @JsonProperty("creationTimestamp")
    private String mCreationTimestamp;

    @JsonProperty("id")
    private String mId;

    @JsonProperty("albumArtRef")
    private List<AlbumArt> mAlbumArtRef;

    @JsonProperty("description")
    private String mDescription;

    public String getKind() {
        return mKind;
    }

    public String getName() {
        return mName;
    }

    public boolean getDeleted() {
        return mDeleted;
    }

    public String getType() {
        return mType;
    }

    public String getLastModifiedTimestamp() {
        return mLastModifiedTimestamp;
    }

    public String getRecentTimestamp() {
        return mRecentTimestamp;
    }

    public String getShareToken() {
        return mShareToken;
    }

    public String getOwnerProfilePhotoUrl() {
        return mOwnerProfilePhotoUrl;
    }

    public String getOwnerName() {
        return mOwnerName;
    }

    public boolean getAccessControlled() {
        return mAccessControlled;
    }

    public String getCreationTimestamp() {
        return mCreationTimestamp;
    }

    public String getId() {
        return mId;
    }

    public List<AlbumArt> getAlbumArtRef() {
        return mAlbumArtRef;
    }

    public String getDescription() {
        return mDescription;
    }
}