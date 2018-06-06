package com.mkyong.app.web;

import com.mkyong.app.code.AuthorizationRestClient;
import com.mkyong.app.module.AuthorizeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

/**
 * Created by fengdaqing on 2018/4/3.
 */
@Controller
public class LoginController {

    @Autowired
    private AuthorizationRestClient authorizationRestClient;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        Collection<? extends GrantedAuthority> rst = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        ModelAndView model = new ModelAndView();
        model.setViewName("Login/sign");
        return model;
    }

    @RequestMapping(value = "/loginOut")
    public ModelAndView loginOut() {
        ModelAndView model = new ModelAndView();
        model.setViewName("Login/signOut");
        return model;
    }

    @RequestMapping(value = "/authorize", method = RequestMethod.POST)
    public String authorize(AuthorizeDto user, HttpServletRequest request,
                            HttpServletResponse response, RedirectAttributes redirectAttributes) throws Exception {
        OAuth2Authentication oauth2 = authorizationRestClient.GetAuthorization(user);
        String token = authorizationRestClient.getToken();
        if (oauth2 != null) {
            SecurityContextHolder.getContext().setAuthentication(oauth2.getUserAuthentication());
        }
        Cookie cookie=new Cookie("authorization", token);
        cookie.setMaxAge(100);
        response.addCookie(cookie);
        redirectAttributes.addAttribute("access_token", token);
        return "redirect:/User/Index";
    }
}
