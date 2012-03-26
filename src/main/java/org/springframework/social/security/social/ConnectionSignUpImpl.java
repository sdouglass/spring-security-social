package org.springframework.social.security.social;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.security.model.User;
import org.springframework.social.security.service.UserService;

public class ConnectionSignUpImpl implements ConnectionSignUp {
    @Autowired
    private UserService userService;

    /**
     * Sign up a new user of the application from the connection.
     *
     * @param connection the connection
     * @return the new user id
     */
    public String execute(Connection<?> connection) {
        UserProfile profile = connection.fetchUserProfile();
        String userId = null;
        if (profile.getEmail() != null) {
            // copy the username from the connection
            userId = profile.getUsername();

            // create the user
            User user = new User();
            user.setUsername(userId);
            userService.registerUser(user);
        }
        return userId;
    }
}
