package com.example.test.article.controller;

import com.example.test.article.dto.ArticleDto;
import com.example.test.article.entity.ArticleEntity;
import com.example.test.article.repository.ArticleRepository;
import com.example.test.comment.dto.CommentDto;
import com.example.test.comment.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j //자동으로 로깅코드를 생성하여 간편하게 로글르 출력
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CommentService commentService;
    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }
    @PostMapping("/articles/create")
    public String createArticle(ArticleDto articleDto) {
        log.info(articleDto.toString());    // println() 을 로깅으로 대체!

        ArticleEntity article = articleDto.toEntity();
        log.info(article.toString()); // println() 을 로깅으로 대체!

        ArticleEntity saved = articleRepository.save(article);
        log.info(saved.toString());   // println() 을 로깅으로 대체!

        return "redirect:/articles/"+saved.getId();
    }
    @GetMapping("/articles")
    public String index(Model model) {
        // 1: 모든 Article을 가져온다!
        List<ArticleEntity> articleEntityList = articleRepository.findAll();
        // 2: 가져온 Article 묶음을 뷰로 전달!
        model.addAttribute("articleList", articleEntityList);
        // 3: 뷰 페이지를 설정!
        return "articles/index";
    }
    @PostMapping("/articles/update") // form이 dto
    public String update(ArticleDto articleDto) {
        log.info(articleDto.toString());    // println() 을 로깅으로 대체!

        ArticleEntity articleEntity = articleDto.toEntity();
        log.info(articleEntity.toString());

        ArticleEntity target = articleRepository.findById(articleEntity.getId()).orElse(null);

        if(target != null){
            articleRepository.save(articleEntity);
        }

        return "redirect:/articles/"+articleEntity.getId();
    }
    @GetMapping("/articles/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("삭제 요청이 들어왔습니다!!");
        // 1: 삭제 대상을 가져옴
        ArticleEntity target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());
        // 2: 대상을 삭제
        if (target != null) {
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제가 완료되었습니다.");
        }
        // 3: 결과 페이지로 리다이렉트
        return "redirect:/articles";
    }
    @GetMapping("/articles/{id}") // 해당 URL요청을 처리 선언
    public String show(@PathVariable Long id,
                       Model model) { // URL에서 id를 변수로 가져옴
        log.info("id = " + id);
        // 1: id로 데이터를 가져옴!
        ArticleEntity articleEntity = articleRepository.findById(id).orElse(null);
        List<CommentDto> commentsDtos = commentService.comments(id);
        // 2: 가져온 데이터를 모델에 등록!
        model.addAttribute("article", articleEntity);
        model.addAttribute("commentDtos", commentsDtos);
        // 3: 보여줄 페이지를 설정!
        return "articles/show";
    }
}
