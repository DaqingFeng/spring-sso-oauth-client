package com.mkyong.app.code;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.stereotype.Component;

import javax.swing.plaf.PanelUI;

/**
 * Created by fengdaqing on 2018/4/27.
 */
@Component
@PropertySource(value = "classpath:application.properties")
public class CustomTokenServices  {

    @Value("${config.oauth2.clientID}")
    private String appId;

    @Value("${config.oauth2.secret}")
    private String appSecret;

    @Value("${config.oauth2.checkTokenUri}")
    private String authCheckTokenUrl;

    public ResourceServerTokenServices tokenService() {
        RemoteTokenServices tokenServices = new RemoteTokenServices();
        tokenServices.setClientId(appId);
        tokenServices.setClientSecret(appSecret);
        tokenServices.setCheckTokenEndpointUrl(authCheckTokenUrl);
        return tokenServices;
    }
}
