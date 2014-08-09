package io.fusionx.googlemusic.unofficialapi.components.track;

import java.io.IOException;
import java.util.List;

import io.fusionx.googlemusic.unofficialapi.model.Track;
import retrofit.RetrofitError;

public interface TrackComponent {

    public List<Track> getAllTracks() throws RetrofitError, IOException;

    public String getTrackUrl(final Track track) throws RetrofitError;
}