package pl.cba.lalewicz.cmsfirst.model;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import pl.cba.lalewicz.cmsfirst.entity.Category;
import pl.cba.lalewicz.cmsfirst.entity.FileData;
import pl.cba.lalewicz.cmsfirst.utils.MyTools;

import java.time.LocalDate;
import java.util.List;

public class ExtendedArticle {
    private Long id;
    private String title;
    private String shortDescription;
    private String description;
    private LocalDate publicationDate;
    private List<Category> categoryList;
    private List<FileData> fileDataList;


    public ExtendedArticle(Long id, String title, String description, LocalDate publicationDate, List<Category> categoryList,List<FileData> fileDataList)  {
        this.id = id;
        this.title = title;
        this.shortDescription = MyTools.clearHtmlTags(description);
        this.description = description;
        this.publicationDate = publicationDate;
        this.categoryList = categoryList;
        this.fileDataList = fileDataList;
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

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
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

    public List<FileData> getFileDataList() {
        return fileDataList;
    }

    public void setFileDataList(List<FileData> fileDataList) {
        this.fileDataList = fileDataList;
    }
}
