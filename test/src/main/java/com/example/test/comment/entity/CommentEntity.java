package com.example.test.comment.entity;

import com.example.test.article.entity.ArticleEntity;
import com.example.test.comment.dto.CommentDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private ArticleEntity article;

  /* @ManyToOne
    @JoinColumn(name="user_id")
    private UserEntity user;*/

    @Column
    private String nickname;
    @Column
    private String comment;
    public static CommentEntity createComment(CommentDto dto, ArticleEntity article) {
        // 예외 발생
        if (dto.getId() != null)
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야 합니다.");
        if (dto.getArticleId() != article.getId())
            throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id가 잘못되었습니다.");
        // 엔티티 생성 및 반환
        return new CommentEntity(
                dto.getId(),
                article,
                dto.getNickname(),
                dto.getComment()
        );
    }
    public void patch(CommentDto dto) {
        // 예외 발생
        if (this.id != dto.getId())
            throw new IllegalArgumentException("댓글 수정 실패! 잘못된 id가 입력되었습니다.");
        // 객체를 갱신
        if (dto.getNickname() != null)
            this.nickname = dto.getNickname();
        if (dto.getComment() != null)
            this.comment = dto.getComment();
    }

}
