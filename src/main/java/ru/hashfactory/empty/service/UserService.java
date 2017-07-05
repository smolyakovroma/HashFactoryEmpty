package ru.hashfactory.empty.service;

import ru.hashfactory.empty.domain.User;

import java.util.List;

public interface UserService {
    public User findUserByEmail(String email);
    public void saveUser(User user);
    public List<User> findAll();
}