package raf.news.rafnews.repositories;

import raf.news.rafnews.entities.User;

import java.util.List;

public interface UserRepository {

    User addUser(User user);

    User updateUser(User user);

    List<User> allUsers();

    User findUser(String email);

    User findUser(int id);

    User deactivateUser(int id);

    User deleteUser(int id);
}
