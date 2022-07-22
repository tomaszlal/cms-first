package pl.cba.lalewicz.cmsfirst.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.cba.lalewicz.cmsfirst.repository.ArticleDao;

@Service
public class ArticleService {

    @Autowired
    private ArticleDao articleDao;

}
