package com.mkyong.app.config;

import com.mkyong.app.Program;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

/**
 * Created by fengdaqing on 2018/4/4.
 */
@Configuration
public class ServletInitializerConfig extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        setRegisterErrorPageFilter(false);
        return application.sources(Program.class);
    }
}
