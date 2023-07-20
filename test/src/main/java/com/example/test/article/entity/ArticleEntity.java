package com.example.test.article.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@ToString
@Getter
@Setter
@NoArgsConstructor
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String title;
    @Column
    private String content;

    public void patch(ArticleEntity articleEntity){
        if(articleEntity.name != null)
            this.name = articleEntity.name;
        if(articleEntity.title != null)
            this.title = articleEntity.title;
        if(articleEntity.content != null)
            this.content = articleEntity.content;

    }
}
