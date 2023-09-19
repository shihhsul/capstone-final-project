package com.fish.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fish.api.entitity.Fish;
import com.fish.api.entitity.FishSchool;
import com.fish.api.service.AquariumService;
import com.fish.api.service.FishSchoolService;
import com.fish.api.service.FishService;

@RestController
@RequestMapping("/fishschools")
public class FishSchoolController {

    @Autowired
    FishSchoolService FishSchoolServ;

    @Autowired
    FishService FishServ;

    @Autowired
    AquariumService AquariumServ;

    @PostMapping("/new")
    public void addFishSchool(@RequestBody FishSchool FishSchool) {
        this.FishSchoolServ.createFishSchool(FishSchool);
    }

    @PostMapping("/new/{aquarium}")
    public ResponseEntity<FishSchool> addFishSchoolByUser(@PathVariable String aquarium, @RequestBody String fishName) {
        Fish fishtype = FishServ.getFishById(fishName);

        if (AquariumServ.getAquariumById(aquarium).hasFishType(fishName)) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } else {
            FishSchool template = new FishSchool(0, AquariumServ.getAquariumById(aquarium), fishtype, 1);
            this.FishSchoolServ.createFishSchool(template);
            return new ResponseEntity<>(template, HttpStatus.OK);
        }
    }

    @PutMapping("/modify/{id}")
    public ResponseEntity<FishSchool> modifyFishSchool(@PathVariable int id, @RequestBody int fishAmount) {
        FishSchoolServ.updateFishSchool(id, fishAmount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/findall")
    public List<FishSchool> findAllFishSchools() {
        return this.FishSchoolServ.getAllFishSchools();
    }

    @GetMapping("/{id}")
    public FishSchool findFishSchoolById(@PathVariable int id) {
        return this.FishSchoolServ.getFishSchoolById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void removeFishSchool(@PathVariable int id) {
        this.FishSchoolServ.deleteFishSchoolById(id);
    }
}
