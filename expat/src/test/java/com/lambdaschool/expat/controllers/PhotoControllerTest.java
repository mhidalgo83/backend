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
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@WithMockUser(username = "tomjones", roles = {"USER"})
public class PhotoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StoryService storyService;

    @MockBean
    private PhotoService photoService;

    @MockBean
    private UserService userService;

    private List<Photo> photoList;

    @Before
    public void setUp() throws Exception {
        photoList = new ArrayList<>();
//
//        {
//            "imageurl":"url",
//             "description":"d"
//        }

        Set<UserStories> us1 = new HashSet<>();
        Story s1 = new Story("My Trip", "New Zealand", "We had a great time in Auckland...");
        User u1 = new User("tomjones",
                "password",
                "tom@lambdaschool.local");
        u1.setUserid(1);
        s1.setStoryid(10);
        List<Photo> photos = new ArrayList<>();
        Photo p1 = new Photo("https://picsum.photos/id/10/200/300", "A new pic", s1);
        Photo p2 = new Photo("https://picsum.photos/id/100/200/300", "A new pic", s1);
        p1.setPhotoid(100);
        p2.setPhotoid(200);
        photos.add(p1);
        photos.add(p2);
        us1.add(new UserStories(u1, s1));
        u1.setUserstories(us1);
        s1.setPhotos(photos);

        photoList.add(p1);
        photoList.add(p2);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAllPhotos() throws Exception{
        String apiUrl = "/photos/photos";
        Mockito.when(photoService.findAllPhotos()).thenReturn(photoList);

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
        MvcResult r = mockMvc.perform(rb).andReturn();
        String tr = r.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(photoList);

        assertEquals(er, tr);
    }

    @Test
    public void getPhotoById() throws Exception{
        String apiUrl = "/photos/photo/100";
        Mockito.when(photoService.findPhotoById(100)).thenReturn(photoList.get(0));

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
        MvcResult r = mockMvc.perform(rb).andReturn();
        String tr = r.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(photoList.get(0));

        assertEquals(er, tr);
    }

    @Test
    public void getPhotoByIdNotFound() throws Exception{
        String apiUrl = "/photos/photo/12";
        Mockito.when(photoService.findPhotoById(12)).thenReturn(null);

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
        MvcResult r = mockMvc.perform(rb).andReturn();
        String tr = r.getResponse().getContentAsString();

        String er = "";

        assertEquals(er, tr);
    }

    @Test
    public void addNewPhoto() throws Exception{
        String apiUrl = "/photos/photo/story/10";


        Photo p1 = new Photo();
        p1.setImageurl("url");
        p1.setDescription("description");


        ObjectMapper mapper = new ObjectMapper();
        String photoString = mapper.writeValueAsString(p1);

        Mockito.when(photoService.save(any(Photo.class), any(Long.class))).thenReturn(photoList.get(0));

        RequestBuilder rb = MockMvcRequestBuilders.post(apiUrl)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(photoString);

        mockMvc.perform(rb).andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());


    }

    @Test
    public void updatePhoto() throws Exception{
        String apiUrl = "/photos/photo/{photoid}";

        Photo p1 = new Photo();
        p1.setImageurl("url");
        p1.setDescription("description");

        Mockito.when(photoService.update(p1, 10)).thenReturn(photoList.get(0));

        ObjectMapper mapper = new ObjectMapper();
        String userString = mapper.writeValueAsString(p1);

        RequestBuilder rb = MockMvcRequestBuilders.put(apiUrl, 100L)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(userString);

        mockMvc.perform(rb)
                .andExpect(status().is2xxSuccessful())
                .andDo(MockMvcResultHandlers.print());




    }

    @Test
    public void deletePhoto() throws Exception{
        String apiUrl = "/photos/photo/{photoid}";

        RequestBuilder rb = MockMvcRequestBuilders.delete(apiUrl, "3")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(rb)
                .andExpect(status().is2xxSuccessful())
                .andDo(MockMvcResultHandlers.print());
    }
}