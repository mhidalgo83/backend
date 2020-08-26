package com.lambdaschool.expat.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.expat.models.Photo;
import com.lambdaschool.expat.models.Story;
import com.lambdaschool.expat.models.User;
import com.lambdaschool.expat.models.UserStories;
import com.lambdaschool.expat.services.PhotoService;
import com.lambdaschool.expat.services.StoryService;
import com.lambdaschool.expat.services.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
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
//@WebMvcTest(value = UserController.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@WithMockUser(username = "admin", roles = {"USER"})
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private UserService userService;

    @MockBean
    private StoryService storyService;

    @MockBean
    private PhotoService photoService;

    private List<User> userList;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();

        userList = new ArrayList<>();

        userService.deleteAll();
        storyService.deleteAll();
        photoService.deleteAll();


        Set<UserStories> us1 = new HashSet<>();
        Story s1 = new Story("My Trip", "New Zealand", "We had a great time in Auckland...");
        User u1 = new User("tomjones",
                "password",
                "tom@lambdaschool.local");
        userList.add(u1);
        List<Photo> photoList = new ArrayList<>();
        Photo p1 = new Photo("https://picsum.photos/id/10/200/300", "A new pic", s1);
        Photo p2 = new Photo("https://picsum.photos/id/100/200/300", "A new pic", s1);
        p1 = photoService.seedSave(p1);
        p2 = photoService.seedSave(p2);
        photoList.add(p1);
        photoList.add(p2);
        us1.add(new UserStories(u1, s1));
        u1.setUserstories(us1);
        s1.setPhotos(photoList);
        u1 = userService.save(u1);
        s1 = storyService.save(s1);


        Set<UserStories> us2 = new HashSet<>();
        Story s2 = new Story("Another Trip", "Australia", "We had a great time in Sydney...");
        User u2 = new User("bobjones",
                "password",
                "bob@lambdaschool.local");
        userList.add(u2);
        photoList.clear();
        Photo p3 = new Photo("https://picsum.photos/id/1001/200/300", "A new pic", s2);
        Photo p4 = new Photo("https://picsum.photos/id/1002/200/300", "A new pic", s2);
        p3 = photoService.seedSave(p3);
        p4 = photoService.seedSave(p4);
        photoList.add(p3);
        photoList.add(p4);
        us2.add(new UserStories(u2, s2));
        u2.setUserstories(us2);
        s2.setPhotos(photoList);
        u2 = userService.save(u2);
        s2 = storyService.save(s2);

        Set<UserStories> us3 = new HashSet<>();
        Story s3 = new Story("My Trip", "China", "We had a great time in Shanghai...");
        User u3 = new User("jonjones",
                "password",
                "jon@lambdaschool.local");
        userList.add(u3);
        photoList.clear();
        Photo p5 = new Photo("https://picsum.photos/id/1003/200/300", "A new pic", s3);
        Photo p6 = new Photo("https://picsum.photos/id/1004/200/300", "A new pic", s3);
        p5 = photoService.seedSave(p5);
        p6 = photoService.seedSave(p6);
        photoList.add(p5);
        photoList.add(p6);
        us3.add(new UserStories(u3, s3));
        u3.setUserstories(us3);
        s3.setPhotos(photoList);
        u3 = userService.save(u3);
        s3 = storyService.save(s3);

        Set<UserStories> us4 = new HashSet<>();
        Story s4 = new Story("My Trip", "Thailand", "We had a great time in Bangkok...");
        User u4 = new User("jimjones",
                "password",
                "jim@school.lambda");
        userList.add(u4);
        photoList.clear();
        Photo p7 = new Photo("https://picsum.photos/id/1005/200/300", "A new pic", s4);
        Photo p8 = new Photo("https://picsum.photos/id/1006/200/300", "A new pic", s4);
        p7 = photoService.seedSave(p7);
        p8 = photoService.seedSave(p8);
        photoList.add(p7);
        photoList.add(p8);
        us4.add(new UserStories(u4, s4));
        u4.setUserstories(us4);
        s4.setPhotos(photoList);
        u4 = userService.save(u4);
        s4 = storyService.save(s4);

        Set<UserStories> us5 = new HashSet<>();
        Story s5 = new Story("My Trip", "New Zealand", "We had a great time in Auckland...");
        User u5 = new User("lizjones",
                "password",
                "liz@school.lambda");
        userList.add(u5);
        photoList.clear();
        Photo p9 = new Photo("https://picsum.photos/id/1007/200/300", "A new pic", s5);
        Photo p10 = new Photo("https://picsum.photos/id/1008/200/300", "A new pic", s5);
        p9 = photoService.seedSave(p9);
        p10 = photoService.seedSave(p10);
        photoList.add(p9);
        photoList.add(p10);
        us5.add(new UserStories(u5, s5));
        u5.setUserstories(us5);
        s5.setPhotos(photoList);
        u5 = userService.save(u5);
        s5 = storyService.save(s5);






    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void listAllUsers() throws Exception{
        String apiUrl = "/users/users";
        System.out.println(userList);
        Mockito.when(userService.findAll()).thenReturn(userList);

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
        MvcResult r = mockMvc.perform(rb).andReturn();
        String tr = r.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(userList);

        assertEquals(er, tr);
    }

    @Test
    public void getUserById() throws Exception{
        String apiUrl = "/users/user/1";
        Mockito.when(userService.findUserById(1)).thenReturn(userList.get(0));

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
        MvcResult r = mockMvc.perform(rb).andReturn();
        String tr = r.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(userList.get(0));

        assertEquals(er, tr);
    }

    @Test
    public void getUserByIdNotFound() throws Exception{
        String apiUrl = "/users/user/12";
        Mockito.when(userService.findUserById(100)).thenReturn(null);

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
        MvcResult r = mockMvc.perform(rb).andReturn();
        String tr = r.getResponse().getContentAsString();

        String er = "";

        assertEquals(er, tr);

    }

    @Test
    public void getUserByName() throws Exception{
        String apiUrl = "/users/user/name/tomjones";

        Mockito.when(userService.findByName("tomjones"))
                .thenReturn(userList.get(0));

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult r = mockMvc.perform(rb).andReturn();
        String tr = r.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(userList.get(0));

        assertEquals("Rest API Returns List", er, tr);
    }

    @Test
    public void getUserLikeName() throws Exception{
        String apiUrl = "/users/user/name/like/mjo";

        Mockito.when(userService.findByNameContaining(any(String.class)))
                .thenReturn(userList);

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl)
                .accept(MediaType.APPLICATION_JSON);


        MvcResult r = mockMvc.perform(rb).andReturn();
        String tr = r.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(userList);

        assertEquals("Rest API Returns List", er, tr);
    }

    @Test
    public void addNewUser() throws Exception{
        String apiUrl = "/users/user";

        User u3 = new User("barnbarn",
                "ILuvM4th!",
                "barnbarn@lambdaschool.local");
        u3.setUserid(30);



        ObjectMapper mapper = new ObjectMapper();
        String userString = mapper.writeValueAsString(u3);

        Mockito.when(userService.save(any(User.class))).thenReturn(u3);

        RequestBuilder rb = MockMvcRequestBuilders.post(apiUrl)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(userString);

        mockMvc.perform(rb).andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateFullUser() {
    }

    @Test
    public void updateUser() throws Exception{
        String apiUrl = "/users/user/{userid}";

        // build a user
        User u1 = new User();
        u1.setUserid(100);
        u1.setUsername("username");
        u1.setPrimaryemail("test@test.com");
        u1.setPassword("password");

        Mockito.when(userService.update(u1, 100))
                .thenReturn(userList.get(0));

        ObjectMapper mapper = new ObjectMapper();
        String userString = mapper.writeValueAsString(u1);

        RequestBuilder rb = MockMvcRequestBuilders.put(apiUrl, 100L)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(userString);

        mockMvc.perform(rb)
                .andExpect(status().is2xxSuccessful())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteUserById() throws Exception{
        String apiUrl = "/users/user/1";

        RequestBuilder rb = MockMvcRequestBuilders.delete(apiUrl, "1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(rb)
                .andExpect(status().is2xxSuccessful())
                .andDo(MockMvcResultHandlers.print());
    }
}