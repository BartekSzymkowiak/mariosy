package com.deloitte.ads.mariosy.controller;

import com.deloitte.ads.mariosy.repository.User;
import com.deloitte.ads.mariosy.service.Mariosy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/users/")
public class UserController {
    @Autowired
    private Mariosy mariosy;

    @GetMapping()
    public Set<User> getAllUsers() {
        return mariosy.getUsers();
    }

}
