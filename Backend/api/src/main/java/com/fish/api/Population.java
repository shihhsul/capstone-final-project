package com.fish.api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.fish.api.repository.*;
import com.fish.api.entitity.Aquarium;
import com.fish.api.entitity.Fish;
import com.fish.api.entitity.FishSchool;
import com.fish.api.entitity.User;

@Component
public class Population implements CommandLineRunner {
        @Autowired
        private UserRepository userRepository;
        @Autowired
        private AquariumRepository aquariumRepository;
        @Autowired
        private FishSchoolRepository fishSchoolRepository;
        @Autowired
        private FishRepository fishTypeRepository;

        @Override
        public void run(String... args) throws Exception {
                List<Fish> fishList = new ArrayList<>();
                try (BufferedReader reader = new BufferedReader(
                                new FileReader("Backend/api/src/main/java/com/fish/api/fish_data.txt"))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                                String[] fields = line.split(",");

                                Fish fish = new Fish();
                                int fieldIndex = 0;
                                fish.setCommonName(fields[fieldIndex++]);
                                fish.setScientificName(fields[fieldIndex++]);
                                fish.setSpeciesGroup(fields[fieldIndex++]);
                                fish.setCareLevel(fields[fieldIndex++]);
                                fish.setAverageSize(fields[fieldIndex++]);
                                fish.setLifespan(fields[fieldIndex++]);
                                fish.setPhLow(fields[fieldIndex++]);
                                fish.setPhHigh(fields[fieldIndex++]);
                                fish.setTempLow(fields[fieldIndex++]);
                                fish.setTempHigh(fields[fieldIndex++]);
                                fish.setHardLow(fields[fieldIndex++]);
                                fish.setHardHigh(fields[fieldIndex++]);
                                fish.setSwimmingLevel(fields[fieldIndex++]);
                                fish.setIsAggressiveSelf(fields[fieldIndex++]);
                                fish.setIsAggressiveOther(fields[fieldIndex++]);
                                fish.setIdealNumber(fields[fieldIndex++]);
                                fish.setLivePlants(fields[fieldIndex++]);
                                fish.setFoodType(fields[fieldIndex++]);
                                fish.setFoodOptions(fields[fieldIndex++]);
                                fish.setSubstrate(fields[fieldIndex++]);
                                fish.setLight(fields[fieldIndex++]);
                                fish.setCurrent(fields[fieldIndex++]);
                                fish.setDecorations(fields[fieldIndex++]);
                                fish.setMinimumTankSize(fields[fieldIndex++]);
                                fish.setPicUrl(fields[fieldIndex]);
                                fishList.add(fish);
                        }
                } catch (IOException e) {
                        e.printStackTrace();
                }

                fishTypeRepository.saveAll(fishList);
        }
}
