package com.example.test.comment.repository;


import com.example.test.comment.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    @Query(value =
            "SELECT * " +
                    "FROM comment " +
                    "WHERE article_id = :articleId",
            nativeQuery = true)
    List<CommentEntity> findByArticleId(@Param("articleId")Long articleId); //이름이 일치하지 않거나, 이름을 더 명확하게 지정하고 싶을 때 @Param을 사용하여 매개변수 이름을 지정
    List<CommentEntity> findByNickname(String nickname);
}
