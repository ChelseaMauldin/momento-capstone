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

    public List<Post> getPostsByUsername(String username) {
        String sql = "SELECT * FROM posts WHERE username = ?" +
                "ORDER BY post_id DESC";

        List<Post> posts = new ArrayList<>();

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, username);

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
        String sql = "SELECT * FROM posts WHERE post_id=?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
        Post post = new Post();
        if (result.next()) {
            post = mapRowToPost(result);
        }
        return post;
    }

    @Override
    public int createPost(Post post) {
        String sql = "INSERT INTO posts (username, photo_url, likes, caption)\n" +
                "VALUES (?, ?, ?, ?) RETURNING post_id";
        int postId;
        try{
            postId=jdbcTemplate.queryForObject(sql, Integer.class, post.getUsername(), post.getPhotoUrl(), post.getLikes(), post.getCaption());
        } catch(Exception e){
            System.out.println(e.getMessage());
            return -1;
        }
        return postId;

    }

    @Override
    public void likePost(int id) {
        String sql = "UPDATE posts \n" +
                "SET likes = (SELECT likes FROM posts WHERE post_id=?) + 1\n" +
                "WHERE post_id=?";
        jdbcTemplate.update(sql, id, id);
    }

    @Override
    public void unLikePost(int id) {
        String sql = "UPDATE posts \n" +
                "SET likes = (SELECT likes FROM posts WHERE post_id=?) - 1\n" +
                "WHERE post_id=?";
        jdbcTemplate.update(sql, id, id);
    }

    @Override
    public void editCaption(Post post) {
        String sql = "UPDATE posts " +
                "SET caption = ? " +
                "WHERE post_id = ?";
        jdbcTemplate.update(sql, post.getCaption() , post.getPostId());
    }

    @Override
    public void deletePost(int id) {
        String sql = "DELETE FROM posts " +
                "WHERE post_id = ?";
        jdbcTemplate.update(sql, id);
    }
}
