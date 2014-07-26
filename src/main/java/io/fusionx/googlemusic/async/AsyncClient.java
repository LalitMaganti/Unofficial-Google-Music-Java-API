package io.fusionx.googlemusic.async;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

import io.fusionx.googlemusic.model.Track;

public interface AsyncClient {

    public String getAuthorizationHeader();

    boolean isLoggedIn();

    public ListenableFuture<Boolean> login(final String username, final String password);

    public ListenableFuture<List<Track>> getAllTracks();

    public ListenableFuture<String> getTrackUrl(final Track track);
}