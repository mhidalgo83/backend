package com.lambdaschool.expat.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.expat.ExpatApplication;
import com.lambdaschool.expat.models.Story;
import com.lambdaschool.expat.models.User;
import com.lambdaschool.expat.models.UserStories;
import com.lambdaschool.expat.services.StoryService;
import com.lambdaschool.expat.services.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@WithMockUser(username = "tomjones", roles = {"USER"})
public class StoryControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @MockBean
    private StoryService storyService;

    @MockBean
    private UserService userService;

    List<Story> storyList = new ArrayList<>();

    User u1;

    @Before
    public void setUp() throws Exception {

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();

        storyList = new ArrayList<>();

        storyService.deleteAll();
        u1 = new User("tomjones",
                "password",
                "tom@lambdaschool.local");
        u1.setUserid(1);

        User u2 = new User("bobjones",
                "password",
                "bob@lambdaschool.local");
        u2.setUserid(2);



        Story s1 = new Story("My Trip", "New Zealand", "We had a great time in Auckland...");
        s1.getUserstories().add(new UserStories(u1, new Story()));
        s1.setStoryid(10);

        Story s2 = new Story("Another Trip", "Australia", "We had a great time in Sydney...");
        s2.getUserstories().add(new UserStories(u2, new Story()));
        s2.setStoryid(20);
//        User u1 = new User("tomjones",
//                "password",
//                "tom@lambdaschool.local");
//        u1.setUserid(1);
//
//        User u2 = new User("bobjones",
//                "password",
//                "bob@lambdaschool.local");
//        u2.setUserid(2);
//
//        User u3 = new User("jonjones",
//                "password",
//                "jon@lambdaschool.local");
//        u3.setUserid(3);
//
//        User u4 = new User("jimjones",
//                "password",
//                "jim@school.lambda");
//
//        u4.setUserid(4);
//
//        User u5 = new User("lizjones",
//                "password",
//                "liz@school.lambda");
//        u5.setUserid(5);
//
//
//        Set<UserStories> userstories = new HashSet<>();
//        Story s1 = new Story("My Trip", "New Zealand", "We had a great time in Auckland...");
//        userstories.add(new UserStories(u1, s1));
//        s1.setStoryid(10);
//        u1.setUserstories(userstories);
//
//        userstories = new HashSet<>();
//        Story s2 = new Story("Another Trip", "Australia", "We had a great time in Sydney...");
//        userstories.add(new UserStories(u2, s2));
//        s2.setStoryid(20);
//        u2.setUserstories(userstories);
//
//        userstories = new HashSet<>();
//        Story s3 = new Story("My Trip", "China", "We had a great time in Shanghai...");
//        userstories.add(new UserStories(u3, s3));
//        s3.setStoryid(30);
//        u3.setUserstories(userstories);
//
//        userstories = new HashSet<>();
//        Story s4 = new Story("My Trip", "Thailand", "We had a great time in Bangkok...");
//        userstories.add(new UserStories(u4, s4));
//        s4.setStoryid(40);
//        u4.setUserstories(userstories);
//
//        userstories = new HashSet<>();
//        Story s5 = new Story("My Trip", "New Zealand", "We had a great time in Auckland...");
//        userstories.add(new UserStories(u5, s5));
//        s5.setStoryid(50);
//        u5.setUserstories(userstories);
//
        storyList.add(s1);
        storyList.add(s2);
//        storyList.add(s3);
//        storyList.add(s4);
//        storyList.add(s5);


    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void listAllStories() throws Exception{
        String apiUrl = "/stories/stories";
        Mockito.when(storyService.findAll()).thenReturn(storyList);

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
        MvcResult r = mockMvc.perform(rb).andReturn();
        String tr = r.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(storyList);

        assertEquals(er, tr);
    }

    @Test
    public void getStoryById() throws Exception{
        String apiUrl = "/stories/story/11";
        Mockito.when(storyService.findStoryById(11)).thenReturn(storyList.get(0));

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
        MvcResult r = mockMvc.perform(rb).andReturn();
        String tr = r.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(storyList.get(0));

        assertEquals(er, tr);
    }

    @Test
    public void addNewStory() throws Exception {
        String apiUrl = "/stories/story";

        Story s2 = new Story("My Trip", "Thailand", "We had a great time in Bangkok...");
        s2.setStoryid(30);

        ObjectMapper mapper = new ObjectMapper();
        String storyString = mapper.writeValueAsString(s2);

        Mockito.when(storyService.save(any(Story.class))).thenReturn(s2);
        Mockito.when(userService.findByName(any(String.class))).thenReturn(u1);

        RequestBuilder rb = MockMvcRequestBuilders.post(apiUrl)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(storyString);

        mockMvc.perform(rb).andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateStory() throws Exception{
        String apiUrl = "/stories/story/{storyid}";

        Story s1 = new Story();
        s1.setStoryid(10);
        s1.setTitle("Test");
        s1.setLocation("NYC");
        s1.setDescription("Test Story");

        Mockito.when(storyService.update(s1, 10))
                .thenReturn(storyList.get(0));

        ObjectMapper mapper = new ObjectMapper();
        String userString = mapper.writeValueAsString(s1);

        RequestBuilder rb = MockMvcRequestBuilders.put(apiUrl, 100L)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(userString);

        mockMvc.perform(rb)
                .andExpect(status().is2xxSuccessful())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteStoryById() throws Exception{
        String apiUrl = "/stories/story/{storyid}";

        RequestBuilder rb = MockMvcRequestBuilders.delete(apiUrl, "11")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(rb)
                .andExpect(status().is2xxSuccessful())
                .andDo(MockMvcResultHandlers.print());
    }
}