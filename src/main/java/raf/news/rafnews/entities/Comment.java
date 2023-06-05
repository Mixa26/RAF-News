package raf.news.rafnews.entities;

import javax.validation.constraints.NotNull;

public class Comment {

    private int id;

    @NotNull(message = "Author must not be empty!")
    private String author;

    @NotNull(message = "Content must not be empty!")
    private String content;

    @NotNull(message = "Time created must be provided!")
    private Double timeCreated;

    @NotNull(message = "News id must be provided!")
    private int newsId;

    public Comment(){}

    public Comment(int id, String author, String content, Double timeCreated, int newsId) {
        this();
        this.id = id;
        this.author = author;
        this.content = content;
        this.timeCreated = timeCreated;
        this.newsId = newsId;
    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public Double getTimeCreated() {
        return timeCreated;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTimeCreated(Double timeCreated) {
        this.timeCreated = timeCreated;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }
}
