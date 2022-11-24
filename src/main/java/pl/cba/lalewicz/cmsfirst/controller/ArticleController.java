package pl.cba.lalewicz.cmsfirst.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.cba.lalewicz.cmsfirst.entity.Article;
import pl.cba.lalewicz.cmsfirst.service.ArticleService;

import java.util.List;


@CrossOrigin
@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;


    @GetMapping("/articles")
    // http://localhost:9292/articles?page=1&size=2
    public Page<Article> getArticles(@RequestParam("page") int page, @RequestParam("size") int size) {
        return articleService.getArticles(page,size);
    }

    @GetMapping("/allarticles")
    public List<Article> getAllArticles() {
        return articleService.getAllArticles();
    }
}
