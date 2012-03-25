package org.springframework.social.security.service;

import org.springframework.social.security.model.User;

public interface UserService {

  User findByLogin(String login);

  void registerUser(User user);
}
