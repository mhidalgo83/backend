package com.lambdaschool.expat;

import com.lambdaschool.expat.models.Photo;
import com.lambdaschool.expat.models.User;
import com.lambdaschool.expat.services.PhotoService;
import com.lambdaschool.expat.services.StoryService;
import com.lambdaschool.expat.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


/**
 * SeedData puts both known and random data into the database. It implements CommandLineRunner.
 * <p>
 * CoomandLineRunner: Spring Boot automatically runs the run method once and only once
 * after the application context has been loaded.
 */
@Transactional
@Component
public class SeedData implements CommandLineRunner
{

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
    public void run(String[] args) throws Exception
    {
        userService.deleteAll();


        // admin, data, user
        User u1 = new User("Tom Jones",
                           "password",
                           "tom@lambdaschool.local");
        userService.save(u1);

        // data, user
        User u2 = new User("Bob Jones",
                           "password",
                           "bob@lambdaschool.local");
        userService.save(u2);

        // user
        User u3 = new User("Jon Jones",
                           "password",
                           "jon@lambdaschool.local");
        userService.save(u3);

        User u4 = new User("Jim Jones",
                           "password",
                           "jim@school.lambda");

        userService.save(u4);

        User u5 = new User("Liz Jones",
                           "password",
                           "liz@school.lambda");
        userService.save(u5);




    }
}