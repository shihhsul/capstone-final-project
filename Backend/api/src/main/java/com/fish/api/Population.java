package com.fish.api;

import java.util.ArrayList;

import org.hibernate.mapping.List;
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

                // // Create a FishType for the specific fish
                // Fish fishType = new Fish();
                // fishType.setCommonName("noah");
                // // Set other fish type attributes as needed
                // fishTypeRepository.save(fishType);

                // User user = new User("username", "password", "Fname Lname", "email");
                // userRepository.save(user);

                // Aquarium aquarium = new Aquarium("Aquarium", user);
                // aquariumRepository.save(aquarium);

                // // Create a FishSchool associated with the FishType
                // FishSchool fish = new FishSchool(0, aquarium, fishType, 0);
                // fishSchoolRepository.save(fish);

                // // Add the fish school to the aquarium
                // aquarium.getFishSchools().add(fish);
                // aquariumRepository.save(aquarium);

                // // Add the aquarium to the user
                // user.getAquariums().add(aquarium);
                // userRepository.save(user);

                // ArrayList<Fish> fishtypeList = new ArrayList<Fish>();
                // Fish fish5 = new Fish();
                // fish5.setCommonName("Noah");
                // fishTypeRepository.save(fish5);
        }
}
