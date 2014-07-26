package io.fusionx.googlemusic.base;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.fusionx.googlemusic.components.auth.AuthenticationComponent;
import io.fusionx.googlemusic.components.track.TrackComponent;

public interface SyncClient extends AuthenticationComponent, TrackComponent {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static final String MAIN_ENDPOINT_URL = "https://www.googleapis.com/sj/v1.5/";
}