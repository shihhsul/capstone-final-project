package com.fish.api.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fish.api.entitity.Fish;
import com.fish.api.repository.FishRepository;

@Service
public class FishService {

    @Autowired
    private FishRepository FishRepo;

    /*
     * Adds a new Fish to the repository (db). The "C" (create) in CRUD
     */
    public void createFish(Fish Fish) {
        this.FishRepo.save(Fish);
    }

    /*
     * Gets all the Fishs in the repo (db). The "R" (read) in CRUD
     */
    public List<Fish> getAllFishs() {
        return FishRepo.findAll();
    }

    /*
     * Gets a specific Fish by its id. The "R" (read) in CRUD
     */
    public Fish getFishById(String id) {

        Optional<Fish> Fish = FishRepo.findById(id);
        if (Fish.isPresent()) {
            return Fish.get();
        }
        return null;
    }

    /*
     * Delete a Fish from the repo. The "D" (delete) in CRUD
     */
    public boolean deleteFishById(String id) {
        if (FishRepo.existsById(id)) {
            FishRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
