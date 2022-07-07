package com.sample.articles;

import com.sample.articles.entities.request.ArticleItemRequestEntity;
import com.sample.articles.entities.request.ArticleRequestEntity;
import com.sample.articles.entities.response.ArticleItemResponseEntity;
import com.sample.articles.entities.response.ArticleResponseEntity;
import com.sample.articles.objectMothers.ArticleMother;
import com.sample.users.entities.UserEntity;
import com.sample.users.helpers.SignInHelper;
import com.sample.users.objectMothers.UserMother;
import com.sample.utils.Utils;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArticleTest {

    ArticleMother articleMother = new ArticleMother();
    ArticleRequestEntity articleRequestEntity = articleMother.getArticleRequest();
    ArticleItemRequestEntity article = articleRequestEntity.getArticle();
    UserMother userMother = new UserMother();
    UserEntity user = userMother.getUserForLoginRequest();
    String token = "";

    @BeforeEach
    public void init() {
        SignInHelper signInHelper = new SignInHelper();
        token = "Token ".concat(signInHelper.getJwtAuthorization());
    }

    @AfterEach
    public void deleteArticle_expectOK() {
        given()
            .header("jwtauthorization", token)
            .contentType(ContentType.JSON)
            .log().body()
        .when()
            .delete(Utils.getBaseUrl().concat("/api/articles/").concat(article.getSlug()))
        .then()
            .assertThat()
            .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void postArticle_expectOK_expectArticleInResponse() {
        ArticleResponseEntity articleResponseEntity =
            given()
                .header("jwtauthorization", token)
                .contentType(ContentType.JSON)
                .body(articleRequestEntity).
                log().body().
            when().
                post(Utils.getBaseUrl().concat("/api/articles")).
            then().
                assertThat()
                    .statusCode(HttpStatus.SC_OK)
            .extract().response().as(ArticleResponseEntity.class);

        ArticleItemResponseEntity articleItemResponseEntity = articleResponseEntity.getArticle();

        Assertions.assertEquals(article.getSlug(), articleItemResponseEntity.getSlug());
        Assertions.assertEquals(article.getTitle(), articleItemResponseEntity.getTitle());
        Assertions.assertEquals(article.getDescription(), articleItemResponseEntity.getDescription());
        Assertions.assertEquals(article.getBody(), articleItemResponseEntity.getBody());
        Assertions.assertNotNull(articleItemResponseEntity.getCreatedAt());
        Assertions.assertNotNull(articleItemResponseEntity.getUpdatedAt());
        Assertions.assertArrayEquals(article.getTagList().toArray(), articleItemResponseEntity.getTagList().toArray());
        Assertions.assertFalse(articleItemResponseEntity.isFavorited());
        Assertions.assertEquals(article.getFavoritesCount(), articleItemResponseEntity.getFavoritesCount());
        Assertions.assertEquals(userMother.getUserForLoginRequest().getUsername(), articleItemResponseEntity.getAuthor().getUsername());
        Assertions.assertNotNull(articleItemResponseEntity.getAuthor().getImage());
        Assertions.assertFalse(articleItemResponseEntity.isFollowing());
    }
}
