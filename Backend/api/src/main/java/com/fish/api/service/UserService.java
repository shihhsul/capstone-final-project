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

    /*
     * Adds a new User to the repository (db). The "C" (create) in CRUD
     */
    public void createUser(User user) {
        this.UserRepo.save(user);
    }

    /*
     * Gets all the Users in the repo (db). The "R" (read) in CRUD
     */
    public List<User> getAllUsers() {
        return UserRepo.findAll();
    }

    /*
     * Gets a specific User by its id. The "R" (read) in CRUD
     */
    public User getUserById(Long id) {

        Optional<User> user = UserRepo.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }

    /*
     * Updates an existing User, found by a specific id. The "U" (update) in
     * CRUD
     */
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

        return null; // Return null if the user with the given ID is not found
    }

    /*
     * Delete a User from the repo. The "D" (delete) in CRUD
     */
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
