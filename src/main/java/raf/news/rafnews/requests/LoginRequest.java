package raf.news.rafnews.requests;

public class LoginRequest {
    private String email;
    private String password;

    public LoginRequest(){}

    public LoginRequest(String email, String password) {
        this();
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
