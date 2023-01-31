package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Post {

    @JsonProperty("post_id")
    private int postId;
    private String username;
    @JsonProperty("photo_url")
    private String photoUrl;
    private int likes;
    private String caption;

    public Post(int postId, String username, String photoUrl, int likes, String caption) {
        this.postId = postId;
        this.username = username;
        this.photoUrl = photoUrl;
        this.likes = likes;
        this.caption = caption;
    }

    public Post(){

    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
