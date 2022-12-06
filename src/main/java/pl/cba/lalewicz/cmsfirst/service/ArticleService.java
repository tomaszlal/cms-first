package pl.cba.lalewicz.cmsfirst.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.cba.lalewicz.cmsfirst.entity.Article;
import pl.cba.lalewicz.cmsfirst.entity.Category;
import pl.cba.lalewicz.cmsfirst.repository.ArticleDao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleDao articleDao;

    public Page<Article> getArticles(int page, int size){
        return articleDao.findAll(PageRequest.of(page,size));
    }

    public List<Article> getAllArticles(){
        List<Article> articleList = new ArrayList<>();
        Iterable<Article> articleIterable = articleDao.findAll();
        for (Article article:articleIterable) {
            articleList.add(article);
        }
        return articleList;
    }

    public Article addArticle(Article article){
        return articleDao.save(article);
    }

    public Page<Article> getArticlesByCategory(List<Category> categoryList, int page, int size) {
//        Page<Article> byCategoryList = articleDao.findByCategoryListIn(categoryList, PageRequest.of(page, size)); //poprzednia wersja powielała artykuły
        List<Article> byCategoryList = articleDao.findByCategoryListIn(categoryList); //lista powtarza elementy
//        System.out.println("all art  "+byCategoryList);
        ArrayList<Article> uniqueList = new ArrayList<>(new HashSet<>(byCategoryList)); //unikalna lista
//        System.out.println("unikalna "+uniqueList);
        PageRequest pagable = PageRequest.of(page, size);
        int start = (int) pagable.getOffset();
        int end = Math.min((start + pagable.getPageSize()), uniqueList.size());
//        System.out.println("poczatek :"+start+" | koniec: "+end+" | rozmiar listy : "+uniqueList.size());
        Page<Article> newPagesByCategoryList = new PageImpl<Article>(uniqueList.subList(start,end), pagable, uniqueList.size());
//        System.out.println(newPagesByCategoryList.getTotalElements());
//        System.out.println(newPagesByCategoryList.getContent());
        return newPagesByCategoryList;
    }

}
