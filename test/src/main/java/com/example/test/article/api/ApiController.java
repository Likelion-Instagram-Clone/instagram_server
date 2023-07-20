package com.example.test.article.api;

import com.example.test.article.dto.ArticleDto;
import com.example.test.article.entity.ArticleEntity;
import com.example.test.article.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class ApiController {
    @Autowired
    private ArticleService articleService;

   /* @GetMapping("/articles/{id}")
    public ArticleEntity show(@PathVariable Long id){
        return articleRepository.findById(id).orElse(null);
    }*/
   @GetMapping("/api/articles")
   public List<ArticleEntity> index(){
       return articleService.index();
   }
    @GetMapping("/api/articles/{id}")
    public ArticleEntity show(@PathVariable Long id){
        return articleService.show(id);
    }
   @PostMapping("/api/articles")
   public ResponseEntity<ArticleEntity> create(@RequestBody ArticleDto dto) {
       ArticleEntity created = articleService.create(dto);
       return (created != null) ?
               ResponseEntity.status(HttpStatus.OK).body(created) :
               ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
   }
    // PathVariable 란 일부 경로를 변수로 활용 ex) /{id} 같이 , 동적인 부분을 추출
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<ArticleEntity> update(@PathVariable Long id,
                                          @RequestBody ArticleDto dto) {
        ArticleEntity updated = articleService.update(id, dto);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<ArticleEntity> delete(@PathVariable Long id) {
        ArticleEntity deleted = articleService.delete(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<ArticleEntity>> transactionTest(@RequestBody List<ArticleDto> dtos) {
        List<ArticleEntity> createdList = articleService.createArticles(dtos);
        return (createdList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(createdList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
