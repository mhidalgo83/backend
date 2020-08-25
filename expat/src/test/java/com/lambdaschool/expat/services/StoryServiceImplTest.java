package com.lambdaschool.expat.services;

import com.lambdaschool.expat.ExpatApplication;
import com.lambdaschool.expat.models.Story;
import com.lambdaschool.expat.models.User;
import com.lambdaschool.expat.models.UserStories;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ExpatApplication.class)
public class StoryServiceImplTest {

    @Autowired
    private StoryService storyService;

    @Autowired
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findAll() {
        assertEquals(5, storyService.findAll().size());
    }

    @Test
    public void findStoryById() {
        assertEquals("My Trip", storyService.findStoryById(6).getTitle());
    }

    @Test
    public void delete() {
        storyService.delete(6);
        assertEquals(4, storyService.findAll().size());
    }

    @Test
    public void save() {
        Story s5 = new Story("Test Story", "Germany", "My travels begin...");

        User u5 = new User("Liz Jones",
                "password",
                "liz@school.lambda");
        u5.setUserid(1);
        s5.getUserStories().add(new UserStories(u5, s5));
        storyService.save(s5);
        assertEquals(6, storyService.findAll().size());

    }

    @Test
    public void update() {
        User u5 = new User("Liz Jones",
                "password",
                "liz@school.lambda");
        u5.setUserid(1);

        Story s5 = new Story("Test Story", "Germany", "My travels begin...");
        s5.getUserStories().add(new UserStories(u5, s5));

        Story updateds5 = storyService.update(s5, 6);

        assertEquals("Test Story", updateds5.getTitle());
    }

    @Test
    public void deleteAll() {
        storyService.deleteAll();
        assertEquals(0, storyService.findAll().size());
    }
}