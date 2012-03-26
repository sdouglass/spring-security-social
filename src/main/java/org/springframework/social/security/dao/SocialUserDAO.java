package org.springframework.social.security.dao;

import org.springframework.social.security.model.SocialUser;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Set;

/**
 * Data access methods used in implementing Spring Social's ConnectionRepository
 * and UsersConnectionRepository interfaces.
 *
 * One important note is that in Spring Social the code and documentation use
 * the term "userId" to mean a unique identifier of local accounts. It does not
 * have to be a numeric unique id, such as a database primary key value. For
 * integration with Spring Security the "userId" should be whatever identifier
 * your users log in with (e.g. a username, an email address, etc.). In this
 * application it is the local account's username.
 *
 * Also important to note is that Spring Social technically allows for
 * many-to-many relationships between local users and accounts from a
 * social network (e.g. Twitter). For example, you can associate one Twitter
 * account with several local accounts, and associate one local account with
 * several Twitter accounts. This is reflected in the methods of this interface.
 *
 * This allows for maximum flexibility for applications using Spring Social,
 * however most often apps will probably only allow a one-to-one relationship
 * (e.g. one Twitter account connects with only one local account and vice versa).
 * In this demo application users are not allowed to associate one social account
 * with multiple local accounts. They will get an error if they attempt to do so.
 * Also there is no UI provided for associating multiple social accounts from
 * the same provider with one local account.
 *
 * There are some common arguments to these methods:
 *
 * userId - unique id of a local account
 * providerId - id of a social provider (e.g. "twitter", "facebook", etc.)
 * providerUserId - id of an account on a social provider's network (e.g. a Twitter handle, Facebook user id, etc.)
 */
public interface SocialUserDAO {

    void save(SocialUser socialUser);

    List<SocialUser> findByProviderId(String providerId);

    List<SocialUser> findByUserId(String userId);

    List<SocialUser> findByUserIdAndProviderId(String userId, String providerId);

    List<SocialUser> findByUserIdAndProviderUserIds(String userId, MultiValueMap<String, String> providerUserIds);

    SocialUser get(String userId, String providerId, String providerUserId);

    List<SocialUser> findPrimaryByUserIdAndProviderId(String userId, String providerId);

    Integer selectMaxRankByUserIdAndProviderId(String userId, String providerId);

    List<String> findUserIdsByProviderIdAndProviderUserId(String providerId, String providerUserId);

    List<String> findUserIdsByProviderIdAndProviderUserIds(String providerId, Set<String> providerUserIds);

    void delete(SocialUser socialUser);
}
