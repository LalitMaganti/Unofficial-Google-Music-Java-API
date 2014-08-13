package io.fusionx.googlemusic.unofficialapi.components.playlist;

import java.util.List;

import io.fusionx.googlemusic.unofficialapi.model.response.Playlist;

public interface PlaylistComponent {

    public List<Playlist> getPlaylists();
}