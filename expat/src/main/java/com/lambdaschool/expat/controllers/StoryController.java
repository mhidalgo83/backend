package com.lambdaschool.expat.controllers;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.lambdaschool.expat.models.Story;
import com.lambdaschool.expat.models.User;
import com.lambdaschool.expat.models.UserStories;
import com.lambdaschool.expat.services.StoryService;
import com.lambdaschool.expat.services.UserService;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.BeanPropertyWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/stories")
public class StoryController {

    @Autowired
    StoryService storyService;

    @Autowired
    UserService userService;

    @GetMapping(value = "/stories", produces = {"application/json"})
    public ResponseEntity<?> listAllStories(HttpServletRequest request){
        List<Story> myStories = storyService.findAll();
        return new ResponseEntity<>(myStories, HttpStatus.OK);
    }

    @GetMapping(value = "/story/{storyid}", produces = {"application/json"})
    public ResponseEntity<?> getStoryById(HttpServletRequest request, @PathVariable Long storyid){
        Story s = storyService.findStoryById(storyid);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    @GetMapping(value = "/stories/user/{userid}", produces = "application/json")
    public ResponseEntity<?> getStoriesByUserId(@PathVariable long userid) {
        List<UserStories> storyList = new ArrayList<>();
        User u = userService.findUserById(userid);
        for (UserStories s : u.getUserstories()) {
            storyList.add(s);
        }

        return new ResponseEntity<>(storyList, HttpStatus.OK);
    }

    @PostMapping(value ="/story", consumes = "application/json")
    public ResponseEntity<?> addNewStory(@Valid @RequestBody Story newStory,
                                         Authentication authentication) throws
            URISyntaxException{

        User currentUser = userService.findByName(authentication.getName());
        newStory.setStoryid(0);
        newStory.getUserstories().add(new UserStories(currentUser, newStory));
        newStory = storyService.save(newStory);


        HttpHeaders responseHeaders = new HttpHeaders();
        URI newStoryURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{storyid}")
                .buildAndExpand(newStory.getStoryid())
                .toUri();
        responseHeaders.setLocation(newStoryURI);

        //will build method to add story to user depending on who is logged in

        return new ResponseEntity<>(newStory, responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping(value = "/story/{storyid}", consumes = "application/json")
    public ResponseEntity<?> updateFullStory(@Valid @RequestBody Story updateStory, @PathVariable long storyid){

        updateStory.setStoryid(storyid);
//        User u = userService.findByName(authentication.getName());

        storyService.update(updateStory, storyid);

        return new ResponseEntity<>(updateStory, HttpStatus.OK);
    }

    @PatchMapping(value = "/story/{storyid}", consumes = "application/json")
    public ResponseEntity<?> updateStory(
            @RequestBody Story updateStory, @PathVariable long storyid){
        storyService.update(updateStory, storyid);
        return new ResponseEntity<>(updateStory, HttpStatus.OK);
    }

    @DeleteMapping(value = "/story/{storyid}")
    public ResponseEntity<?> deleteStoryById(@PathVariable long storyid){
        storyService.delete(storyid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
