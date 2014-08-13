package io.fusionx.googlemusic.unofficialapi;

import com.google.common.util.concurrent.ListenableFuture;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import io.fusionx.googlemusic.unofficialapi.client.GuavaClient;
import io.fusionx.googlemusic.unofficialapi.client.ProgrammableGuavaClient;
import io.fusionx.googlemusic.unofficialapi.model.response.Playlist;
import io.fusionx.googlemusic.unofficialapi.model.response.Track;
import io.fusionx.googlemusic.unofficialapi.util.FutureUtil;
import java8.util.stream.StreamSupport;

public class Example {

    public static void main(final String[] args) throws IOException {
        if (args.length == 1 && args[0].equals("async")) {
            async();
        } else {
            sync();
        }
    }

    private static void async() throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        final GuavaClient mobileClient = new ProgrammableGuavaClient();

        System.out.print("Username: ");
        final String username = reader.readLine();

        System.out.print("Password: ");
        final String password = reader.readLine();

        final ListenableFuture<Boolean> future = mobileClient.login(username, password).async();
        FutureUtil.addCallback(future, r -> Example.getTracks(mobileClient, reader, r),
                Throwable::printStackTrace);
    }

    private static void getTracks(final GuavaClient mobileClient,
            final BufferedReader reader, final Boolean result) {
        if (!result) {
            return;
        }

        final ListenableFuture<List<Track>> future = mobileClient.getAllTracks().async();
        FutureUtil.addCallback(future, r -> Example.getStreamUrl(mobileClient, reader, r),
                Throwable::printStackTrace);
    }

    private static void getStreamUrl(final GuavaClient mobileClient, final BufferedReader reader,
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
        final ListenableFuture<String> future = mobileClient.getTrackUrl(track).async();
        FutureUtil.addCallback(future, System.out::println, Throwable::printStackTrace);
    }

    private static void sync() throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        final GuavaClient mobileClient = new ProgrammableGuavaClient();

        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.print("Username: ");
            final String username = reader.readLine();

            System.out.print("Password: ");
            final String password = reader.readLine();

            loggedIn = mobileClient.login(username, password).sync();

            if (!loggedIn) {
                System.out.println("Login failed. Try again");
            }
        }
        final List<Playlist> playlist = mobileClient.getPlaylists().sync();
        playlist.stream().map(Playlist::getName).forEach(System.out::println);

        final List<Track> list = mobileClient.getAllTracks().sync();

        System.out.print("Name of track to find: ");
        final String searchParam = reader.readLine();

        Track track = findTrack(list, searchParam);
        System.out.println(mobileClient.getTrackUrl(track).sync());

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