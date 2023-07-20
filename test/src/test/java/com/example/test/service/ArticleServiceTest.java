package com.example.test.service;

import com.example.test.article.entity.ArticleEntity;
import com.example.test.article.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ArticleServiceTest {
    @Autowired
    ArticleService articleService;
    @Test
    void index() {
        ArticleEntity a = new ArticleEntity(null,"first", "가가가가", "1111");
        ArticleEntity b = new ArticleEntity(null,"second", "나나나나", "2222");
        ArticleEntity c = new ArticleEntity(null,"third", "다다다다", "3333");
        List<ArticleEntity> expected = new ArrayList<ArticleEntity>(Arrays.asList(a, b, c));
        // 실제
        List<ArticleEntity> articles = articleService.index();
        // 검증
        assertEquals(expected.toString(), articles.toString());
    }
}