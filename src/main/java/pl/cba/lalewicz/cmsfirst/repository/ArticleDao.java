package pl.cba.lalewicz.cmsfirst.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.cba.lalewicz.cmsfirst.entity.Article;
import pl.cba.lalewicz.cmsfirst.entity.Category;

import java.util.List;

@Repository
public interface ArticleDao extends PagingAndSortingRepository<Article, Long> {

    public Page<Article> findByCategoryListIn(List<Category> category, Pageable pageable);
}
