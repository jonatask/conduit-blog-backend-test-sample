package com.sample.profiles.objectMothers;

import com.sample.profiles.entities.ProfileEntity;
import com.sample.users.objectMothers.UserMother;

public class ProfileMother {

    private static boolean FOLLOWING = true;

    public static ProfileEntity getProfile() {
        UserMother userMother = new UserMother();

        ProfileEntity profile = new ProfileEntity();
        profile.setUsername(userMother.getUsername());
        profile.setBio(userMother.getBio());
        profile.setImage(userMother.getImage());
        profile.setFollowing(FOLLOWING);
        return profile;
    }
}
