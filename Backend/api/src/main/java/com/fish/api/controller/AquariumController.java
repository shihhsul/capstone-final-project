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

import com.fish.api.entitity.Aquarium;
import com.fish.api.entitity.User;
import com.fish.api.service.AquariumService;
import com.fish.api.service.UserService;

@RestController
@RequestMapping("/aquariums")
public class AquariumController {

    @Autowired
    AquariumService AquariumServ;

    @Autowired
    UserService UserServ;

    @PostMapping("/new/{username}")
    public ResponseEntity addAquarium(@PathVariable String username, @RequestBody Aquarium Aquarium) {

        User user = UserServ.getByUsername(username);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        user.getAquariums().add(Aquarium);
        UserServ.updateUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/findall")
    public List<Aquarium> findAllAquariums() {
        return this.AquariumServ.getAllAquariums();
    }

    @GetMapping("findby/{id}")
    public Aquarium findAquariumById(@PathVariable String id) {
        return this.AquariumServ.getAquariumById(id);
    }

    @GetMapping("compatibility/{id}")
    public String[] findCompabilityById(@PathVariable String id) {
        return this.AquariumServ.getAquariumCompability(id);
    }

    @GetMapping("tankinfo/{id}")
    public String[] findTankInfoById(@PathVariable String id) {
        return this.AquariumServ.getEbaySearches(id);
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
