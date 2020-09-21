package com.poc.graphql.querydl.demo.service;

import com.poc.graphql.querydl.demo.model.User;
import com.poc.graphql.querydl.demo.repository.BasicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private BasicRepository repository;

    public void save(User user) {
        repository.setClazz(User.class);
        repository.save(user);
    }
}
