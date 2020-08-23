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
    @JoinColumn(name = "userid",
    nullable = false)
    @JsonIgnoreProperties(value = "photos", allowSetters = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "storyid", nullable = false)
    @JsonIgnoreProperties(value = "photos", allowSetters = true)
    private Story story;

    public Photo() {
    }

    public Photo(String imageurl, String description, User user, Story story) {
        this.imageurl = imageurl;
        this.description = description;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }

}
