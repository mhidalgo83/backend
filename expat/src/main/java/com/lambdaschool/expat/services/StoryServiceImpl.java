package com.lambdaschool.expat.services;


import com.lambdaschool.expat.exceptions.ResourceNotFoundException;
import com.lambdaschool.expat.models.Story;
import com.lambdaschool.expat.models.User;
import com.lambdaschool.expat.models.UserStories;
import com.lambdaschool.expat.repository.StoryRepository;
import com.lambdaschool.expat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service("storyService")
public class StoryServiceImpl implements StoryService {
    @Autowired
    UserAuditing userAuditing;

    @Autowired
    StoryRepository storyrepos;

    @Autowired
    UserRepository userrepos;



    @Override
    public List<Story> findAll() {
        List<Story> list = new ArrayList<>();
        storyrepos.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

    @Override
    public Story findStoryById(long id) {
        return storyrepos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Story with id " + id + " Not Found"));
    }

    @Transactional
    @Override
    public void delete(long id) {

        if (storyrepos.findById(id).isPresent()){
            storyrepos.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Story with id " + id + " Not Found");
        }

    }

    @Transactional
    @Override
    public Story save(Story story){
        Story newStory = new Story();

        if(story.getStoryid() != 0){
            storyrepos.findById(story.getStoryid())
                    .orElseThrow(() -> new ResourceNotFoundException("Story with id " + story.getStoryid() + " Not Found"));
        }

        newStory.setTitle(story.getTitle());
        newStory.setLocation(story.getLocation());
        newStory.setDescription(story.getDescription());

        newStory.getUserstories()
                .clear();
        for(UserStories s : story.getUserstories()){
        User currentUser = userrepos.findById(s.getUser().getUserid())
                    .orElseThrow(() -> new ResourceNotFoundException("User Id " + s.getUser()
                    .getUserid() + " Not Found"));
        newStory.getUserstories()
                .add(new UserStories(currentUser, newStory));
        }
        return storyrepos.save(newStory);
    }

    @Transactional
    @Override
    public Story update(Story story, long storyid) {
        Story currentStory = findStoryById(storyid);

        if(story.getTitle() != null){
            currentStory.setTitle(story.getTitle());
        }
        if(story.getLocation() != null){
            currentStory.setLocation(story.getLocation());
        }
        if (story.getDescription() != null){
            currentStory.setDescription(story.getDescription());
        }
        if(story.getUserstories().size() > 0){
            for (UserStories s : story.getUserstories()){
                User addUser = userrepos.findById(s.getUser().getUserid())
                        .orElseThrow(() -> new ResourceNotFoundException("User Id " + s.getUser()
                                .getUserid() + " Not Found"));
                currentStory.getUserstories().add(new UserStories(addUser, currentStory));
            }
        }
        return storyrepos.save(currentStory);
    }

    @Override
    public void deleteAll() {
        storyrepos.deleteAll();
    }
}
