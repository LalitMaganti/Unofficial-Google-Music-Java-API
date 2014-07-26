package io.fusionx.googlemusic.components.auth;

import retrofit.RetrofitError;

public interface AuthenticationComponent {

    public String getAuthorizationHeader();

    boolean isLoggedIn();

    public boolean login(final String username, final String password) throws RetrofitError;
}