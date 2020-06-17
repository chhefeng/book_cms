package com.hef.book.service.impl;

import com.hef.book.dao.UserRepository;
import com.hef.book.entity.User;
import com.hef.book.service.UserService;
import com.hef.book.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        return user;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
