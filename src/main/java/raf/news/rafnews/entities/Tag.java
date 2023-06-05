package raf.news.rafnews.entities;

public class Tag {

    private int id;

    private String tag;

    public Tag() {}

    public Tag(int id, String tag) {
        this();
        this.id = id;
        this.tag = tag;
    }

    public int getId() {
        return id;
    }

    public String getTag() {
        return tag;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
