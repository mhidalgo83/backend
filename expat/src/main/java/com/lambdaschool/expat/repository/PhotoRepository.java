package com.lambdaschool.expat.repository;

import com.lambdaschool.expat.models.Photo;
import org.springframework.data.repository.CrudRepository;

public interface PhotoRepository extends CrudRepository<Photo, Long> {
}
