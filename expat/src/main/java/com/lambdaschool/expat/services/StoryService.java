package com.lambdaschool.expat.services;

import com.lambdaschool.expat.models.Story;

import java.util.List;

public interface StoryService {
    List<Story> findAll();

    Story findStoryById(long id);

    void delete(long id);

    Story save(Story story);

    Story update(Story story, long id);

    void deleteAll();
}
