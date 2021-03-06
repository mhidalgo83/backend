package com.lambdaschool.expat.services;

import com.lambdaschool.expat.ExpatApplication;
import com.lambdaschool.expat.models.Photo;
import com.lambdaschool.expat.models.Story;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@SpringBootTest(classes = ExpatApplication.class)
@RunWith(SpringRunner.class)
public class PhotoServiceImplTest {

    @Autowired
    PhotoService photoService;

    @Autowired
    StoryService storyService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void save() {
        Story s1 = new Story("Test", "Test", "Test");
        s1 = storyService.save(s1);
        Photo p1 = new Photo("test",
                "test", s1
        );
        photoService.save(p1, 5);
        assertEquals(11, photoService.findAllPhotos().size());
    }

    @Test
    public void seedSave() {
        Story s1 = new Story("Test", "Test", "Test");
        s1 = storyService.save(s1);
        Photo p1 = new Photo("test",
                "test", s1
                );
        photoService.seedSave(p1);
        assertEquals(11, photoService.findAllPhotos().size());
    }

    @Test
    public void update() {
        Story s1 = new Story("Test", "Test", "Test");
        s1.setStoryid(1);
        Photo p1 = new Photo("test",
                "test", s1
        );
        photoService.update(p1, 4);
        assertEquals("test", photoService.findPhotoById(4).getImageurl());

    }

    @Test
    public void findPhotoById() {
        assertEquals("https://picsum.photos/id/100/200/300", photoService.findPhotoById(4).getImageurl());
    }

    @Test
    public void findAllPhotos() {
        assertEquals(10, photoService.findAllPhotos().size());
    }

    @Test
    public void delete() {
        photoService.delete(4);
        assertEquals(9, photoService.findAllPhotos().size());
    }

    @Test
    public void deleteAll() {
        photoService.deleteAll();
        assertEquals(0, photoService.findAllPhotos().size());
    }
}