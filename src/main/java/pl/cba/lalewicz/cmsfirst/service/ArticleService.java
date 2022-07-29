package pl.cba.lalewicz.cmsfirst.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.cba.lalewicz.cmsfirst.entity.Article;
import pl.cba.lalewicz.cmsfirst.repository.ArticleDao;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleDao articleDao;

    public Page<Article> getArticles(){
        return articleDao.findAll(PageRequest.of(0,10));
    }

    public Article addArticle(Article article){
        return articleDao.save(article);
    }
}
