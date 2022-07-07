package com.sample.profiles;

import com.sample.profiles.entities.ProfileEntity;
import com.sample.profiles.entities.ProfileResponse;
import com.sample.users.entities.UserEntity;
import com.sample.users.helpers.SignInHelper;
import static io.restassured.RestAssured.given;

import com.sample.users.objectMothers.UserMother;
import com.sample.utils.Utils;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProfileTest {
    String token = "";

    @BeforeEach
    public void init() {
        SignInHelper signInHelper = new SignInHelper();
        token = signInHelper.getJwtAuthorization();
    }

    @Test
    public void getProfile_expectOK_expectProfileInResponse() {
        UserMother userMother = new UserMother();
        UserEntity user = userMother.getUserForLoginRequest();

        ProfileResponse profileResponse =
            given()
                .header("jwtauthorization", token)
                .contentType(ContentType.JSON)
                .log().body()
            .when()
                .get(Utils.getBaseUrl().concat("/api/profiles/").concat(user.getUsername()))
            .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract().response().as(ProfileResponse.class);

        ProfileEntity profile = profileResponse.getProfile();

        Assertions.assertEquals(user.getUsername(), profile.getUsername());
        Assertions.assertEquals(user.getImage(), profile.getImage());
        Assertions.assertEquals(user.getBio(), profile.getBio());
        Assertions.assertEquals(user.isFollowing(), profile.isFollowing());
    }
}
