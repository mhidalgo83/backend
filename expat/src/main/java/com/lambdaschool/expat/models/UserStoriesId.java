package com.lambdaschool.expat.models;

import java.io.Serializable;
import java.util.Objects;

public class UserStoriesId implements Serializable {
    private long user;
    private long story;

    public UserStoriesId() {
    }

    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
    }

    public long getStory() {
        return story;
    }

    public void setStory(long story) {
        this.story = story;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, story);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        UserStoriesId userStoriesId = (UserStoriesId) o;
        return user == userStoriesId.user &&
                story == userStoriesId.story;
    }
}
