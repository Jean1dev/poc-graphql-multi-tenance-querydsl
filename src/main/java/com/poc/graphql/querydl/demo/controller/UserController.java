package com.poc.graphql.querydl.demo.controller;

import com.poc.graphql.querydl.demo.model.User;
import com.poc.graphql.querydl.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("user")
@Transactional(propagation = Propagation.SUPPORTS)
public class UserController {

    @Autowired
    private UserService service;

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
}
