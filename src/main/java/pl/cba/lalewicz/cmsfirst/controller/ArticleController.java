package pl.cba.lalewicz.cmsfirst.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import pl.cba.lalewicz.cmsfirst.service.ArticleService;

@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;


}
