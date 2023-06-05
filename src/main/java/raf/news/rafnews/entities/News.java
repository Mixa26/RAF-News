package raf.news.rafnews.entities;

import javax.validation.constraints.NotNull;

public class News {
    private int id;

    @NotNull(message = "Title must not be empty!")
    private String title;

    @NotNull(message = "Content must not be empty!")
    private String content;

    @NotNull(message = "Time created must be provided!")
    private Double timeCreated;

    @NotNull(message = "Number of visits must be provided!")
    private int numOfVisits;

    @NotNull(message = "User id must be provided!")
    private int userId;

    @NotNull(message = "Category id must be provided!")
    private int categoryId;

    public News(){}

    public News(int id, String title, String content, Double timeCreated, int numOfVisits, int userId, int categoryId) {
        this();
        this.id = id;
        this.title = title;
        this.content = content;
        this.timeCreated = timeCreated;
        this.numOfVisits = numOfVisits;
        this.userId = userId;
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Double getTimeCreated() {
        return timeCreated;
    }

    public int getNumOfVisits() {
        return numOfVisits;
    }

    public int getUserId() {
        return userId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTimeCreated(Double timeCreated) {
        this.timeCreated = timeCreated;
    }

    public void setNumOfVisits(int numOfVisits) {
        this.numOfVisits = numOfVisits;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
