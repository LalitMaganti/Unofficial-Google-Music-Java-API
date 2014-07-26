package io.fusionx.googlemusic.async;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.List;
import java.util.concurrent.Executors;

import io.fusionx.googlemusic.model.Track;
import io.fusionx.googlemusic.sync.SyncClient;
import io.fusionx.googlemusic.sync.SyncMobileClient;

public class AsyncMobileClient implements AsyncClient {

    private final ListeningExecutorService mListeningExecutorService;

    private final SyncClient mSyncClient;

    public AsyncMobileClient() {
        mListeningExecutorService = MoreExecutors.listeningDecorator(Executors
                .newSingleThreadExecutor());
        mSyncClient = new SyncMobileClient();
    }

    @Override
    public String getAuthorizationHeader() {
        return mSyncClient.getAuthorizationHeader();
    }

    @Override
    public boolean isLoggedIn() {
        return mSyncClient.isLoggedIn();
    }

    @Override
    public ListenableFuture<Boolean> login(final String username, final String password) {
        return mListeningExecutorService.submit(() -> mSyncClient.login(username, password));
    }

    @Override
    public ListenableFuture<List<Track>> getAllTracks() {
        return mListeningExecutorService.submit(mSyncClient::getAllTracks);
    }

    @Override
    public ListenableFuture<String> getTrackUrl(final Track track) {
        return mListeningExecutorService.submit(() -> mSyncClient.getTrackUrl(track));
    }
}