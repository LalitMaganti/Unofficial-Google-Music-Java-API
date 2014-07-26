package io.fusionx.googlemusic;

import com.google.common.util.concurrent.ListenableFuture;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.ExecutionException;

import io.fusionx.googlemusic.async.AsyncClient;
import io.fusionx.googlemusic.async.AsyncMobileClient;
import io.fusionx.googlemusic.model.Track;
import io.fusionx.googlemusic.sync.SyncClient;
import io.fusionx.googlemusic.sync.SyncMobileClient;
import io.fusionx.googlemusic.util.FutureUtil;
import io.fusionx.googlemusic.util.Util;
import java8.util.stream.StreamSupport;

public class Example {

    public static void main(final String[] args) throws IOException {
        if (args.length == 1 && args[0].equals("sync")) {
            sync();
        } else {
            async();
        }
    }

    private static void async() throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        final AsyncClient mobileClient = new AsyncMobileClient();

        System.out.print("Username: ");
        final String username = reader.readLine();

        System.out.print("Password: ");
        final String password = reader.readLine();

        final ListenableFuture<Boolean> loginFuture = mobileClient.login(username, password);
        FutureUtil.addCallback(loginFuture, r -> Example.getTracks(mobileClient, reader, r),
                Throwable::printStackTrace);
    }

    private static void getTracks(final AsyncClient mobileClient,
            final BufferedReader reader, final Boolean result) {
        if (!result) {
            return;
        }

        final ListenableFuture<List<Track>> future = mobileClient.getAllTracks();
        FutureUtil.addCallback(future, r -> Example.getStreamUrl(mobileClient, reader, r),
                Throwable::printStackTrace);
    }

    private static void getStreamUrl(final AsyncClient mobileClient, final BufferedReader reader,
            final List<Track> list) {
        System.out.print("Name of track to find: ");
        final String searchParam;
        try {
            searchParam = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Track track = findTrack(list, searchParam);
        final ListenableFuture<String> future = mobileClient.getTrackUrl(track);
        FutureUtil.addCallback(future, System.out::println, Throwable::printStackTrace);
    }

    private static void sync() throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        final SyncClient mobileClient = new SyncMobileClient();

        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.print("Username: ");
            final String username = reader.readLine();

            System.out.print("Password: ");
            final String password = reader.readLine();

            loggedIn = mobileClient.login(username, password);

            if (!loggedIn) {
                System.out.println("Login failed. Try again");
            }
        }
        final List<Track> list = mobileClient.getAllTracks();

        System.out.print("Name of track to find: ");
        final String searchParam = reader.readLine();

        Track track = findTrack(list, searchParam);
        System.out.println(mobileClient.getTrackUrl(track));

        promptUser();
        String line;
        while ((line = reader.readLine()) != null && !line.equals("quit")) {
            if (!line.equals("repeat")) {
                track = findTrack(list, searchParam);
            }
            System.out.println(mobileClient.getTrackUrl(track));

            promptUser();
        }
    }

    private static Track findTrack(final List<Track> list, String searchParam) {
        return StreamSupport.stream(list).filter(t -> t.getTitle().equals(searchParam))
                .findFirst().orElse(null);
    }

    private static void promptUser() {
        System.out.print("Type another track to find, repeat to get the refresh the track URL"
                + " or quit to exit: ");
    }
}