package com.fish.api.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fish.api.entitity.User;
import com.fish.api.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository UserRepo;

    public void createUser(User user) {
        this.UserRepo.save(user);
    }

    public List<User> getAllUsers() {
        return UserRepo.findAll();
    }

    public User getUserById(Long id) {

        Optional<User> user = UserRepo.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }

    public User updateUser(User updateduser) {
        User existinguser = getUserById(updateduser.getId());

        if (existinguser != null) {
            existinguser.setAquariums(updateduser.getAquariums());
            existinguser.setEmail(updateduser.getEmail());
            existinguser.setFullName(updateduser.getFullName());
            existinguser.setPassword(updateduser.getPassword());
            existinguser.setUserName(updateduser.getUserName());
            UserRepo.save(existinguser);
        }

        return null;
    }

    public boolean deleteUserById(Long id) {
        if (UserRepo.existsById(id)) {
            UserRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean doesUsernameExist(String username) {
        for (User user : UserRepo.findAll()) {
            if (user.getUserName().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public User getByUsername(String username) {
        for (User user : UserRepo.findAll()) {
            if (user.getUserName().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
