package dao;

import entities.Organization;

import java.util.List;
import java.util.Optional;

public interface IOrganizationDAO {
    List findAllOrganization();

    Optional<Organization> saveOrganization(Organization org);

    void updateOrganization(Organization org);

    void deleteOrganization(Organization org);

    Optional<Organization> findOrganizationByName(String name);
}
