package com.lambdaschool.expat.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "userstories")
@IdClass(UserStoriesId.class)
public class UserStories extends Auditable implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties("userstories")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "storyid")
    @JsonIgnoreProperties("userstories")
    private Story story;

    public UserStories() {
    }

    public UserStories(User user, Story story) {
        this.user = user;
        this.story = story;
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

    @Override
    public int hashCode() {
        return 37;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserStories)) {
            return false;
        }
        UserStories that = (UserStories) o;
        return ((user == null) ? 0 : user.getUserid()) == ((that.user == null) ? 0 : that.user.getUserid()) &&
                ((story == null) ? 0 : story.getStoryid()) == ((that.story == null) ? 0 : that.story.getStoryid());
    }
}
