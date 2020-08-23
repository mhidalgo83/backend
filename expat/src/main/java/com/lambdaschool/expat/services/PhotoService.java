package com.lambdaschool.expat.services;

import com.lambdaschool.expat.models.Photo;

import java.util.List;

public interface PhotoService {

    Photo save(Photo photo);

    Photo findPhotoById(long id);

    void delete(long id);

    void deleteAll();

    List<Photo> findAllPhotos();

    Photo update(Photo photo, long id);
}
