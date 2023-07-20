package com.example.test.article.repository;

import com.example.test.article.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {
    @Override
    ArrayList<ArticleEntity> findAll();

}
