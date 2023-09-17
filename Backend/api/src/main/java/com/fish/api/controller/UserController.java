package com.fish.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fish.api.entitity.User;
import com.fish.api.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService UserServ;

    @PostMapping("/new")
    public ResponseEntity<User> addUser(@RequestBody User User) {
        if (User.getUserName() == null || User.getUserName().equals("")) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        if (UserServ.doesUsernameExist(User.getUserName())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            this.UserServ.createUser(User);
            return new ResponseEntity<>(UserServ.getByUsername(User.getUserName()), HttpStatus.OK);
        }
    }

    @PutMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody User User) {
        List<User> allUsers = this.UserServ.getAllUsers();
        for (User currentUser : allUsers) {
            if (currentUser.getUserName().equals(User.getUserName())) {
                if (currentUser.getPassword().equals(User.getPassword())) {
                    System.out.println("Login Succesful");
                    return new ResponseEntity<>(currentUser, HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/findall")
    public List<User> findAllUsers() {
        return this.UserServ.getAllUsers();
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable Long id) {
        return this.UserServ.getUserById(id);
    }

    @PutMapping("/modify")
    public User modifyUser(@RequestBody User updatedUser) {
        return this.UserServ.updateUser(updatedUser);
    }

    @DeleteMapping("/delete/{id}")
    public void removeUser(@PathVariable Long id) {
        this.UserServ.deleteUserById(id);
    }

}
