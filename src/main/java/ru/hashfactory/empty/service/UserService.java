package ru.hashfactory.empty.service;

import ru.hashfactory.empty.domain.User;

public interface UserService {
    public User findUserByEmail(String email);
    public void saveUser(User user);
}