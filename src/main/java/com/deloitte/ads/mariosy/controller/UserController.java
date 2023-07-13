package com.deloitte.ads.mariosy.controller;

import com.deloitte.ads.mariosy.DTO.UserDTO;
import com.deloitte.ads.mariosy.entity.UserEntity;
import com.deloitte.ads.mariosy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users/")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping()
    public Set<UserDTO> getAllUsers() {
        return userService.getUsers().stream().map(UserDTO::mapUserEntityToUserDTO).collect(Collectors.toSet());
    }

    @PostMapping()
    public ResponseEntity addUser(@RequestBody UserDTO userDTO){
        try {
            userService.createUser(userDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<UserDTO> getUserByEmail(@RequestParam String email )
    {
        Optional<UserEntity> user =  userService.findUserByEmail(email);
        if (user.isPresent()){
            return new ResponseEntity<>(UserDTO.mapUserEntityToUserDTO(user.get()), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }

}
