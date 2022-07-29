package pl.cba.lalewicz.cmsfirst.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.cba.lalewicz.cmsfirst.entity.Article;
import pl.cba.lalewicz.cmsfirst.entity.Category;
import pl.cba.lalewicz.cmsfirst.service.ArticleService;
import pl.cba.lalewicz.cmsfirst.service.CategoryService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleService articleService;


    @GetMapping("/test")
    public String test(){
        return "Tes is OK";
    }


    @PostConstruct
    public void createSampleData() {
        //create category

        categoryService.addNewCategory(new Category("INF.02", "Kwalifikacja INF.02"));
        categoryService.addNewCategory(new Category("INF.03", "Kwalifikacja INF.03"));
        categoryService.addNewCategory(new Category("HTML", "Język HTML"));
        categoryService.addNewCategory(new Category("C++", "Języka programowania C++"));
        categoryService.addNewCategory(new Category("Java", "Języka programowania Java"));

        //create article
        Article article1 = new Article();
        article1.setTitle("Znaczniki HTML");
        article1.setDescription("Lorem ipsum ...");
        List<Category> categories = new ArrayList<>();
        categories.add(categoryService.getCategoryById(2l));
        categories.add(categoryService.getCategoryById(4l));
        article1.setCategoryList(categories);
        articleService.addArticle(article1);
    }
}
