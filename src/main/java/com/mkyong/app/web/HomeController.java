package com.mkyong.app.web;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/Home")
public class HomeController {

    @RequestMapping(value = {"/", "/welcome/**"}, method = RequestMethod.GET)
    public ModelAndView welcomePage(Principal principal) {
        Collection<? extends GrantedAuthority> rst = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security Custom Login Form");
        model.addObject("message", "This is welcome page!");
        model.setViewName("Home/welcome");
        return model;
    }
    @RequestMapping(value="/hello")
    @ResponseBody
    public String helloWorld() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> rst = auth.getAuthorities();
        List<String> right = rst.stream().map(item -> item.getAuthority()).collect(Collectors.toList());
        String rights = String.join(",", right);
        return String.format("Hello Welcome %s. User Right: %s .", auth.getName(), rights);
    }
}