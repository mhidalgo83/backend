package com.lambdaschool.expat.services;

import com.lambdaschool.expat.models.Story;

import java.util.List;

public interface StoryService {
    List<Story> findAll();

    Story findStoryById(long id);

    void delete(long id);

    Story save(Story role);

    Story update(Story role, long id);

    void deleteAll();
}
