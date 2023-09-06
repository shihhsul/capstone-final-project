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

import com.fish.api.entitity.Fish;
import com.fish.api.service.FishService;

@RestController
@RequestMapping("/fish")
public class FishController {

    @Autowired
    FishService FishServ;

    @PostMapping("/new")
    public void addFish(@RequestBody Fish Fish) {
        this.FishServ.createFish(Fish);
    }

    @GetMapping("/findall")
    public List<Fish> findAllFishs() {
        return this.FishServ.getAllFishs();
    }

    @GetMapping("/{id}")
    public Fish findFishById(@PathVariable String id) {
        return this.FishServ.getFishById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void removeFish(@PathVariable String id) {
        this.FishServ.deleteFishById(id);
    }
}
