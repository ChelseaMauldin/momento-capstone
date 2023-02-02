package com.techelevator.dao;

import com.techelevator.model.Post;

import java.util.List;

public interface PostDao {

    public List<Post> getPosts();

    public List<Post> getPostsByUsername(String username);

    public Post getPost(int id);

    public void createPost(Post post);

    public void likePost(int id);

    public void unLikePost(int id);
}
