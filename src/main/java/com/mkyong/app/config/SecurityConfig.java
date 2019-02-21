package com.mkyong.app.config;

import com.mkyong.app.code.CustomTokenServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;

@Configuration
@EnableResourceServer
@Order(2)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomTokenServices customTokenService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/User/**").authenticated()
                .antMatchers("/Home/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin().loginPage("/login")
                .and()
                .cors();
    }

    public AuthenticationManager authenticationManagerBean() throws Exception {
        OAuth2AuthenticationManager authenticationManager = new OAuth2AuthenticationManager();
        authenticationManager.setTokenServices(customTokenService.tokenService());
        return authenticationManager;
    }
}