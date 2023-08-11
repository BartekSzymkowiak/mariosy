package com.deloitte.ads.mariosy.controller;

import com.deloitte.ads.mariosy.DTO.UserDTO;
import com.deloitte.ads.mariosy.security.JwtConverter;
import com.deloitte.ads.mariosy.service.IllegalUserFieldValueException;
import com.deloitte.ads.mariosy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    @PreAuthorize("hasRole('client_admin')")
    public List<UserDTO> getAllUsers(JwtAuthenticationToken token) {
        return userService.getAllUsersDTOs();
    }

    @GetMapping(value = "/users", params = "searchKeyword")
    @PreAuthorize("hasAnyRole('client_user','client_admin')")
    public List<UserDTO> searchUsers(@RequestParam("searchKeyword") String searchKeyword) {
        return userService.searchUsersDTOs(searchKeyword);
    }

    @PostMapping("/users")
    @PreAuthorize("hasAnyRole('client_user','client_admin')")
    public ResponseEntity<UserDTO> addUser(JwtAuthenticationToken token) {
        try {
            String username = JwtConverter.getUsernameFromJwt(token.getToken());
            UserDTO responseUserDTO = userService.createUser(UUID.fromString(token.getName()), username);
            return new ResponseEntity<>(responseUserDTO, HttpStatus.CREATED);
        } catch (IllegalUserFieldValueException e) {
            UserDTO emptyUserDTO = new UserDTO();
            emptyUserDTO.setAdditionalMessage(e.getMessage());
            return new ResponseEntity<>(emptyUserDTO, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/users/{userExternalId}")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity deleteUser(@PathVariable UUID userExternalId) {
        userService.deleteUser(userExternalId);
        return new ResponseEntity(HttpStatus.OK);
    }

}
