package io.fusionx.googlemusic.components.track;

import io.fusionx.googlemusic.model.Track;
import retrofit.RetrofitError;

import java.io.IOException;
import java.util.List;

public interface TrackComponent {

    public List<Track> getAllTracks() throws RetrofitError, IOException;

    public String getTrackUrl(final Track track) throws RetrofitError;
}