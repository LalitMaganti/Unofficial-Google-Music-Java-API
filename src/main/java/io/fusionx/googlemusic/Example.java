package io.fusionx.googlemusic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import io.fusionx.googlemusic.base.SyncClient;
import io.fusionx.googlemusic.base.SyncMobileClient;
import io.fusionx.googlemusic.model.Track;
import java8.util.stream.StreamSupport;

public class Example {

    public static void main(final String[] args) throws IOException {
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