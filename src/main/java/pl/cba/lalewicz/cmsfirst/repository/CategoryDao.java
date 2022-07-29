package pl.cba.lalewicz.cmsfirst.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.cba.lalewicz.cmsfirst.entity.Category;

@Repository
public interface CategoryDao extends JpaRepository<Category,Long> {
}
