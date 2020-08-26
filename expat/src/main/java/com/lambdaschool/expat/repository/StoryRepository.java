package com.lambdaschool.expat.repository;

import com.lambdaschool.expat.models.Story;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StoryRepository extends CrudRepository<Story, Long> {

}
