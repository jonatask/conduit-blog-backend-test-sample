package com.sample.articles.objectMothers;

import com.sample.articles.entities.request.ArticleItemRequestEntity;
import com.sample.articles.entities.request.ArticleRequestEntity;
import com.sample.profiles.objectMothers.ProfileMother;

import java.util.List;
import java.util.Random;

public class ArticleMother {

    public ArticleRequestEntity getArticleRequest() {
        ArticleRequestEntity articleRequestEntity = new ArticleRequestEntity();
        articleRequestEntity.setArticle(getArticle());
        return articleRequestEntity;
    }

    public ArticleItemRequestEntity getArticle() {
        Random random = new Random();

        ArticleItemRequestEntity article = new ArticleItemRequestEntity();
        article.setSlug("slug-here-".concat(String.valueOf(random.nextInt(1000))));
        article.setTitle("Title here");
        article.setDescription("Description here");
        article.setBody("Body here");
        article.setTagList(List.of("tag1here", "tag2here"));
        article.setFavoritesCount(236);
        article.setAuthor(ProfileMother.getProfile());
        return article;
    }
}
