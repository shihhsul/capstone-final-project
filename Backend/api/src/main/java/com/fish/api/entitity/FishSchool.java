package com.fish.api.entitity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class FishSchool {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fishSchool_id")
    private Aquarium aquarium;

    @ManyToOne
    @JoinColumn(name = "fish_type_id")
    private Fish fishType;

    @Column(name = "amountFish")
    private int amountFish;

    public FishSchool() {
    }

    public FishSchool(int id, Aquarium aquarium, Fish fishType, int amountFish) {
        this.id = id;
        this.aquarium = aquarium;
        this.fishType = fishType;
        this.amountFish = amountFish;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Aquarium getAquarium() {
        return this.aquarium;
    }

    public void setAquarium(Aquarium aquarium) {
        this.aquarium = aquarium;
    }

    public Fish getFishType() {
        return this.fishType;
    }

    public void setFishType(Fish fishType) {
        this.fishType = fishType;
    }

    public int getAmountFish() {
        return this.amountFish;
    }

    public void setAmountFish(int amountFish) {
        this.amountFish = amountFish;
    }
}