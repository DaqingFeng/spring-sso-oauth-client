package com.mkyong.app.code;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by fengdaqing on 2018/4/12.
 */
@Component
@PropertySource(value = "classpath:application.properties")
public class AuthorizationProperty {

    @Value("${config.oauth2.clientID}")
    private String appId;

    @Value("${config.oauth2.secret}")
    private String appSecret;

    @Value("${config.oauth2.host}")
    private String authServerHost;

    @Value("${config.oauth2.port}")
    private int authServerPort;

    public String getAppId() {
        return appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public String getAuthServerHost() {
        return authServerHost;
    }

    public int getAuthServerPort() {
        return authServerPort;
    }

}
