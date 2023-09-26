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

    public void createFish(Fish Fish) {
        this.FishRepo.save(Fish);
    }

    public List<Fish> getAllFishs() {
        return FishRepo.findAll();
    }

    public Fish getFishById(String id) {

        Optional<Fish> Fish = FishRepo.findById(id);
        if (Fish.isPresent()) {
            return Fish.get();
        }
        return null;
    }

    public boolean deleteFishById(String id) {
        if (FishRepo.existsById(id)) {
            FishRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
