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

    @PutMapping("/updatearticle")
    public Article updateArticle(@RequestBody Article article) {
        return articleService.updateArticle(article);
    }


    @PostMapping("/articlebycategory")
    public Page<ExtendedArticle> getArticlesByCategory(@RequestBody List<Category> categoryList, @RequestParam("page") int page, @RequestParam("size") int size){
        return articleService.getArticlesByCategory(categoryList,page,size);
    }

    @DeleteMapping("/deletearticle/{id}")
    public List<Article> deleteArticle(@PathVariable Long id){
        articleService.deleteArticle(id);
        return articleService.getAllArticles();
    }

    @GetMapping("/search")
    public Page<ExtendedArticle> serchTextInArticle(@RequestParam("searchtext") String searchText, @RequestParam("page") int page, @RequestParam("size") int size) {

        return articleService.serchInArticles(searchText,page,size);
    }

    @GetMapping("/changeiparticle")
    public void changeIp(@RequestParam("oldip") String oldIp, @RequestParam("newip") String newIp ) {
        articleService.changeIpInArticle(oldIp,newIp);
    }
 }
