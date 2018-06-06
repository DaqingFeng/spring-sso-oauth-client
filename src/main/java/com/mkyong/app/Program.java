package com.mkyong.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;


/**
 * Created by fengdaqing on 2018/4/2.
 */
@SpringBootApplication
public class Program extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Program.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Program.class, args);
    }
}

