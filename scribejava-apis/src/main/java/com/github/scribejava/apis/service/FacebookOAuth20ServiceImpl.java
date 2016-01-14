package com.github.scribejava.apis.service;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.model.AbstractRequest;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthConfig;
import com.github.scribejava.core.model.OAuthConstants;
import com.github.scribejava.core.oauth.OAuth20Service;

public class FacebookOAuth20ServiceImpl extends OAuth20Service {

    public FacebookOAuth20ServiceImpl(DefaultApi20 api, OAuthConfig config) {
        super(api, config);
    }

    @Override
    protected <T extends AbstractRequest> T createRefreshTokenRequest(OAuth2AccessToken refreshToken, T request) {
        final OAuthConfig config = getConfig();
        request.addParameter(OAuthConstants.CLIENT_ID, config.getApiKey());
        request.addParameter(OAuthConstants.CLIENT_SECRET, config.getApiSecret());
        //facebook uses the access_token as a refresh_token. fun, right?
        request.addParameter("fb_exchange_token", refreshToken.getAccessToken());
        request.addParameter("grant_type", "fb_exchange_token");
        return request;
    }
}
