package dao;

import entities.Userinfo;
import log.UserLog;
import lombok.Setter;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Stateless
public class UserManager implements IUserDAO {
    @Setter
    @PersistenceContext(name = "NewPersistenceUnit")
    private EntityManager em;

    public List<Userinfo> findAllUsers() {
        return em.createNamedQuery("findAllUsers").getResultList();
    }

    public List<Userinfo> finAllUsersInOrganization(UUID organizationID) {
        return em.createNamedQuery("findAllUsersInOrganization")
                .setParameter("organizationID", organizationID)
                .getResultList();
    }

    public Optional<Userinfo> findUserWithPhone(BigInteger phone) {
        return (Optional<Userinfo>) em.createQuery("select c " +
                "from Userinfo c where c.phone = :phone")
                .setParameter("phone", phone)
                .getResultList().stream().findFirst();
    }

    @Interceptors(UserLog.class)
    public void saveUser(Userinfo userinfo) {
        em.persist(userinfo);
        em.flush();
    }

    public void deleteUser(Userinfo userinfo) {
        em.remove(userinfo);
    }

    public void updateUser(Userinfo userinfo) {
        em.refresh(userinfo);
    }
}
