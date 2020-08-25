package com.lambdaschool.expat.controllers;

import com.lambdaschool.expat.ExpatApplication;
import com.lambdaschool.expat.models.Story;
import com.lambdaschool.expat.services.StoryService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ExpatApplication.class)
@WithMockUser(username = "tomjones", roles = "USER")
public class StoryControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @MockBean
    private StoryService storyService;
    List<Story> storyList = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();

        storyList = new ArrayList<>();

        storyService.deleteAll();

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void listAllStories() {
    }

    @Test
    public void getStoryById() {
    }

    @Test
    public void addNewStory() {
    }

    @Test
    public void updateStory() {
    }

    @Test
    public void deleteStoryById() {
    }
}