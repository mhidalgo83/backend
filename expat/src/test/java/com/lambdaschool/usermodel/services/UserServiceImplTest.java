package com.lambdaschool.usermodel.services;

import com.lambdaschool.usermodel.UserModelApplication;
import com.lambdaschool.usermodel.models.Role;
import com.lambdaschool.usermodel.models.User;
import com.lambdaschool.usermodel.models.UserRoles;
import com.lambdaschool.usermodel.models.Useremail;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserModelApplication.class)
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

//        List<User> myList = userService.findAll();
//        for (User u : myList){
//            System.out.println(u.getUserid() + " " + u.getUsername());
//        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findUserById() {
        assertEquals("cinnamon", userService.findUserById(7).getUsername());
    }

    @Test(expected = EntityNotFoundException.class)
    public void findRestaurantByIdNotFound(){
        assertEquals("Test Eagle Cafe", userService.findUserById(100).getUsername());
    }

    @Test
    public void findByNameContaining() {
        assertEquals(1, userService.findByNameContaining("cinn").size());
    }

    @Test
    public void findAll() {
        assertEquals(4, userService.findAll().size());
    }

    @Test
    public void delete() {
        userService.delete(13);
        assertEquals(4, userService.findAll()
                .size());
    }

    @Test
    public void findByName() {
        assertEquals("admin", userService.findByName("admin")
                .getUsername());
    }

    @Test
    public void saveadd() {
        User u2 = new User("Sigi", "password", "sigi@gmail.com");

        Role r1 = new Role("Admin");
        r1.setRoleid(1);
        u2.getRoles().add(new UserRoles(u2, r1));

        u2.getUseremails().add(new Useremail(u2, "sigi@gmail.com"));

        User addUser = userService.save(u2);
        assertNotNull(addUser);
        assertEquals("sigi", addUser.getUsername());
    }

//    @Test
//    public void saveput() {
//        User u7 = new User("Sigi", "password", "sigi@gmail.com");
//        u7.setUserid(7);
//
//        Role r1 = new Role("Admin");
//        r1.setRoleid(1);
//        u7.getRoles().add(new UserRoles(u7, r1));
//        u7.getUseremails().add(new Useremail(u7, "sigi@gmail.com"));
//
//        User addUser = userService.save(u7);
//        assertNotNull(addUser);
//        assertEquals("sigi", addUser.getUsername());
//    }

    @Test
    public void update() {
        Role r2 = new Role("user");
        r2.setRoleid(2);

        User u2 = new User("cinnamon", "password", "cinnamon@school.lambda");
        u2.getRoles().add(new UserRoles(u2, r2));

        u2.getUseremails()
                .add(new Useremail(u2, "cinnamon@mymail.thump"));
        u2.getUseremails()
                .add(new Useremail(u2, "hops@mymail.thump"));
        u2.getUseremails()
                .add(new Useremail(u2, "bunny@email.thump"));

        User updatedu2 = userService.update(u2, 7);

        System.out.println("*** DATA ***");
        System.out.println(updatedu2);
        System.out.println("*** DATA ***");

        int checking = updatedu2.getUseremails()
                .size() - 1;
        assertEquals("bunny@email.thump", updatedu2.getUseremails()
                .get(checking)
                .getUseremail());
    }

    @Test
    public void deleteAll() {
    }
}