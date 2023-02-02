package com.techelevator.controller;

import com.techelevator.dao.ProfileDao;
import com.techelevator.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Profiles;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ProfileController {

    @Autowired
    private ProfileDao dao;

    @RequestMapping(path="/profiles", method = RequestMethod.GET)
    public List<Profile> getProfiles() {
        return dao.getProfiles();
    }

    @RequestMapping(path="/profiles/{username}", method = RequestMethod.GET)
    public Profile getProfile(@PathVariable String username) {
        return dao.getProfile(username);
    }

    @RequestMapping(path="/profiles/create", method = RequestMethod.POST)
    public int createProfile(@RequestBody Profile profile) {
        return dao.createProfile(profile);
    }

}
