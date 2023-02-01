package com.techelevator.dao;

import com.techelevator.model.Comment;

import java.util.List;

public interface CommentDao {


    public List<Comment> getCommentsByPost(int postId);


}
