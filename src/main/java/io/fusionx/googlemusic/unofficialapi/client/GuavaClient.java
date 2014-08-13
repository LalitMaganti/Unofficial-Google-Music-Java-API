package io.fusionx.googlemusic.unofficialapi.client;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import io.fusionx.googlemusic.unofficialapi.model.response.Playlist;
import io.fusionx.googlemusic.unofficialapi.model.response.Track;
import io.fusionx.googlemusic.unofficialapi.request.GuavaRequest;
import retrofit.http.Body;

public interface GuavaClient {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public String getAuthorizationHeader();

    public boolean isLoggedIn();

    // Auth component start
    public GuavaRequest<Boolean> login(final String username, final String password);
    // Auth component end

    // Track component start
    public GuavaRequest<List<Track>> getAllTracks();

    public GuavaRequest<String> getTrackUrl(final Track track);
    // Track component end

    // Playlist component start
    public GuavaRequest<List<Playlist>> getPlaylists();
    // Playlist component end
}