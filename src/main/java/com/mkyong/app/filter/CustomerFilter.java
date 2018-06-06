package com.mkyong.app.filter;


import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by fengdaqing on 2018/4/4.
 */
@Component
@ServletComponentScan
@WebFilter(urlPatterns = "/User/*",filterName = "UserFilter")
public class CustomerFilter  implements Filter {

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain
            filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) srequest;
        filterChain.doFilter(request, sresponse);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }
}

