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

/**
 * Controller for the home screen for logged in users.
 */
@Controller
public class HomeController {

    @Autowired
    private SocialUserService socialUserService;

    @RequestMapping("/")
    public ModelAndView home() {

        ModelAndView modelAndView = new ModelAndView("home");

        User user = SecurityUtil.getLoggedInUser();

        modelAndView.addObject(user);

        // check if the account is connected to any social network accounts
        ConnectionRepository connectionRepository = socialUserService.createConnectionRepository(user.getUsername());

        // only Twitter and Facebook accounts are supported in this app, so check for those specifically
        List connections = connectionRepository.findConnections("twitter");

        // Spring Social allows multiple social network accounts to be connected to one local account
        // however in this app we are only providing a UI for users to connect one Twitter and one Facebook account

        // so, anything in the list means that the user has connected a Twitter account
        if (!connections.isEmpty()) {
            modelAndView.addObject("twitterConnected", "true");
        }
        // same thing, for Facebook
        connections = connectionRepository.findConnections("facebook");
        if (!connections.isEmpty()) {
            modelAndView.addObject("facebookConnected", "true");
        }

        return modelAndView;
    }
}
