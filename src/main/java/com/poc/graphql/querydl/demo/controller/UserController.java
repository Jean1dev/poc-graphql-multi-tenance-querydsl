package com.poc.graphql.querydl.demo.controller;

import com.poc.graphql.querydl.demo.model.QUser;
import com.poc.graphql.querydl.demo.model.User;
import com.poc.graphql.querydl.demo.repository.BasicRepository;
import com.poc.graphql.querydl.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller()
@RequestMapping("user")
@Transactional(propagation = Propagation.SUPPORTS)
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private BasicRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity save() {
        User user = new User();
        user.setAge(2);
        user.setEmail("email");
        user.setFirstName("nome");

        service.save(user);
        System.out.println("ok");
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity find() {
        repository.setClazz(User.class);
        List<User> all = repository.findAll();
        QUser q = QUser.user;
        return ResponseEntity.ok(repository.findAll(User.class, q.email.like("email")));
    }
}
