package com.techelevator.dao;

import com.techelevator.model.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;

@Component
@CrossOrigin
public class JdbcProfileDao implements ProfileDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcProfileDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Profile getProfile(String username) {
        String sql = "SELECT * FROM profiles p JOIN users u ON p.user_id = u.user_id WHERE username=?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, username);
        Profile profile = new Profile();
        if (result.next()) {
            profile = mapRowToProfile(result);
        }
        return profile;
    }

    @Override
    public List<Profile> getProfiles() {
        String sql = "SELECT * FROM profiles";
        List<Profile> profiles = new ArrayList<>();

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql);

        while(result.next()) {
            Profile profile = mapRowToProfile(result);
            profiles.add(profile);
        }

        return profiles;
    }

    @Override
    public int createProfile(Profile profile) {
        String sql = "INSERT INTO profiles (user_id, email, name, profile_image, profile_bio) " +
                     "VALUES (?, ?, ?, ?, ?)";

        int profileId;

        try {
            profileId = jdbcTemplate.queryForObject(sql, Integer.class, profile.getUserId(), profile.getEmail(), profile.getName(), profile.getProfileImage(), profile.getProfileBio());
        } catch (NullPointerException e) {
            return -1;
        }
        return profileId;
    }

    private static Profile mapRowToProfile(SqlRowSet result){
        Profile profile = new Profile();
        profile.setProfileId(result.getInt("profile_id"));
        profile.setUserId(result.getInt("user_id"));
        profile.setEmail(result.getString("email"));
        profile.setName(result.getString("name"));
        profile.setProfileImage(result.getString("profile_image"));
        profile.setProfileBio(result.getString("profile_bio"));
        return profile;
    }
}
