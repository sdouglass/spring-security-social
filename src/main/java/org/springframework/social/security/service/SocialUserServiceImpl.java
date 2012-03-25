package org.springframework.social.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.security.dao.SocialUserDAO;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional(readOnly = true)
public class SocialUserServiceImpl implements SocialUserService {

  private SocialUserDAO socialUserDAO;
  private UserService userService;
  private ConnectionFactoryLocator connectionFactoryLocator;
  private String encryptionPassword;
  private TextEncryptor textEncryptor;
  private boolean encryptCredentials;

  @PostConstruct
  public void initializeTextEncryptor() {
    textEncryptor = Encryptors.text(encryptionPassword, KeyGenerators.string().generateKey());
  }

  public List<String> findUserIdsWithConnection(Connection<?> connection) {
    ConnectionKey key = connection.getKey();
    return socialUserDAO.findUserIdsByProviderIdAndProviderUserId(key.getProviderId(), key.getProviderUserId());
  }

  public Set<String> findUserIdsConnectedTo(String providerId, Set<String> providerUserIds) {
    return new HashSet<String>(socialUserDAO.findUserIdsByProviderIdAndProviderUserIds(providerId, providerUserIds));
  }

  public ConnectionRepository createConnectionRepository(String userId) {
    if (userId == null) {
      throw new IllegalArgumentException("userId cannot be null");
    }
    return new SocialUserConnectionRepositoryImpl(
        userId,
        userService,
        socialUserDAO,
        connectionFactoryLocator,
        (encryptCredentials ? textEncryptor : null)
    );
  }

  @Autowired
  public void setSocialUserDAO(SocialUserDAO socialUserDAO) {
    this.socialUserDAO = socialUserDAO;
  }

  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  @Autowired
  public void setConnectionFactoryLocator(ConnectionFactoryLocator connectionFactoryLocator) {
    this.connectionFactoryLocator = connectionFactoryLocator;
  }

  @Value("${social.crypto.password}")
  public void setEncryptionPassword(String encryptionPassword) {
    this.encryptionPassword = encryptionPassword;
  }

  @Value("${social.crypto.enabled:true}")
  public void setEncryptCredentials(boolean encryptCredentials) {
    this.encryptCredentials = encryptCredentials;
  }
}
