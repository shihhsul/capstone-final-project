package com.fish.api.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fish.api.repository.FishSchoolRepository;
import com.fish.api.entitity.FishSchool;
import com.fish.api.entitity.FishSchool;

@Service
public class FishSchoolService {

    @Autowired
    private FishSchoolRepository FishSchoolRepo;

    /*
     * Adds a new FishSchool to the repository (db). The "C" (create) in CRUD
     */
    public void createFishSchool(FishSchool FishSchool) {
        this.FishSchoolRepo.save(FishSchool);
    }

    /*
     * Gets all the FishSchools in the repo (db). The "R" (read) in CRUD
     */
    public List<FishSchool> getAllFishSchools() {
        return FishSchoolRepo.findAll();
    }

    /*
     * Gets a specific FishSchool by its id. The "R" (read) in CRUD
     */
    public FishSchool getFishSchoolById(int id) {

        Optional<FishSchool> FishSchool = FishSchoolRepo.findById(id);
        if (FishSchool.isPresent()) {
            return FishSchool.get();
        }
        return null;
    }

    /*
     * Updates an existing FishSchool, found by a specific id. The "U" (update) in
     * CRUD
     */
    public FishSchool updateFishSchool(int id, FishSchool updatedFishSchool) {
        FishSchool existingFishSchool = getFishSchoolById(id);

        if (existingFishSchool != null) {
            existingFishSchool.setAmountFish(updatedFishSchool.getAmountFish());
            existingFishSchool.setAquarium(updatedFishSchool.getAquarium());
            existingFishSchool.setFishType(existingFishSchool.getFishType());
            FishSchoolRepo.save(existingFishSchool);
        }

        return null; // Return null if the FishSchool with the given ID is not found
    }

    /*
     * Delete a FishSchool from the repo. The "D" (delete) in CRUD
     */
    public boolean deleteFishSchoolById(int id) {
        if (FishSchoolRepo.existsById(id)) {
            FishSchoolRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
