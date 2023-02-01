package com.techelevator.dao;


import com.techelevator.model.Post;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
@CrossOrigin
public class JdbcPostDao implements PostDao{
    private JdbcTemplate jdbcTemplate;

    public JdbcPostDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Post> getPosts() {
        String sql = "SELECT * FROM posts";
        List<Post> posts = new ArrayList<>();

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql);

        while(result.next()) {
            Post post = mapRowToPost(result);
            posts.add(post);
        }

        return posts;
    }

    private static Post mapRowToPost(SqlRowSet result){
            Post post = new Post();
            post.setPostId(result.getInt("post_id"));
             post.setUsername(result.getString("username"));
            post.setPhotoUrl(result.getString("photo_url"));
            post.setLikes(result.getInt("likes"));
            post.setCaption(result.getString("caption"));
            return post;

    }

    @Override
    public Post getPost(int id) {
        return null;
    }

    @Override
    public void createPost(Post post) {

    }

    @Override
    public void likePost(int id) {

    }

    @Override
    public void unLikePost(int id) {
        
    }
}
