package raf.news.rafnews.entities;

public class News_Tag_Table {

    private int newsId;

    private int tagId;

    public News_Tag_Table() {}

    public News_Tag_Table(int newsId, int tagId) {
        this.newsId = newsId;
        this.tagId = tagId;
    }

    public int getNewsId() {
        return newsId;
    }

    public int getTagId() {
        return tagId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }
}
