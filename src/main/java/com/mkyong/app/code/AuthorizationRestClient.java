package com.mkyong.app.code;

import com.alibaba.fastjson.JSONObject;
import com.mkyong.app.module.AuthorizeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by fengdaqing on 2018/4/9.
 */
@Component
public class AuthorizationRestClient {

    private String token;

    public String getToken() {
        return token;
    }

    @Autowired
    public CustomTokenServices customTokenServices;

    @Autowired
    private AuthorizationProperty authorizationProperty;

    public OAuth2Authentication GetAuthorization(AuthorizeDto user) {
        OAuth2Authentication auth = null;
        try {
            String tokenUrl = combineTokenUrl(user);
            if (!StringUtils.isEmpty((tokenUrl))) {
                token = getAccessToken(tokenUrl);
                if (!StringUtils.isEmpty(token)) {
                    auth = customTokenServices.tokenService().loadAuthentication(token);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return auth;
    }

    private String combineTokenUrl(AuthorizeDto user) {
        String checkTokenUrl = "";
        if (user == null
                || StringUtils.isEmpty(user.getUsername())
                || StringUtils.isEmpty(user.getPassword())) {
            return checkTokenUrl;
        }
        checkTokenUrl = String.format(" http://%s:%s/oauth/token" +
                        "?grant_type=password" +
                        "&username=%s" +
                        "&password=%s" +
                        "&client_id=%s" +
                        "&client_secret=%s ",
                authorizationProperty.getAuthServerHost()
                , authorizationProperty.getAuthServerPort()
                , user.getUsername()
                , user.getPassword()
                , authorizationProperty.getAppId()
                , authorizationProperty.getAppSecret()
        );
        return checkTokenUrl;
    }

    private String getAccessToken(String Url) throws IOException {
        String token = "";
        try {
            URL tokenUrl = new URL(Url);
            HttpURLConnection httpConnection = (HttpURLConnection) tokenUrl.openConnection();
            httpConnection.setRequestMethod("POST");
            httpConnection.setRequestProperty("Accept", "application/json");
            if (httpConnection.getResponseCode() != 200) {
                throw new RuntimeException("HTTP GET Request Failed with Error code : "
                        + httpConnection.getResponseCode());
            }
            BufferedReader responseBuffer = new BufferedReader(new InputStreamReader(
                    (httpConnection.getInputStream())));
            StringBuilder sbuild = new StringBuilder();
            String output;
            while ((output = responseBuffer.readLine()) != null) {
                sbuild.append(output);
            }
            JSONObject jsonObj = JSONObject.parseObject(sbuild.toString());
            Object tokenObj = jsonObj.get("access_token");
            if (tokenObj != null) {
                token = tokenObj.toString();
            }
        } catch (IOException ex) {
            throw ex;
        }
        return token;
    }
}
