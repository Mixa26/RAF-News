package raf.news.rafnews.entities;

public class Type {

    private int id;

    private String userType;

    public Type(){};

    public Type(int id, String userType) {
        this();
        this.id = id;
        this.userType = userType;
    }

    public int getId() {
        return id;
    }

    public String getUserType() {
        return userType;
    }
}
