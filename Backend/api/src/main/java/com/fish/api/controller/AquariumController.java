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

import com.fish.api.entitity.Aquarium;
import com.fish.api.service.AquariumService;

@RestController
@RequestMapping("/aquariums")
public class AquariumController {

    @Autowired
    AquariumService AquariumServ;

    @PostMapping("/new")
    public void addAquarium(@RequestBody Aquarium Aquarium) {
        this.AquariumServ.createAquarium(Aquarium);
    }

    @GetMapping("/findall")
    public List<Aquarium> findAllAquariums() {
        return this.AquariumServ.getAllAquariums();
    }

    @GetMapping("/{id}")
    public Aquarium findAquariumById(@PathVariable String id) {
        return this.AquariumServ.getAquariumById(id);
    }

    @PutMapping("/modify/{id}")
    public Aquarium modifyAquarium(@PathVariable String id, @RequestBody Aquarium updatedAquarium) {
        return this.AquariumServ.updateAquarium(id, updatedAquarium);
    }

    @DeleteMapping("/delete/{id}")
    public void removeAquarium(@PathVariable String id) {
        this.AquariumServ.deleteAquariumById(id);
    }
}
