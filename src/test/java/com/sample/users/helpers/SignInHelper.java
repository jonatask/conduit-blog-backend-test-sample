package com.sample.users.helpers;

import com.sample.users.entities.request.LoginRequestEntity;
import com.sample.users.entities.request.LoginItemRequestEntity;
import com.sample.users.objectMothers.UserMother;
import com.sample.utils.Utils;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;

public class SignInHelper {

    LoginItemRequestEntity loginItemRequestEntity = new LoginItemRequestEntity();
    LoginRequestEntity loginRequestEntity = new LoginRequestEntity();
    UserMother userMother = new UserMother();

    public String getJwtAuthorization() {
        loginItemRequestEntity.setEmail(userMother.getUserForLoginRequest().getEmail());
        loginItemRequestEntity.setPassword(userMother.getUserForLoginRequest().getPassword());
        loginRequestEntity.setUser(loginItemRequestEntity);

        String token =
            given()
                .contentType(ContentType.JSON)
                .body(loginRequestEntity)
                .log().body()
            .when()
                .post(Utils.getBaseUrl().concat("/api/users/login"))
            .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
            .extract()
                .path("user.token");
        return token;
    }
}
