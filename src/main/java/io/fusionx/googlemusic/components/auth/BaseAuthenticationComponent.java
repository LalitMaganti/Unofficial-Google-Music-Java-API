package io.fusionx.googlemusic.components.auth;

import com.fasterxml.jackson.databind.JsonNode;

import io.fusionx.googlemusic.model.Device;
import io.fusionx.googlemusic.protocol.MobileProtocol;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import io.fusionx.googlemusic.base.SyncClient;
import io.fusionx.googlemusic.util.Util;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Header;
import retrofit.client.Response;
import retrofit.http.GET;

public abstract class BaseAuthenticationComponent implements AuthenticationComponent {

    public static final String ENDPOINT_URL = "https://play.google.com";

    private final MobileProtocol mMobileProtocol;

    private final WebClientAPI mWebClientAPI;

    protected final List<Device> mDevices = new ArrayList<>();

    protected String mAuthToken;

    private String mXtCookie;

    private String mSjsaidCookie;

    public BaseAuthenticationComponent() {
        final RestAdapter.Builder restAdapter = new RestAdapter.Builder()
                .setEndpoint(ENDPOINT_URL);
        mMobileProtocol = restAdapter.build().create(MobileProtocol.class);

        restAdapter.setEndpoint(ENDPOINT_URL);
        mWebClientAPI = restAdapter.build().create(WebClientAPI.class);
    }

    @Override
    public String getAuthorizationHeader() {
        return "GoogleLogin auth=" + mAuthToken;
    }

    public void retrieveDeviceList() {
        // We need the xt and sjsaid cookies for retrieving the device IDs
        if (Util.isEmpty(mSjsaidCookie) || Util.isEmpty(mXtCookie)) {
            loginToWebClient();
        }

        try {
            final Response response = mMobileProtocol
                    .getDeviceIds(getAuthorizationHeader(), mXtCookie, mSjsaidCookie);
            final InputStream inputStream = response.getBody().in();
            final JsonNode rootNode = SyncClient.OBJECT_MAPPER.readTree(inputStream);
            final JsonNode nodes = rootNode.get("settings").get("devices");
            for (final JsonNode node : nodes) {
                mDevices.add(Device.fromJson(SyncClient.OBJECT_MAPPER, node));
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private static String getCookieFromHeader(final String header, final String cookieName) {
        return header.substring(header.indexOf(cookieName) + cookieName.length(),
                header.indexOf(";"));
    }

    private void loginToWebClient() {
        try {
            final Response response = mWebClientAPI.getCookies(getAuthorizationHeader());
            if (response.getStatus() == HttpURLConnection.HTTP_OK) {
                response.getHeaders().forEach(this::getCookies);
            }
        } catch (RetrofitError error) {
            error.printStackTrace(System.out);
        }
    }

    public void getCookies(final Header header) {
        if (header.getValue().startsWith("xt=")) {
            mXtCookie = getCookieFromHeader(header.getValue(), "xt=");
        } else if (header.getValue().startsWith("sjsaid=")) {
            mSjsaidCookie = getCookieFromHeader(header.getValue(), "sjsaid=");
        } else if ("Update-Client-Auth".equals(header.getName())) {
            mAuthToken = header.getValue();
        }
    }

    public Device getRandomMobileDevice() {
        return mDevices.get(0);
    }

    private static interface WebClientAPI {

        @GET("/music/listen")
        public Response getCookies(@retrofit.http.Header("Authorization") final String authToken);
    }
}