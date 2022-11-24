package pl.cba.lalewicz.cmsfirst.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.cba.lalewicz.cmsfirst.entity.Category;
import pl.cba.lalewicz.cmsfirst.repository.CategoryDao;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    public List<Category> getAllCategories(){
        return categoryDao.findAll();
    }

    public Category addNewCategory(Category category) {
        return categoryDao.save(category);
    }

    public Category getCategoryById(Long id) {
        return categoryDao.findById(id).get();
    }
}
