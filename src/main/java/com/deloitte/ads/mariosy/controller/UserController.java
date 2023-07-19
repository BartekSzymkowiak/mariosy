package com.deloitte.ads.mariosy.controller;

import com.deloitte.ads.mariosy.DTO.UserDTO;
import com.deloitte.ads.mariosy.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

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
        return userService.getAllUsersDTOs();
    }

    @GetMapping(value = "/users", params = "searchKeyword")
    public Set<UserDTO> searchUsers (@RequestParam("searchKeyword") String searchKeyword) {
        return userService.searchUsersDTOs(searchKeyword);
    }

    @GetMapping(value = "/users", params = "email")
    public ResponseEntity<UserDTO> getUserByEmail(@RequestParam String email)
    {
        Optional<UserDTO> userDTOOptional =  userService.getUserDTOByEmail(email);
        if (userDTOOptional.isPresent()){
            return new ResponseEntity<>(userDTOOptional.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/users/{userExternalId}")
    public ResponseEntity<UserDTO> getUserByExternalId(@PathVariable UUID userExternalId) {
        Optional<UserDTO> userDTOOptional = userService.getUserDTOByExternalId(userExternalId);
        if (userDTOOptional.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(userDTOOptional.get(), HttpStatus.OK);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO){
        try {
             UserDTO responseUserDTO = userService.createUser(userDTO);
            return new ResponseEntity<UserDTO>(responseUserDTO, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
