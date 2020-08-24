package com.lambdaschool.expat.services;

import com.lambdaschool.expat.models.Photo;
import com.lambdaschool.expat.models.Story;
import com.lambdaschool.expat.models.User;
import com.lambdaschool.expat.repository.PhotoRepository;
import com.lambdaschool.expat.repository.StoryRepository;
import com.lambdaschool.expat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "photoService")
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    PhotoRepository photorepos;

    @Autowired
    UserService userService;

    @Autowired
    StoryService storyService;

    @Autowired
    UserRepository userrepos;

    @Autowired
    StoryRepository storyrepos;

    @Transactional
    @Override
    public Photo save(Photo photo) {
        Photo newPhoto = new Photo();



        if(photo.getPhotoid() != 0) {
            photorepos.findById(photo.getPhotoid())
                    .orElseThrow(() -> new EntityNotFoundException("Photo " + photo.getPhotoid() + " Not Found"));
        }

        newPhoto.setImageurl(photo.getImageurl());
        newPhoto.setDescription(photo.getDescription());
        User currentUser = userrepos.findById(photo.getUser().getUserid())
                .orElseThrow(() -> new EntityNotFoundException("User " + photo.getUser().getUserid() + " Not Found"));
        Story curStory = storyrepos.findById(photo.getStory().getStoryid())
                .orElseThrow(() -> new EntityNotFoundException("Story " + photo.getStory().getStoryid() + " Not Found"));
        newPhoto.setUser(currentUser);
        newPhoto.setStory(curStory);



        return photorepos.save(newPhoto);
    }

    @Override
    public Photo update(Photo photo, long id) {
        Photo curPhoto = photorepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Photo " + id + "Not Found"));

        if(photo.getImageurl() != null) {
            curPhoto.setImageurl(photo.getImageurl());
        }

        if(photo.getDescription() != null) {
            curPhoto.setDescription(photo.getDescription());
        }

        if (photo.getUser() != null) {
            curPhoto.setUser(photo.getUser());
        }

        if(photo.getStory() != null) {
            curPhoto.setStory(photo.getStory());
        }

        return photorepos.save(curPhoto);
    }

    @Override
    public Photo findPhotoById(long id) {
      Photo photo = photorepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Photo " + id + " Not Found"));
      return photo;
    }

    @Override
    public List<Photo> findAllPhotos() {
        List<Photo> photoList = new ArrayList<>();
        photorepos.findAll().iterator().forEachRemaining(photoList::add);

        return photoList;
    }

    @Transactional
    @Override
    public void delete(long id) {
        photorepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Photo " + id + " Not Found"));
        photorepos.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteAll() {
        photorepos.deleteAll();
    }
}
