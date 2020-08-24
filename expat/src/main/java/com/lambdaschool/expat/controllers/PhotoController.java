package com.lambdaschool.expat.controllers;

import com.lambdaschool.expat.models.Photo;
import com.lambdaschool.expat.repository.PhotoRepository;
import com.lambdaschool.expat.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/photos")
public class PhotoController {

    @Autowired
    PhotoService photoService;

    //GET photos
    @GetMapping(value = "/photos", produces = "application/json")
    public ResponseEntity<?> getAllPhotos() {
        List<Photo> photoList = photoService.findAllPhotos();
        return new ResponseEntity<>(photoList, HttpStatus.OK);
    }

    //GET photo by id
    @GetMapping(value = "/photo/{photoid}", produces = "application/json")
    public ResponseEntity<?> getPhotoById(@PathVariable long photoid) {
        Photo photo = photoService.findPhotoById(photoid);
        return new ResponseEntity<>(photo, HttpStatus.OK);
    }

    //POST photo
    @PostMapping(value = "/photo/story/{storyid}", consumes = "application/json")
    public ResponseEntity<?> addNewPhoto(@Valid @RequestBody Photo newPhoto, @PathVariable long storyid) {
        newPhoto.setPhotoid(0);
        newPhoto = photoService.save(newPhoto, storyid);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPhotoURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{userid}")
                .buildAndExpand(newPhoto.getPhotoid())
                .toUri();
        responseHeaders.setLocation(newPhotoURI);
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    //PUT photo
     @PutMapping(value = "/photo/{photoid}", consumes = "application/json")
    public ResponseEntity<?> updatePhoto(@Valid @RequestBody Photo uPhoto, @PathVariable long photoid) {
        photoService.update(uPhoto, photoid);
        return new ResponseEntity<>(HttpStatus.OK);
     }

    //DELETE photo
    @DeleteMapping(value = "/photo/{photoid}")
    public ResponseEntity<?> deletePhoto(@PathVariable long photoid) {
        photoService.delete(photoid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
