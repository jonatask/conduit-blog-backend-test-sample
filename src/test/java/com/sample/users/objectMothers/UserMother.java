package com.sample.users.objectMothers;

import com.sample.users.entities.UserEntity;
import io.github.cdimascio.dotenv.Dotenv;

public class UserMother {
    private String username = "username-here";
    private String bio = "Bio here";
    private String image = "image-here";

    public UserEntity getUserForLoginRequest() {
        Dotenv dotenv = null;
        dotenv = Dotenv.configure().load();

        UserEntity user = new UserEntity();
        user.setEmail(dotenv.get("ACCEPTANCE_TEST_EDITOR_EMAIL"));
        user.setPassword(dotenv.get("ACCEPTANCE_TEST_EDITOR_PASSWORD"));
        user.setUsername(dotenv.get("ACCEPTANCE_TEST_EDITOR_USERNAME"));
        user.setImage(dotenv.get("ACCEPTANCE_TEST_EDITOR_IMAGE_URL"));
        user.setBio(dotenv.get("ACCEPTANCE_TEST_EDITOR_BIO"));
        return user;
    }

    public String getUsername(){
        return username;
    }

    public String getBio() {
        return bio;
    }

    public String getImage() {
        return image;
    }
}
