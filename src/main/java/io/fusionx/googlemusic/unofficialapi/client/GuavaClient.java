package io.fusionx.googlemusic.unofficialapi.client;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import io.fusionx.googlemusic.unofficialapi.model.Track;
import io.fusionx.googlemusic.unofficialapi.request.GuavaRequest;

public interface GuavaClient {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public String getAuthorizationHeader();

    public boolean isLoggedIn();

    public GuavaRequest<Boolean> login(final String username, final String password);

    public GuavaRequest<List<Track>> getAllTracks();

    public GuavaRequest<String> getTrackUrl(final Track track);
}