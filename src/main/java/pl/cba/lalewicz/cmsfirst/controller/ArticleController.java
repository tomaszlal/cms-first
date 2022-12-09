package pl.cba.lalewicz.cmsfirst.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import pl.cba.lalewicz.cmsfirst.entity.Article;
import pl.cba.lalewicz.cmsfirst.entity.Category;
import pl.cba.lalewicz.cmsfirst.model.ExtendedArticle;
import pl.cba.lalewicz.cmsfirst.service.ArticleService;

import java.util.List;


@CrossOrigin
@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;


    @GetMapping("/articles")
    // http://localhost:9292/articles?page=1&size=2
    public Page<ExtendedArticle> getArticles(@RequestParam("page") int page, @RequestParam("size") int size) {
        return articleService.getArticles(page,size);
    }

    @GetMapping("/allarticles")
    public List<Article> getAllArticles() {
        return articleService.getAllArticles();
    }


    @PostMapping("/addarticle")
    public Article saveArticle(@RequestBody Article article){
        return articleService.addArticle(article);
    }


    @PostMapping("/articlebycategory")
    public Page<ExtendedArticle> getArticlesByCategory(@RequestBody List<Category> categoryList, @RequestParam("page") int page, @RequestParam("size") int size){
        return articleService.getArticlesByCategory(categoryList,page,size);
    }
 }
