package services;

import dao.IOrganizationDAO;
import dao.IUserDAO;
import entities.Organization;
import entities.Userinfo;
import log.UserLog;

import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Path("/user")
public class UserService {

    @Inject
    IUserDAO userDAO;
    @Inject
    IOrganizationDAO organizationDAO;

    @GET
    @Path("/allUsers")
    @Produces("application/json")
    @Interceptors(UserLog.class)
    public List getAllUsers() {
        return userDAO.findAllUsers();
    }

    @POST
    @Path("/saveUser")
    @Produces("application/json")
    public Response saveUser(JsonObject object) {
        Userinfo user;
        String organizationName = object.getString("organization_name");
        BigInteger phone = new BigInteger(object.getString("phone"));
        Optional<Organization> org = organizationDAO.findOrganizationByName(organizationName);
        if (!org.isPresent()) {
            Organization newOrganization = new Organization();
            newOrganization.setName(organizationName);
            user = new Userinfo();
            user.setPhone(phone);
            user.setOrganizationByOrganizationId(newOrganization);
            userDAO.saveUser(user);
        }
        return Response.status(200).build();
    }

    @GET
    @Path("/allUsersInOrganization/{organizationName}")
    @Produces("application/json")
    public List<Userinfo> getAllUsersInOrganization(@PathParam("organizationName") String name) {
        Optional<Organization> org = organizationDAO.findOrganizationByName(name);
        return userDAO.finAllUsersInOrganization(org.get().getId());
    }

}
