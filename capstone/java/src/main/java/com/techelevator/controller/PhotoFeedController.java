package com.techelevator.controller;

import com.techelevator.dao.PostDao;
import com.techelevator.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class PhotoFeedController {

    @Autowired
    private PostDao dao;

    public PhotoFeedController(PostDao postDao){
        this.dao = postDao;
    }

    @RequestMapping(path="/posts", method = RequestMethod.GET)
    public List<Post> getPosts(){
        return dao.getPosts();
    }
}
