package org.springframework.social.security.social;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.security.model.User;
import org.springframework.social.security.service.UserService;

/**
 * This class can be used in two cases.
 *
 * 1) If you are using Spring Social's JDBC connection repository classes, you must implementing ConnectionSignUp
 * so that JdbcUsersConnectionRepository can create a new local account for a user who signs in for the first
 * time with a social provider. See JdbcUsersConnectionRepository.findUserIdsWithConnection(Connection<?> connection)
 * for details.
 *
 * 2) If you wish to automatically create a new local account for a user signing in for the first time with a
 * social provider, in your own implementation of UserConnectionRepository.findUserIdsWithConnection(Connection<?> connection)
 * following the example of JdbcUsersConnectionRepository.
 *
 * If you have any sign up/registration requirements and validation that may not be met by the user profile data from
 * the social provider (e.g. you require an email address, or a password, or other data) you probably do not
 * want to make a ConnectionSignUp implementation. Rather than automatically creating an account for a user you
 * would rely on the ProviderSignInController to send them to your sign up/registration form, where you could show
 * them a message about what further data they must enter.
 *
 * In this app the sign up requirements are deliberately minimal and accounts are automatically being created. Rather
 * than using this class though they are being created in the sign up form controller in the GET method handler.
 *
 * @see org.springframework.social.security.controllers.SignUpController#getForm(org.springframework.web.context.request.NativeWebRequest, org.springframework.social.security.model.User)
 */
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
