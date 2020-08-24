package com.lambdaschool.expat.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "tables")
public class Photo extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long photoid;

    @Column(nullable = false)
    private String imageurl;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "storyid", nullable = false)
    @JsonIgnoreProperties(value = "photos", allowSetters = true)
    private Story story;

    public Photo() {
    }

    public Photo(String imageurl, String description, Story story) {
        this.imageurl = imageurl;
        this.description = description;
        this.story = story;
    }

    public long getPhotoid() {
        return photoid;
    }

    public void setPhotoid(long photoid) {
        this.photoid = photoid;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }

}
