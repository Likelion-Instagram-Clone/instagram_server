package com.example.test.comment.dto;

import com.example.test.comment.entity.CommentEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
@Setter
public class CommentDto {
    private Long id;

    @JsonProperty("article_id")
    private Long articleId;
   // private Long userId;

    private String nickname;
    private String comment;
    public static CommentDto createCommentDto(CommentEntity comment) {
        return new CommentDto(
                comment.getId(),
                comment.getArticle().getId(),
                comment.getNickname(),
                comment.getComment()
        );
    }

}
