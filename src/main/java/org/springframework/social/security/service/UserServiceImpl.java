package org.springframework.social.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.security.dao.UserDAO;
import org.springframework.social.security.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    public User findByLogin(String login) {
        return userDAO.findByUsername(login);
    }

    @Transactional(readOnly = false)
    public void registerUser(User user) {

        if (user.getPassword() == null) {
            String password = generatePassword();
            user.setPassword(password);
        }

        userDAO.save(user);
    }

    private static final String RANDOM_PASSWORD_CHARS =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890-_!$*";

    private static final int RANDOM_PASSWORD_LENGTH = 12;

    private String generatePassword() {
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < RANDOM_PASSWORD_LENGTH; i++) {
            int charIndex = (int) (Math.random() * RANDOM_PASSWORD_CHARS.length());
            char randomChar = RANDOM_PASSWORD_CHARS.charAt(charIndex);
            password.append(randomChar);
        }
        return password.toString();
    }
}
