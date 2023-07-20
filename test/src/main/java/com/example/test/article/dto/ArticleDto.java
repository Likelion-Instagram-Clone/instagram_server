package com.example.test.article.dto;

import com.example.test.article.entity.ArticleEntity;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ArticleDto {
    private Long id;
    private String name;
    private String title;
    private String content;

    public ArticleEntity toEntity(){
        return new ArticleEntity(id,name,title,content);
    }
}
