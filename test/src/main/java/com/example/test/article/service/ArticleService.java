package com.example.test.article.service;

import com.example.test.article.dto.ArticleDto;
import com.example.test.article.entity.ArticleEntity;
import com.example.test.article.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<ArticleEntity> index(){
        return articleRepository.findAll();
    }
    public ArticleEntity show(Long id){
        return articleRepository.findById(id).orElse(null);
    }

    public ArticleEntity create(ArticleDto articleDto){
        ArticleEntity article = articleDto.toEntity();
        if(article.getId() != null)
            return null;
        return articleRepository.save(article);
    }

    public ArticleEntity update(Long id, ArticleDto dto) {
        // 1: DTO -> 엔티티
        ArticleEntity article = dto.toEntity();
        log.info("id: {}, article: {}", id, article.toString());
        // 2: 타겟 조회
        ArticleEntity target = articleRepository.findById(id).orElse(null);
        // 3: 잘못된 요청 처리
        if (target == null || id != article.getId()) {
            // 400, 잘못된 요청 응답!
            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
            return null;
        }
        // 4: 업데이트
        target.patch(article);
        ArticleEntity updated = articleRepository.save(target);
        return updated;
    }
    public ArticleEntity delete(Long id) {
        // 대상 찾기
        ArticleEntity target = articleRepository.findById(id).orElse(null);
        // 잘못된 요청 처리
        if (target == null) {
            return null;
        }
        // 대상 삭제
        articleRepository.delete(target);
        return target;
    }

    @Transactional
    public List<ArticleEntity> createArticles(List<ArticleDto> dtos) {
        // dto 묶음을 entity 묶음으로 변환
        List<ArticleEntity> articleList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());
        // entity 묶음을 DB로 저장
        articleList.stream()
                .forEach(article -> articleRepository.save(article));
        // 강제 예외 발생
        articleRepository.findById(-1L).orElseThrow(
                () -> new IllegalArgumentException("결제 실패!")
        );
        // 결과값 반환
        return articleList;
    }

}
