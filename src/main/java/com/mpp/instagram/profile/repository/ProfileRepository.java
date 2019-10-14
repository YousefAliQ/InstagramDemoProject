package com.mpp.instagram.profile.repository;

import com.mpp.instagram.profile.entity.ProfileEntity;
import com.mpp.instagram.user.entity.UserEntity;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends CrudRepository<ProfileEntity, Long> {
    @AllowFiltering
    public ProfileEntity findByUsername(String username);
}
