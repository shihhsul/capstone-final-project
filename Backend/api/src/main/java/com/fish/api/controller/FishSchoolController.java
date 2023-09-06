package com.fish.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fish.api.entitity.FishSchool;
import com.fish.api.service.FishSchoolService;

@RestController
@RequestMapping("/fishschools")
public class FishSchoolController {

    @Autowired
    FishSchoolService FishSchoolServ;

    @PostMapping("/new")
    public void addFishSchool(@RequestBody FishSchool FishSchool) {
        this.FishSchoolServ.createFishSchool(FishSchool);
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
