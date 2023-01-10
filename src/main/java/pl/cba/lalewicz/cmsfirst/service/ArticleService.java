package pl.cba.lalewicz.cmsfirst.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.cba.lalewicz.cmsfirst.entity.Article;
import pl.cba.lalewicz.cmsfirst.entity.Category;
import pl.cba.lalewicz.cmsfirst.entity.FileData;
import pl.cba.lalewicz.cmsfirst.model.ExtendedArticle;
import pl.cba.lalewicz.cmsfirst.repository.ArticleDao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleDao articleDao;

    public Page<ExtendedArticle> getArticles(int page, int size){
        List<Article> articles = (List<Article>) articleDao.findAll();
        articles.sort(((o1, o2) -> o2.getPublicationDate().compareTo(o1.getPublicationDate())));
        List<ExtendedArticle> extendedArticles = new ArrayList<>();// obiekt extendedArticle zawiera dodatkowe pole shortdescription wyczyszczone ze znacznikóœ html
        articles.forEach(article -> {
            extendedArticles.add(new ExtendedArticle(article.getId(), article.getTitle(), article.getDescription(), article.getPublicationDate(), article.getCategoryList(),article.getFileDataList()));
        });
        PageRequest pagable = PageRequest.of(page, size);
        int start = (int) pagable.getOffset();
        int end = Math.min((start + pagable.getPageSize()), extendedArticles.size());
        return new PageImpl<ExtendedArticle>(extendedArticles.subList(start,end), pagable, extendedArticles.size());


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


    public Article updateArticle(Article article){
        return articleDao.save(article);
    }

    public Page<ExtendedArticle> getArticlesByCategory(List<Category> categoryList, int page, int size) {
//        Page<Article> byCategoryList = articleDao.findByCategoryListIn(categoryList, PageRequest.of(page, size)); //poprzednia wersja powielała artykuły
        List<Article> byCategoryList = articleDao.findByCategoryListIn(categoryList); //lista powtarza elementy
//        System.out.println("all art  "+byCategoryList);
        List<Article> uniqueList = new ArrayList<>(new HashSet<>(byCategoryList)); //unikalna lista
        //sortowanie listy po datach
        uniqueList.sort(((o1, o2) -> o2.getPublicationDate().compareTo(o1.getPublicationDate())));
        List<ExtendedArticle> extendedArticles = new ArrayList<>();// obiekt extendedArticle zawiera dodatkowe pole shotdescription wyczyszczone ze znacznikóœ html
        uniqueList.forEach(article -> {
            extendedArticles.add(new ExtendedArticle(article.getId(), article.getTitle(), article.getDescription(), article.getPublicationDate(), article.getCategoryList(), article.getFileDataList()));
        });

//        System.out.println("unikalna "+extendedArticles);
        //https://stackoverflow.com/questions/2784514/sort-arraylist-of-custom-objects-by-property
        PageRequest pagable = PageRequest.of(page, size);
        int start = (int) pagable.getOffset();
        int end = Math.min((start + pagable.getPageSize()), extendedArticles.size());
//        System.out.println("poczatek :"+start+" | koniec: "+end+" | rozmiar listy : "+uniqueList.size());
        Page<ExtendedArticle> newPagesByCategoryList = new PageImpl<ExtendedArticle>(extendedArticles.subList(start,end), pagable, extendedArticles.size());
//        System.out.println(newPagesByCategoryList.getTotalElements());
//        System.out.println(newPagesByCategoryList.getContent());
        return newPagesByCategoryList;
    }

    public void deleteArticle(Long id) {
        articleDao.deleteById(id);
    }

    public Page<ExtendedArticle> serchInArticles(String serchText,int page, int size) {
        List<Article> byTitleLike = articleDao.findByTitleContaining(serchText);
        List<Article> byDescriptionLike = articleDao.findByDescriptionContaining(serchText);
        byTitleLike.addAll(byDescriptionLike);
        List<Article> uniqueList = new ArrayList<>(new HashSet<>(byTitleLike)); //unikalna lista
        //sortowanie listy po datach
        uniqueList.sort(((o1, o2) -> o2.getPublicationDate().compareTo(o1.getPublicationDate())));
        List<ExtendedArticle> extendedArticles = new ArrayList<>();// obiekt extendedArticle zawiera dodatkowe pole shotdescription wyczyszczone ze znacznikóœ html
        uniqueList.forEach(article -> {
            extendedArticles.add(new ExtendedArticle(article.getId(), article.getTitle(), article.getDescription(), article.getPublicationDate(), article.getCategoryList(), article.getFileDataList()));
        });
        System.out.println(extendedArticles);
        PageRequest pagable = PageRequest.of(page, size);
        int start = (int) pagable.getOffset();
        int end = Math.min((start + pagable.getPageSize()), extendedArticles.size());
        Page<ExtendedArticle> newPagesBySearch = new PageImpl<ExtendedArticle>(extendedArticles.subList(start,end), pagable, extendedArticles.size());
        return newPagesBySearch;
    }

    public void changeIpInArticle(String oldIp, String newIp) {
//        String oldIp, String newIp

//        final String oldIp = "192.168.0.58";
//        final String newIp = "192.168.10.250";
//        final String newIp = "192.168.0.58";
//        final String oldIp = "192.168.10.250";
        Iterable<Article> articles = articleDao.findAll();
        articles.forEach( article -> {
            if (article.getDescription().contains(oldIp)){
                article.setDescription(article.getDescription().replaceAll(oldIp, newIp));
            }
        });
        articleDao.saveAll(articles);
    }
}
