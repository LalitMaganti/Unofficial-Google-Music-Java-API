package io.fusionx.googlemusic.unofficialapi.components.auth;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import io.fusionx.googlemusic.unofficialapi.client.GuavaClient;
import io.fusionx.googlemusic.unofficialapi.model.response.Device;
import io.fusionx.googlemusic.unofficialapi.protocol.MobileProtocol;
import io.fusionx.googlemusic.unofficialapi.util.Util;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Header;
import retrofit.client.Response;
import retrofit.converter.JacksonConverter;
import retrofit.http.GET;

public abstract class RetrofitBaseAuthComponent implements AuthComponent {

    public static final String ENDPOINT_URL = "https://play.google.com";

    protected final List<Device> mDevices = new ArrayList<>();

    private final MobileProtocol mMobileProtocol;

    private final WebClientAPI mWebClientAPI;

    protected String mAuthToken;

    private String mXtCookie;

    private String mSjsaidCookie;

    public RetrofitBaseAuthComponent() {
        final RestAdapter.Builder builder = new RestAdapter.Builder()
                .setConverter(new JacksonConverter())
                .setEndpoint(ENDPOINT_URL);
        mMobileProtocol = builder.build().create(MobileProtocol.class);

        mWebClientAPI = builder
                .setEndpoint(ENDPOINT_URL)
                .setConverter(new JacksonConverter())
                .build().create(WebClientAPI.class);
    }

    private static String getCookieFromHeader(final String header, final String cookieName) {
        return header.substring(header.indexOf(cookieName) + cookieName.length(),
                header.indexOf(";"));
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
            final JsonNode rootNode = GuavaClient.OBJECT_MAPPER.readTree(inputStream);
            final JsonNode nodes = rootNode.get("settings").get("devices");
            for (final JsonNode node : nodes) {
                mDevices.add(Device.fromJson(GuavaClient.OBJECT_MAPPER, node));
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
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

    public Device getFirstMobileDevice() {
        return mDevices.get(0);
    }

    private static interface WebClientAPI {

        @GET("/music/listen")
        public Response getCookies(@retrofit.http.Header("Authorization") final String authToken);
    }
}