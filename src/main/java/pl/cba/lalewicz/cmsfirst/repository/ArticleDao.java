package pl.cba.lalewicz.cmsfirst.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.cba.lalewicz.cmsfirst.entity.Article;

@Repository
public interface ArticleDao extends PagingAndSortingRepository<Article, Long> {
}
