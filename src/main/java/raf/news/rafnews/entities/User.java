package raf.news.rafnews.entities;

import javax.validation.constraints.NotNull;

public class User {

    private int id;

    @NotNull(message = "Email must not be empty!")
    private String email;

    @NotNull(message = "Name must not be empty!")

    private String name;

    @NotNull(message = "Surname must not be empty!")

    private String surname;

    @NotNull(message = "Type id must be present!")

    private int typeId;

    @NotNull(message = "Status id must be present!")

    private int statusId;

    @NotNull(message = "Password must not be empty!")

    private String password;

    public User(){}

    public User(int id, String email, String name, String surname, int typeId, int statusId, String password) {
        this();
        this.id = id;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.typeId = typeId;
        this.statusId = statusId;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getTypeId() {
        return typeId;
    }

    public int getStatusId() {
        return statusId;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
