package dao;

import entities.Organization;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Stateless
public class OrganizationManager implements IOrganizationDAO {
    @PersistenceContext(name = "NewPersistenceUnit")
    EntityManager em;

    public List findAllOrganization() {
        return em.createQuery("select c from Organization c").getResultList();
    }

    public Optional<Organization> saveOrganization(Organization org) {
        em.persist(org);
        em.flush();
        return Optional.of(org);
    }

    public void updateOrganization(Organization org) {
        em.refresh(org);
    }

    public void deleteOrganization(Organization org) {
        em.remove(org);
    }

    @Override
    public Optional<Organization> findOrganizationByName(String name) {
        return (Optional<Organization>) em.createQuery("select c from Organization c where name = :organizationName")
                .setParameter("organizationName", name)
                .getResultList().stream().findFirst();
    }


}
