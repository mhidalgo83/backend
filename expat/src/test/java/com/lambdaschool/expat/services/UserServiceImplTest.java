package com.lambdaschool.expat.services;

import com.lambdaschool.expat.ExpatApplication;
import com.lambdaschool.expat.models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ExpatApplication.class)
public class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findUserById() {
        assertEquals("tomjones", userService.findUserById(2).getUsername());
    }

    @Test
    public void findByNameContaining() {
        assertEquals("tomjones", userService.findByNameContaining("omj").get(0).getUsername());
    }

    @Test
    public void findAll() {
        assertEquals(5, userService.findAll().size());
    }

    @Test
    public void delete() {
        userService.delete(2);
        assertEquals(4, userService.findAll().size());
    }

    @Test
    public void findByName() {
        assertEquals("tomjones", userService.findByName("tomjones").getUsername());
    }

    @Test
    public void save() {
    }

    @Test
    public void update() {
        User u1 = new User("test",
                "password",
                "tom@lambdaschool.local");
        userService.update(u1, 2);
        assertEquals("test", userService.findUserById(2).getUsername());
    }

    @Test
    public void deleteAll() {
        userService.deleteAll();
        assertEquals(0, userService.findAll().size());
    }
}