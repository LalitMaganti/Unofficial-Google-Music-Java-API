package io.fusionx.googlemusic.unofficialapi.protocol;

import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Query;

public interface MobileProtocol {

    @POST("/trackfeed")
    public Response getTrackList(@Header("Authorization") final String authToken,
            @Body final String length);

    @POST("/playlistfeed")
    public Response getAllPlaylists(@Header("Authorization") final String authToken);

    @POST("/plentryfeed")
    public Response getAllPlaylistTracks(@Header("Authorization") final String authToken);

    @GET("/music/mplay")
    public Response getTrackUrl(@Header("Authorization") final String authToken,
            @Header("X-Device-ID") final String deviceId, @Query("opt") final String quality,
            @Query("net") final String networkType, @Query("pt") final String ptm,
            @Query("songid") final String songid);

    @FormUrlEncoded
    @POST("/music/services/loadsettings")
    public Response getDeviceIds(@Header("Authorization") final String authToken,
            @Field("xt") final String xtCookie, @Field("sjsaid") final String sjsaidCookie);
}