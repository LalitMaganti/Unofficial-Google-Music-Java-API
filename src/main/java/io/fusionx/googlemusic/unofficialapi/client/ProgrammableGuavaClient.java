package io.fusionx.googlemusic.unofficialapi.client;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;

import io.fusionx.googlemusic.unofficialapi.components.auth.AuthComponent;
import io.fusionx.googlemusic.unofficialapi.components.auth.RetrofitClientLoginAuthComponent;
import io.fusionx.googlemusic.unofficialapi.components.track.RetrofitTrackComponent;
import io.fusionx.googlemusic.unofficialapi.components.track.TrackComponent;
import io.fusionx.googlemusic.unofficialapi.model.Track;
import io.fusionx.googlemusic.unofficialapi.request.GuavaRequest;

public class ProgrammableGuavaClient implements GuavaClient {

    private final ListeningExecutorService mService;

    private final AuthComponent mAuthComponent;

    private final TrackComponent mTrackComponent;

    public ProgrammableGuavaClient() {
        mService = MoreExecutors.listeningDecorator(Executors.newSingleThreadExecutor());

        mAuthComponent = new RetrofitClientLoginAuthComponent();
        mTrackComponent = new RetrofitTrackComponent(mAuthComponent);
    }

    @Override
    public String getAuthorizationHeader() {
        return mAuthComponent.getAuthorizationHeader();
    }

    @Override
    public boolean isLoggedIn() {
        return mAuthComponent.isLoggedIn();
    }

    @Override
    public GuavaRequest<Boolean> login(final String username, final String password) {
        return new GuavaRequest<>(() -> mAuthComponent.login(username, password), mService);
    }

    @Override
    public GuavaRequest<List<Track>> getAllTracks() {
        return new GuavaRequest<>(() -> {
            try {
                return mTrackComponent.getAllTracks();
            } catch (IOException e) {
                // TODO - use UncheckedIOException
                throw new RuntimeException(e);
            }
        }, mService);
    }

    @Override
    public GuavaRequest<String> getTrackUrl(final Track track) {
        return new GuavaRequest<>(() -> mTrackComponent.getTrackUrl(track), mService);
    }
}