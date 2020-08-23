package com.lambdaschool.expat.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.domain.AuditorAware;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "stories")
public class Story extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long storyid;

    private String title;
    private String location;
    private String description;

    @OneToMany(mappedBy = "story",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnoreProperties("story")
    private Set<UserStories> userStories = new HashSet<>();

    public Story() {
    }

    public Story(String title, String location, String description) {
        this.title = title;
        this.location = location;
        this.description = description;
    }

    public long getStoryid() {
        return storyid;
    }

    public void setStoryid(long storyid) {
        this.storyid = storyid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<UserStories> getUserStories() {
        return userStories;
    }

    public void setUserStories(Set<UserStories> userStories) {
        this.userStories = userStories;
    }
}
