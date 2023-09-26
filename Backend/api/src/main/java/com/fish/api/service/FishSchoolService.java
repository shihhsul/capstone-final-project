package com.fish.api.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fish.api.repository.FishSchoolRepository;
import com.fish.api.entitity.FishSchool;

@Service
public class FishSchoolService {

    @Autowired
    private FishSchoolRepository FishSchoolRepo;

    public void createFishSchool(FishSchool FishSchool) {
        this.FishSchoolRepo.save(FishSchool);
    }

    public List<FishSchool> getAllFishSchools() {
        return FishSchoolRepo.findAll();
    }

    public FishSchool getFishSchoolById(int id) {

        Optional<FishSchool> FishSchool = FishSchoolRepo.findById(id);
        if (FishSchool.isPresent()) {
            return FishSchool.get();
        }
        return null;
    }

    public FishSchool updateFishSchool(int id, FishSchool updatedFishSchool) {
        FishSchool existingFishSchool = getFishSchoolById(id);

        if (existingFishSchool != null) {
            existingFishSchool.setAmountFish(updatedFishSchool.getAmountFish());
            existingFishSchool.setAquarium(updatedFishSchool.getAquarium());
            existingFishSchool.setFishType(existingFishSchool.getFishType());
            FishSchoolRepo.save(existingFishSchool);
        }

        return null;
    }

    public FishSchool updateFishSchool(int id, int newAmount) {
        FishSchool existingFishSchool = getFishSchoolById(id);

        if (existingFishSchool != null) {
            existingFishSchool.setAmountFish(newAmount);
            FishSchoolRepo.save(existingFishSchool);
        }

        return null;
    }

    public boolean deleteFishSchoolById(int id) {
        if (FishSchoolRepo.existsById(id)) {
            FishSchoolRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
