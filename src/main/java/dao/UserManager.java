package dao;

import entities.Userinfo;
import log.UserLog;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;

@Stateless
public class UserManager implements IUserDAO {

    @PersistenceContext(name = "NewPersistenceUnit")
    EntityManager em;

    public List findAllUsers() {
        return em.createNamedQuery("findAllUsers").getResultList();
    }

    public List finAllUsersInOrganization(UUID organizationID) {
        return em.createQuery(" select p from Userinfo  p where p.organizationByOrganizationId.id = :organizationID")
                .setParameter("organizationID", organizationID)
                .getResultList();
    }

    @Interceptors(UserLog.class)
    public void saveUser(Userinfo userinfo) {
        em.persist(userinfo);
    }

    public void deleteUser(Userinfo userinfo) {
        em.remove(userinfo);
    }

    public void updateUser(Userinfo userinfo) {
        em.refresh(userinfo);
    }
}
