package org.springframework.social.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.security.model.User;
import org.springframework.social.security.security.SecurityUtil;
import org.springframework.social.security.service.SocialUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {

    @Autowired private SocialUserService socialUserService;

    @RequestMapping("/")
    public ModelAndView home() {

        ModelAndView modelAndView = new ModelAndView("home");

        User user = SecurityUtil.getUser();

        modelAndView.addObject(user);

        // check if the account is connected to Facebook or TWitter accounts
        ConnectionRepository connectionRepository = socialUserService.createConnectionRepository(user.getUsername());

        List connections = connectionRepository.findConnections("twitter");
        if (!connections.isEmpty()) {
            modelAndView.addObject("twitterConnected", "true");
        }
        connections = connectionRepository.findConnections("facebook");
        if (!connections.isEmpty()) {
            modelAndView.addObject("facebookConnected", "true");
        }

        return modelAndView;
    }
}
