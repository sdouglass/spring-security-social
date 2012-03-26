package org.springframework.social.security.dao;

import org.springframework.social.security.model.User;

/**
 * Data access methods for working with local accounts.
 */
public interface UserDAO {

    void save(User user);

    User findByUsername(String username);

}
