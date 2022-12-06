package pl.cba.lalewicz.cmsfirst.entity;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    private LocalDate publicationDate;

    @ManyToMany
    private List<Category> categoryList;

    public Article() {
    }

    public Article(String title, String description, LocalDate publicationDate, List<Category> categoryList) {
        this.title = title;
        this.description = description;
        this.publicationDate = publicationDate;
        this.categoryList = categoryList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", categoryList=" + categoryList +
                '}';
    }
}
