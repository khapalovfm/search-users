package dao;

import entities.Organization;
import entities.Userinfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.EntityManager;

import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserManagerTest {
    @Mock
    private UserManager userDAO;
    @Mock
    private OrganizationManager orgDAO;
    private EntityManager em;

    private Userinfo user;
    private Organization organization;

    @Before
    public void init() {
        organization = new Organization();
        user = new Userinfo();
        userDAO = new UserManager();
        orgDAO = new OrganizationManager();
        em = mock(EntityManager.class);
        when(em.find((Class<?>) any(), any())).thenReturn(null);
        orgDAO.setEm(em);
        userDAO.setEm(em);
    }

    @Test
    public void saveUser() {
        userDAO.saveUser(user);
        verify(em).persist(user);
    }

    @Test
    public void findUser() {
        userDAO.saveUser(user);
        verify(em).persist(user);

        Userinfo finded = new Userinfo();
        when(em.find(Userinfo.class, user.getId())).thenReturn(finded);

        assertNotNull(finded);
    }
}
