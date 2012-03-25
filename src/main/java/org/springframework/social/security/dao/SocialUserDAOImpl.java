package org.springframework.social.security.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.security.model.SocialUser;
import org.springframework.stereotype.Repository;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
@SuppressWarnings("unchecked")
public class SocialUserDAOImpl implements SocialUserDAO {

  @Autowired SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    private Criteria createCriteria() {
        return getCurrentSession().createCriteria(SocialUser.class);
    }

    public void save(SocialUser socialUser) {
        getCurrentSession().save(socialUser);
    }

    public List<SocialUser> findByProviderId(String providerId) {
    return (List<SocialUser>) createCriteria().add(Restrictions.eq("providerId", providerId)).list();
  }

  public List<SocialUser> findByUserId(String userId) {
    return (List<SocialUser>) createCriteria().add(Restrictions.eq("userId", userId)).list();
  }

  public List<SocialUser> findByUserIdAndProviderId(String userId, String providerId) {
    return (List<SocialUser>) createCriteria()
        .add(Restrictions.eq("userId", userId))
        .add(Restrictions.eq("providerId", providerId))
        .list();
  }

  public List<SocialUser> findByUserIdAndProviderUserIds(String userId, MultiValueMap<String, String> providerUserIds) {
    Criteria criteria = createCriteria();
    criteria.add(Restrictions.eq("userId", userId));
    Disjunction or = Restrictions.disjunction();
    for (String providerId : providerUserIds.keySet()) {
      or.add(
          Restrictions.and(
              Restrictions.eq("providerId", providerId),
              Restrictions.in("providerUserId", providerUserIds.get(providerId))
          )
      );
    }
    return (List<SocialUser>) criteria.list();
  }

  public SocialUser get(String userId, String providerId, String providerUserId) {
    return (SocialUser) createCriteria()
        .add(Restrictions.eq("userId", userId))
        .add(Restrictions.eq("providerId", providerId))
        .add(Restrictions.eq("providerUserId", providerUserId))
        .uniqueResult();
  }

  public List<SocialUser> findPrimaryByUserIdAndProviderId(String userId, String providerId) {
    return (List<SocialUser>) createCriteria()
        .add(Restrictions.eq("userId", userId))
        .add(Restrictions.eq("providerId", providerId))
//        .add(Restrictions.eq("rank", 1))
        .addOrder(Order.asc("rank"))
        .list();
  }

  public Integer selectMaxRankByUserIdAndProviderId(String userId, String providerId) {
      return (Integer) createCriteria()
              .add(Restrictions.eq("userId", userId))
              .add(Restrictions.eq("providerId", providerId))
              .setProjection(Projections.max("rank"))
              .uniqueResult();
  }

  public List<String> findUserIdsByProviderIdAndProviderUserId(String providerId, String providerUserId) {
      List<SocialUser> socialUsers = (List<SocialUser>) createCriteria()
              .add(Restrictions.eq("providerId", providerId))
              .add(Restrictions.eq("providerUserId", providerUserId))
              .list();
      List<String> userIds = new ArrayList<String>(socialUsers.size());
      for (SocialUser socialUser : socialUsers) {
          userIds.add(socialUser.getUserId());
      }
    return userIds;
  }

  public List<String> findUserIdsByProviderIdAndProviderUserIds(String providerId, Set<String> providerUserIds) {
      List<SocialUser> socialUsers = (List<SocialUser>) createCriteria()
              .add(Restrictions.eq("providerId", providerId))
              .add(Restrictions.in("providerUserId", providerUserIds))
              .list();
      List<String> userIds = new ArrayList<String>(socialUsers.size());
      for (SocialUser socialUser : socialUsers) {
          userIds.add(socialUser.getUserId());
      }
      return userIds;
  }

    public void delete(SocialUser socialUser) {
        getCurrentSession().delete(socialUser);
    }

}
