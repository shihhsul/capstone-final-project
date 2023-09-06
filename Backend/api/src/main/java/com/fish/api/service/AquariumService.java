package com.fish.api.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fish.api.entitity.Aquarium;
import com.fish.api.repository.AquariumRepository;

@Service
public class AquariumService {

    @Autowired
    private AquariumRepository AquariumRepo;

    /*
     * Adds a new Aquarium to the repository (db). The "C" (create) in CRUD
     */
    public void createAquarium(Aquarium Aquarium) {
        this.AquariumRepo.save(Aquarium);
    }

    /*
     * Gets all the Aquariums in the repo (db). The "R" (read) in CRUD
     */
    public List<Aquarium> getAllAquariums() {
        return AquariumRepo.findAll();
    }

    /*
     * Gets a specific Aquarium by its id. The "R" (read) in CRUD
     */
    public Aquarium getAquariumById(String id) {

        Optional<Aquarium> Aquarium = AquariumRepo.findById(id);
        if (Aquarium.isPresent()) {
            return Aquarium.get();
        }
        return null;
    }

    /*
     * Updates an existing Aquarium, found by a specific id. The "U" (update) in
     * CRUD
     */
    public Aquarium updateAquarium(String id, Aquarium updatedAquarium) {
        Aquarium existingAquarium = getAquariumById(id);

        if (existingAquarium != null) {
            existingAquarium.setFishSchools(updatedAquarium.getFishSchools());
            existingAquarium.setUser(updatedAquarium.getUser());
            AquariumRepo.save(existingAquarium);
        }

        return null; // Return null if the Aquarium with the given ID is not found
    }

    /*
     * Delete a Aquarium from the repo. The "D" (delete) in CRUD
     */
    public boolean deleteAquariumById(String id) {
        if (AquariumRepo.existsById(id)) {
            AquariumRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
