package raf.news.rafnews.entities;

import javax.validation.constraints.NotNull;

public class Category {
    private int id;

    @NotNull(message = "Name of category must not be empty!")
    private String name;

    @NotNull(message = "Description must not be empty!")
    private String description;

    public Category(){}

    public Category(int id, String name, String description) {
        this();
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
