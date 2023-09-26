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

    public void createAquarium(Aquarium Aquarium) {
        this.AquariumRepo.save(Aquarium);
    }

    public List<Aquarium> getAllAquariums() {
        return AquariumRepo.findAll();
    }

    public Aquarium getAquariumById(String id) {

        Optional<Aquarium> Aquarium = AquariumRepo.findById(id);
        if (Aquarium.isPresent()) {
            return Aquarium.get();
        }
        return null;
    }

    public Aquarium updateAquarium(String id, Aquarium updatedAquarium) {
        Aquarium existingAquarium = getAquariumById(id);

        if (existingAquarium != null) {
            existingAquarium.setFishSchools(updatedAquarium.getFishSchools());
            existingAquarium.setUser(updatedAquarium.getUser());
            AquariumRepo.save(existingAquarium);
        }

        return null;
    }

    public boolean deleteAquariumById(String id) {
        if (AquariumRepo.existsById(id)) {
            AquariumRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public String[] getAquariumCompability(String id) {
        Optional<Aquarium> Aquarium = AquariumRepo.findById(id);
        if (Aquarium.isPresent()) {
            return Aquarium.get().aquariumCompatibility();
        }
        return null;
    }

    public String[] getEbaySearches(String id) {
        Optional<Aquarium> Aquarium = AquariumRepo.findById(id);
        if (Aquarium.isPresent()) {
            return Aquarium.get().tankInfo();
        }
        return null;
    }
}
