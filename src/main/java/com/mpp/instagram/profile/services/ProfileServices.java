package com.mpp.instagram.profile.services;

import com.mpp.instagram.profile.entity.ProfileEntity;
import com.mpp.instagram.profile.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServices {

    @Autowired
    ProfileRepository profileRepo ;


    public ProfileEntity findByUsername(String username) {
        return profileRepo.findByUsername(username);
    }

    public ProfileEntity save(ProfileEntity profileEntity) {
        return profileRepo.save(profileEntity);
    }
}
