package com.fish.api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
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
        private static class FishFileHandler {
                private static final String FILE_PATH = "Backend/api/src/main/java/com/fish/api/fish_data.txt";

                public static void saveFishToFile(Fish fish) {
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
                                writer.write(fish.toString());
                                writer.newLine();
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                }
        }

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
                Scanner scanner = new Scanner(System.in);
                String exit = "";

                System.out.println("Enter exit");
                exit = scanner.nextLine();

                while (!exit.equals("exit")) {
                        Fish fish = new Fish();

                        System.out.print("Enter common name: ");
                        fish.setCommonName(scanner.nextLine());

                        System.out.print("Enter scientific name: ");
                        fish.setScientificName(scanner.nextLine());

                        System.out.print("Enter species group: ");
                        fish.setSpeciesGroup(scanner.nextLine());

                        System.out.print("Enter care level: ");
                        fish.setCareLevel(scanner.nextLine());

                        System.out.print("Enter average size: ");
                        fish.setAverageSize(scanner.nextLine());

                        System.out.print("Enter lifespan: ");
                        fish.setLifespan(scanner.nextLine());

                        System.out.print("Enter phLow: ");
                        fish.setPhLow(scanner.nextLine());

                        System.out.print("Enter phHigh: ");
                        fish.setPhHigh(scanner.nextLine());

                        System.out.print("Enter tempLow: ");
                        fish.setTempLow(scanner.nextLine());

                        System.out.print("Enter tempHigh: ");
                        fish.setTempHigh(scanner.nextLine());

                        System.out.print("Enter hardLow: ");
                        fish.setHardLow(scanner.nextLine());

                        System.out.print("Enter hardHigh: ");
                        fish.setHardHigh(scanner.nextLine());

                        System.out.print("Enter swimming level: ");
                        fish.setSwimmingLevel(scanner.nextLine());

                        System.out.print("Is the fish aggressive towards itself? (true/false): ");
                        fish.setIsAggressiveSelf(scanner.nextLine());

                        System.out.print("Is the fish aggressive towards others? (true/false): ");
                        fish.setIsAggressiveOther(scanner.nextLine());

                        System.out.print("Enter ideal number: ");
                        fish.setIdealNumber(scanner.nextLine());

                        System.out.print("Does the fish like live plants? (true/false): ");
                        fish.setLivePlants(scanner.nextLine());

                        System.out.print("Enter food type: ");
                        fish.setFoodType(scanner.nextLine());

                        System.out.print("Enter food options: ");
                        fish.setFoodOptions(scanner.nextLine());

                        System.out.print("Enter substrate: ");
                        fish.setSubstrate(scanner.nextLine());

                        System.out.print("Enter light: ");
                        fish.setLight(scanner.nextLine());

                        System.out.print("Enter current: ");
                        fish.setCurrent(scanner.nextLine());

                        System.out.print("Enter decorations: ");
                        fish.setDecorations(scanner.nextLine());

                        System.out.print("Enter minimum tank size: ");
                        fish.setMinimumTankSize(scanner.nextLine());

                        System.out.print("Enter picture URL: ");
                        fish.setPicUrl(scanner.nextLine());

                        FishFileHandler.saveFishToFile(fish);

                        System.out.print("Enter exit");
                        exit = scanner.nextLine();
                }

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
