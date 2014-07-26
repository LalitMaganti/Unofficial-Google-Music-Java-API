package io.fusionx.googlemusic.sync;

import io.fusionx.googlemusic.model.Track;

import java.io.IOException;
import java.util.List;

import io.fusionx.googlemusic.components.track.BaseTrackComponent;
import io.fusionx.googlemusic.components.auth.ClientLoginAuthenticationComponent;

public class SyncMobileClient implements SyncClient {

    private final ClientLoginAuthenticationComponent mAuthenticationComponent;

    private final BaseTrackComponent mTrackComponent;

    public SyncMobileClient() {
        mAuthenticationComponent = new ClientLoginAuthenticationComponent();
        mTrackComponent = new BaseTrackComponent(this);
    }

    @Override
    public boolean login(final String username, final String password) {
        final boolean result = mAuthenticationComponent.login(username, password);
        if (result) {
            mAuthenticationComponent.retrieveDeviceList();
        }
        return result;
    }

    @Override
    public String getAuthorizationHeader() {
        return mAuthenticationComponent.getAuthorizationHeader();
    }

    @Override
    public boolean isLoggedIn() {
        return mAuthenticationComponent.isLoggedIn();
    }

    @Override
    public List<Track> getAllTracks() throws IOException {
        return mTrackComponent.getAllTracks();
    }

    @Override
    public String getTrackUrl(Track track) {
        return mTrackComponent.getTrackUrl(track);
    }
}