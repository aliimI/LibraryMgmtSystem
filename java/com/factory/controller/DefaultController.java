package com.factory.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DefaultController{
    @RequestMapping("/default")
    public String defaultAfterLogin(HttpServletRequest request) {
        if (request.isUserInRole("USER")) {
            return "redirect:/home-page";
        }
        else if(request.isUserInRole("ADMIN")){
            return "redirect:/admin-home-page";
        }
        return "/";
    }

    @RequestMapping("/profile")
    public String userProfilePage(){
        return "profile-page";
    }

    @RequestMapping("/adminprofile")
    public String adminProfilePage(){
        return "admin-profile-page";
    }
}
