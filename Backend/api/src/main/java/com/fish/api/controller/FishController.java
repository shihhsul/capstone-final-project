package com.fish.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
}
