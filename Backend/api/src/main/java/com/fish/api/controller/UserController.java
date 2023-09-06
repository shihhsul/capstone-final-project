package com.fish.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    public void addUser(@RequestBody User User) {
        this.UserServ.createUser(User);
    }

    @GetMapping("/findall")
    public List<User> findAllUsers() {
        return this.UserServ.getAllUsers();
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable String id) {
        return this.UserServ.getUserById(id);
    }

    @PutMapping("/modify/{id}")
    public User modifyUser(@PathVariable String id, @RequestBody User updatedUser) {
        return this.UserServ.updateUser(id, updatedUser);
    }

    @DeleteMapping("/delete/{id}")
    public void removeUser(@PathVariable String id) {
        this.UserServ.deleteUserById(id);
    }
}
