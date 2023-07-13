package com.deloitte.ads.mariosy.controller;

import com.deloitte.ads.mariosy.repository.User;
import com.deloitte.ads.mariosy.service.Mariosy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
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

//    @PostMapping()
//    public ResponseEntity addUser(@RequestBody UserDTO userDTO){
//        try {
//            mariosy.createUser(userDTO.getFirstName(),userDTO.getLastName(), userDTO.getLastName());
//            return new ResponseEntity<>(HttpStatus.CREATED);
//        }catch (Exception e){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }

}
