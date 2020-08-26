package com.lambdaschool.expat;


import com.lambdaschool.expat.models.Photo;
import com.lambdaschool.expat.models.Story;
import com.lambdaschool.expat.models.User;
import com.lambdaschool.expat.models.UserStories;
import com.lambdaschool.expat.services.PhotoService;
import com.lambdaschool.expat.services.StoryService;
import com.lambdaschool.expat.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



/**
 * SeedData puts both known and random data into the database. It implements CommandLineRunner.
 * <p>
 * CoomandLineRunner: Spring Boot automatically runs the run method once and only once
 * after the application context has been loaded.
 */
@Transactional
//@Component
public class SeedData implements CommandLineRunner {

    /**
     * Connects the user service to this process
     */
    @Autowired
    UserService userService;

    @Autowired
    StoryService storyService;

    @Autowired
    PhotoService photoService;

    /**
     * Generates test, seed data for our application
     * First a set of known data is seeded into our database.
     * Second a random set of data using Java Faker is seeded into our database.
     * Note this process does not remove data from the database. So if data exists in the database
     * prior to running this process, that data remains in the database.
     *
     * @param args The parameter is required by the parent interface but is not used in this process.
     */
    @Transactional
    @Override



    public void run(String[] args) throws Exception {
        userService.deleteAll();
        storyService.deleteAll();
        photoService.deleteAll();


        Set<UserStories> us1 = new HashSet<>();
        Story s1 = new Story("My Trip", "New Zealand", "We had a great time in Auckland...");
        User u1 = new User("tomjones",
                "password",
                "tom@lambdaschool.local");
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
}