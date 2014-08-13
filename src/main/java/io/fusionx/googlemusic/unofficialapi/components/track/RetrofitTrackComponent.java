package io.fusionx.googlemusic.unofficialapi.components.track;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import io.fusionx.googlemusic.unofficialapi.client.GuavaClient;
import io.fusionx.googlemusic.unofficialapi.components.auth.AuthComponent;
import io.fusionx.googlemusic.unofficialapi.model.response.Track;
import io.fusionx.googlemusic.unofficialapi.protocol.MobileProtocol;
import java8.util.stream.StreamSupport;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Header;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;
import retrofit.converter.JacksonConverter;

public class RetrofitTrackComponent implements TrackComponent {

    public static final String MAIN_BASE_URL = "https://www.googleapis.com/sj/v1.5";

    private final AuthComponent mAuthComponent;

    private final MobileProtocol mSjProtocol;

    private final MobileProtocol mAndroidClientProtocol;

    public RetrofitTrackComponent(final AuthComponent authComponent) {
        mAuthComponent = authComponent;

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(MAIN_BASE_URL)
                .setConverter(new JacksonConverter())
                .build();
        mSjProtocol = restAdapter.create(MobileProtocol.class);

        restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://android.clients.google.com")
                .setConverter(new JacksonConverter())
                .build();
        mAndroidClientProtocol = restAdapter.create(MobileProtocol.class);
    }

    @Override
    public List<Track> getAllTracks() throws IOException {
        return mSjProtocol.getTrackList(mAuthComponent.getAuthorizationHeader(), "").getItems();
    }

    @Override
    public String getTrackUrl(final Track track) {
        final boolean old = HttpURLConnection.getFollowRedirects();
        try {
            HttpURLConnection.setFollowRedirects(false);
            mAndroidClientProtocol.getTrackUrl(mAuthComponent.getAuthorizationHeader(),
                    "333c60412226c96f", "med", "mob", "e", track.getId());
            throw RetrofitError.unexpectedError("TODO", null);
        } catch (final RetrofitError error) {
            final Response response = error.getResponse();
            if (response.getStatus() == HttpURLConnection.HTTP_MOVED_TEMP) {
                return StreamSupport.stream(response.getHeaders())
                        .filter(header -> "Location".equals(header.getName()))
                        .map(Header::getValue).findAny().orElse(null);
            } else {
                throw error;
            }
        } finally {
            HttpURLConnection.setFollowRedirects(old);
        }
    }
}