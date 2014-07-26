package io.fusionx.googlemusic.components.auth;

import java.util.List;

import io.fusionx.googlemusic.util.Util;
import java8.util.stream.StreamSupport;
import retrofit.RestAdapter;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

public class ClientLoginAuthenticationComponent extends BaseAuthenticationComponent {

    private static final String ENDPOINT_URL = "https://www.google.com/";

    private final ClientLoginAPI mClientLoginAPI;

    private String mSid;

    private String mLsid;

    private boolean mLoggedIn;

    public ClientLoginAuthenticationComponent() {
        final RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(ENDPOINT_URL)
                .build();
        mClientLoginAPI = restAdapter.create(ClientLoginAPI.class);
    }

    @Override
    public boolean login(final String username, final String password) {
        final List<String> list = Util.parseRetrofitResponse(mClientLoginAPI.login(username,
                password, "HOSTED_OR_GOOGLE", "sj", null, null, null));
        parseClientLoginResponse(list);
        return mLoggedIn;
    }

    @Override
    public boolean isLoggedIn() {
        return mLoggedIn;
    }

    private void parseClientLoginResponse(final List<String> response) {
        StreamSupport.stream(response).map(string -> string.split("=", 2)).forEach(array -> {
            switch (array[0]) {
                case "SID":
                    mSid = array[1];
                    break;
                case "LSID":
                    mLsid = array[1];
                    break;
                case "Auth":
                    mAuthToken = array[1];
                    mLoggedIn = true;
                    break;
            }
        });
    }

    private static interface ClientLoginAPI {

        @FormUrlEncoded
        @POST("/accounts/ClientLogin")
        public Response login(@Field("Email") final String email, @Field("Passwd") final String
                password, @Field("accountType") final String accountType,
                @Field("service") final String service, @Field("source") final String source,
                @Field("loginToken") final String loginToken, @Field("logincaptcha") final String
                loginCaptcha);
    }
}