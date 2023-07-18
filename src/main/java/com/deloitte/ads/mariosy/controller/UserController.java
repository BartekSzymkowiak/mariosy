package com.deloitte.ads.mariosy.controller;

import com.deloitte.ads.mariosy.DTO.UserDTO;
import com.deloitte.ads.mariosy.entity.UserEntity;
import com.deloitte.ads.mariosy.service.UserService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public Set<UserDTO> getAllUsers() {
        return userService.getAllUsers().stream().map(UserDTO::mapUserEntityToUserDTO).collect(Collectors.toSet());
    }

    @GetMapping(value = "/users", params = "searchKeyword")
    public Set<UserDTO> searchUsers (@RequestParam("searchKeyword") String searchKeyword) {
        return userService.searchUsers(searchKeyword).stream().map(UserDTO::mapUserEntityToUserDTO).collect(Collectors.toSet());
    }

    @GetMapping(value = "/users", params = "email")
    public ResponseEntity<UserDTO> getUserByEmail(@RequestParam String email)
    {
        Optional<UserEntity> user =  userService.getUserByEmail(email);
        if (user.isPresent()){
            return new ResponseEntity<>(UserDTO.mapUserEntityToUserDTO(user.get()), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        Optional<UserEntity> userEntityOptional = userService.getUserById(id);
        if (userEntityOptional.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            UserDTO userDTO = UserDTO.mapUserEntityToUserDTO(userEntityOptional.get());
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        }
    }

    @PostMapping("/users")
    public ResponseEntity addUser(@RequestBody UserDTO userDTO){
        try {
            userService.createUser(userDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
