package com.fish.api.entitity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Aquarium {

    @Id
    @Column(name = "name")
    private String name;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aquarium_id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "fishSchool_id", referencedColumnName = "name")
    private List<FishSchool> fishSchools;

    public Aquarium() {
    }

    public Aquarium(String name, User user) {
        this.name = name;
        this.user = user;
        this.fishSchools = new ArrayList<FishSchool>();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<FishSchool> getFishSchools() {
        return this.fishSchools;
    }

    public void setFishSchools(List<FishSchool> fishSchools) {
        this.fishSchools = fishSchools;
    }

}