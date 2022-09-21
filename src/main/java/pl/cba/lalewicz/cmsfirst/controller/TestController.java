package pl.cba.lalewicz.cmsfirst.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.cba.lalewicz.cmsfirst.entity.Article;
import pl.cba.lalewicz.cmsfirst.entity.Category;
import pl.cba.lalewicz.cmsfirst.service.ArticleService;
import pl.cba.lalewicz.cmsfirst.service.CategoryService;

import javax.annotation.PostConstruct;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleService articleService;


    @GetMapping("/")
    public String test(){
        return "CMS started";
    }


    @PostConstruct
    public void createSampleData() {
        //create category

        categoryService.addNewCategory(new Category("INF.02", "Kwalifikacja INF.02"));
        categoryService.addNewCategory(new Category("INF.03", "Kwalifikacja INF.03"));
        categoryService.addNewCategory(new Category("HTML", "Język HTML"));
        categoryService.addNewCategory(new Category("C++", "Języka programowania C++"));
        categoryService.addNewCategory(new Category("Java", "Języka programowania Java"));
        categoryService.addNewCategory(new Category("Linux", "Zadania z Linuxem"));


        //create article
        Article article1 = new Article();
        article1.setTitle("Znaczniki HTML");
        article1.setDescription("Lorem ipsum dolor sit amet consectetur adipisicing elit. Consectetur ut quidem ipsum aliquam necessitatibus id pariatur iure perspiciatis quisquam magni, accusamus earum? Neque iusto perferendis numquam maxime, doloribus quisquam facere.");
        article1.setPublicationDate(LocalDate.of(2022,06,25));
        List<Category> categories = new ArrayList<>();
        categories.add(categoryService.getCategoryById(2l));
        categories.add(categoryService.getCategoryById(3l));
        article1.setCategoryList(categories);
        articleService.addArticle(article1);

        Article article2 = new Article();
        article2.setTitle("Zmienne C++");
        article2.setDescription("Lorem ipsum dolor sit amet consectetur adipisicing elit. Consectetur ut quidem ipsum aliquam necessitatibus id pariatur iure perspiciatis quisquam magni, accusamus earum? Neque iusto perferendis numquam maxime, doloribus quisquam facere.");
        article2.setPublicationDate(LocalDate.of(2022,07,14));
        categories = new ArrayList<>();
        categories.add(categoryService.getCategoryById(2l));
        categories.add(categoryService.getCategoryById(4l));
        article2.setCategoryList(categories);
        articleService.addArticle(article2);

        Article article3 = new Article();
        article3.setTitle("Montaż Ram");
        article3.setDescription("Lorem ipsum dolor sit amet consectetur adipisicing elit. Consectetur ut quidem ipsum aliquam necessitatibus id pariatur iure perspiciatis quisquam magni, accusamus earum? Neque iusto perferendis numquam maxime, doloribus quisquam facere.");
        article3.setPublicationDate(LocalDate.of(2022,4,1));
        categories = new ArrayList<>();
        categories.add(categoryService.getCategoryById(1l));
        article3.setCategoryList(categories);
        articleService.addArticle(article3);

        Article article4 = new Article();
        article4.setTitle("Konfiguracja użytkowników Linuxa");
        article4.setDescription("Lorem ipsum dolor sit amet consectetur adipisicing elit. Consectetur ut quidem ipsum aliquam necessitatibus id pariatur iure perspiciatis quisquam magni, accusamus earum? Neque iusto perferendis numquam maxime, doloribus quisquam facere.");
        article4.setPublicationDate(LocalDate.of(2022,9,18));
        categories = new ArrayList<>();
        categories.add(categoryService.getCategoryById(1l));
        categories.add(categoryService.getCategoryById(6l));
        article4.setCategoryList(categories);
        articleService.addArticle(article4);

        Article article5 = new Article();
        article5.setTitle("Serwer Windows");
        article5.setDescription("Lorem ipsum dolor sit amet consectetur adipisicing elit. Consectetur ut quidem ipsum aliquam necessitatibus id pariatur iure perspiciatis quisquam magni, accusamus earum? Neque iusto perferendis numquam maxime, doloribus quisquam facere.");
        article5.setPublicationDate(LocalDate.of(2022,10,2));
        categories = new ArrayList<>();
        categories.add(categoryService.getCategoryById(1l));
        categories.add(categoryService.getCategoryById(3l));
        article5.setCategoryList(categories);
        articleService.addArticle(article5);
    }
}
