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

    @Autowired
    private SocialUserDAO socialUserDAO;
    @Autowired
    private ConnectionFactoryLocator connectionFactoryLocator;
    private TextEncryptor textEncryptor;
    @Value("${social.crypto.password}")
    private String encryptionPassword;
    @Value("${social.crypto.enabled:true}")
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
                socialUserDAO,
                connectionFactoryLocator,
                (encryptCredentials ? textEncryptor : null)
        );
    }
}
