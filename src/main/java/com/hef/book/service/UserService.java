package com.hef.book.service;

import com.hef.book.entity.User;

public interface UserService {
    User checkUser(String username, String password);
}
