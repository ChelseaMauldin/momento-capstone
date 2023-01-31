package com.techelevator.dao;

import com.techelevator.model.Post;

import java.util.List;

public interface PostDao {

    public List<Post> getPosts();

    public Post getPost(int id);

    public void createPost(Post post);
}
