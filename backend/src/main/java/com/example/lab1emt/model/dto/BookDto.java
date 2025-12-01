package com.example.lab1emt.model.dto;


import com.example.lab1emt.model.domain.Category;
import lombok.Data;

@Data
public class BookDto {
    String name;
    Category category;
    Long authorId;
    Integer availableCopies;


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Integer getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(Integer availableCopies) {
        this.availableCopies = availableCopies;
    }
}
