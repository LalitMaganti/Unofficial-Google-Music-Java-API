package io.fusionx.googlemusic.unofficialapi.components.playlist;

import java.util.List;

import io.fusionx.googlemusic.unofficialapi.components.auth.AuthComponent;
import io.fusionx.googlemusic.unofficialapi.model.response.Playlist;
import io.fusionx.googlemusic.unofficialapi.model.response.ResponseContainer;
import retrofit.RestAdapter;
import retrofit.converter.JacksonConverter;
import retrofit.http.Body;
import retrofit.http.Header;
import retrofit.http.POST;

public class RetrofitPlaylistComponent implements PlaylistComponent {

    public static final String MAIN_BASE_URL = "https://www.googleapis.com/sj/v1.5";

    private final AuthComponent mAuthComponent;

    private final PlaylistProtocol mSjProtocol;

    public RetrofitPlaylistComponent(final AuthComponent authComponent) {
        mAuthComponent = authComponent;

        final RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(MAIN_BASE_URL)
                .setConverter(new JacksonConverter())
                .build();
        mSjProtocol = restAdapter.create(PlaylistProtocol.class);
    }

    @Override
    public List<Playlist> getPlaylists() {
        return mSjProtocol.getPlaylists(mAuthComponent.getAuthorizationHeader(), "").getItems();
    }

    public interface PlaylistProtocol {

        @POST("/playlistfeed")
        public ResponseContainer<Playlist> getPlaylists(@Header("Authorization") final String
                authToken, @Body final String empty);
    }
}