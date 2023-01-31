package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Rating {

    @JsonProperty("rating_id")
    private int ratingId;
    @JsonProperty("post_id")
    private int postId;
    private int rating;

    public Rating(int ratingId, int postId, int rating) {
        this.ratingId = ratingId;
        this.postId = postId;
        this.rating = rating;
    }

    public Rating() {

    }

    public int getRatingId() {
        return ratingId;
    }

    public void setRatingId(int ratingId) {
        this.ratingId = ratingId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
