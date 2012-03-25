package org.springframework.social.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.social.security.model.User;
import org.springframework.social.security.security.SecurityUtil;
import org.springframework.social.security.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.NativeWebRequest;

@Controller
@RequestMapping("/signup")
public class SignUpController {

    private static final String FORM_VIEW = "signup";
    private static final String SUCCESS_VIEW = "redirect:/";

    @Autowired private UserService userService;
    @Autowired private SignInAdapter signInAdapter;

    @RequestMapping(method = RequestMethod.GET)
    public String getForm(NativeWebRequest request, @ModelAttribute User user) {
        String view = FORM_VIEW;

        // check if user is signing in via Spring Social
        Connection<?> connection = ProviderSignInUtils.getConnection(request);
        if (connection != null) {
            // populate new User from social connection profile data
            UserProfile userProfile = connection.fetchUserProfile();
            user.setUsername(userProfile.getUsername());

            // you may want to perform validation on the User object
            // to check if you are able to get all the data your app requires
            // from the social connection profile data
            // (providers may not provide usernames, or email addresses, etc.)
            // if the User is not valid for your app you will need to show
            // your registration form view with some notice that more data is required

            // in this case no other data is required so the user is registered and signed in
            userService.registerUser(user);

            // finish social signup/login
            ProviderSignInUtils.handlePostSignUp(user.getUsername(), request);

            // sign the user in and send them to the user home page
            signInAdapter.signIn(user.getUsername(), connection, request);
            view = SUCCESS_VIEW;
        }

        return view;
    }
    
    @RequestMapping(method = RequestMethod.POST) 
    public String registerUser(@ModelAttribute User user) {
        userService.registerUser(user);

        // sign user in
        SecurityUtil.signInUser(user);

        return SUCCESS_VIEW;
    }
}
