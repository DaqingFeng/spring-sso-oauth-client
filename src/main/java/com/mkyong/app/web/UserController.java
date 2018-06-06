package com.mkyong.app.web;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Collection;

/**
 * Created by fengdaqing on 2018/4/3.
 */
@Controller
@RequestMapping(value = "/User")
public class UserController {

    @RequestMapping(value = "/Index", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    public ModelAndView Index(@RequestParam(value = "Name", required = false) String Name,
                              @RequestParam(value = "token",required = false) String token,
                              Principal user) {
        Collection<? extends GrantedAuthority> rst = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        ModelAndView model = new ModelAndView("User/UserInfo");
        if (user != null && !StringUtils.isEmpty(user.getName())) {
            Name = user.getName();
        }
        model.addObject("Token", token);
        model.addObject("Name", Name);
        return model;
    }
}
