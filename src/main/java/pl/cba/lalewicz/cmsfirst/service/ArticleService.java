package pl.cba.lalewicz.cmsfirst.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.cba.lalewicz.cmsfirst.entity.Article;
import pl.cba.lalewicz.cmsfirst.repository.ArticleDao;

import java.util.ArrayList;
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
}
