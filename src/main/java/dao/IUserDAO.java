package dao;

import entities.Userinfo;

import java.util.List;
import java.util.UUID;

public interface IUserDAO {
    List<Userinfo> findAllUsers();

    List<Userinfo> finAllUsersInOrganization(UUID organizationID);

    void saveUser(Userinfo userinfo);

    void deleteUser(Userinfo userinfo);

    void updateUser(Userinfo userinfo);
}
