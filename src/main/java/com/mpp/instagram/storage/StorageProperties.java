package com.mpp.instagram.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String postLocation = "upload-dir/post";
    private String profileLocation = "upload-dir/profile";

    public String getLocation() {
        return postLocation;
    }

    public void setLocation(String location) {
        this.postLocation = location;
    }

    public String getProfileLocation() {
        return profileLocation;
    }

    public void setProfileLocation(String profileLocation) {
        this.profileLocation = profileLocation;
    }
}
