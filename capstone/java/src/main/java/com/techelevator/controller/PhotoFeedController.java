package com.techelevator.controller;

import com.techelevator.dao.CommentDao;
import com.techelevator.dao.PostDao;
import com.techelevator.model.Comment;
import com.techelevator.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class PhotoFeedController {

    @Autowired
    private PostDao dao;
    @Autowired
    private CommentDao commentDao;



    @RequestMapping(path="/posts", method = RequestMethod.GET)
    public List<Post> getPosts(){
        return dao.getPosts();
    }

    @RequestMapping(path="/posts/{id}", method = RequestMethod.GET)
    public Post getPost(@PathVariable int id){
        return dao.getPost(id);
    }

    @RequestMapping(path="/post/{id}/comments", method = RequestMethod.GET)
    public List<Comment> getComments(@PathVariable int id){
        return commentDao.getCommentsByPost(id);
    }
}
