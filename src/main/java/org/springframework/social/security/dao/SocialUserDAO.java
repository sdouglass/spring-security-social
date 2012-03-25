package org.springframework.social.security.dao;

import org.springframework.social.security.model.SocialUser;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Set;

/**
 * @author sam
 * @version $Id$
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
