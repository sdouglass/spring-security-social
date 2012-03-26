package org.springframework.social.security.model;

import javax.persistence.*;
import java.util.Date;

/**
 * A social network user account.
 *
 * Implementation of Spring Social's expected UserConnection schema defined here:
 *
 * http://static.springsource.org/spring-social/docs/1.0.x/reference/html/serviceprovider.html
 *
 */
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"userId", "providerId", "providerUserId"}),
        @UniqueConstraint(columnNames = {"userId", "providerId", "rank"})
})
public class SocialUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /**
     * A local unique identifier for the user, in this case the username.
     */
    private String userId;

    @Column(nullable = false)
    private String providerId;

    private String providerUserId;

    @Column(nullable = false)
    private int rank;

    private String displayName;

    private String profileUrl;

    private String imageUrl;

    @Column(nullable = false)
    private String accessToken;

    private String secret;

    private String refreshToken;

    private Long expireTime;

    private Date createDate = new Date();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getProviderUserId() {
        return providerUserId;
    }

    public void setProviderUserId(String providerUserId) {
        this.providerUserId = providerUserId;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
