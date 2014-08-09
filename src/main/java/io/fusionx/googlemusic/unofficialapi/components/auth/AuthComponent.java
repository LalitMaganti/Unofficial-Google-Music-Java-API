package io.fusionx.googlemusic.unofficialapi.components.auth;

import retrofit.RetrofitError;

public interface AuthComponent {

    public String getAuthorizationHeader();

    boolean isLoggedIn();

    public boolean login(final String username, final String password) throws RetrofitError;
}