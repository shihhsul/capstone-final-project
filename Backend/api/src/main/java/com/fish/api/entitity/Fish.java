package com.fish.api.entitity;

import java.util.List;

import org.hibernate.mapping.Set;

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
public class Fish {

    // a char type means it can be low mid or high, 'l','m','h' respectively
    @Id
    @Column(name = "commonName")
    private String commonName;
    @Column(name = "scientificName")
    private String scientificName = "";
    @Column(name = "speciesGroup")
    private String speciesGroup = "";
    @Column(name = "careLevel")
    private char careLevel = 'n';
    @Column(name = "averageSize")
    private int averageSize;
    @Column(name = "lifespan")
    private Double lifespan;

    @Column(name = "phLow")
    private Double phLow;
    @Column(name = "phHigh")
    private Double phHigh;

    @Column(name = "tempLow")
    private Double tempLow;
    @Column(name = "tempHigh")
    private Double tempHigh;

    @Column(name = "hardLow")
    private Double hardLow;
    @Column(name = "hardHigh")
    private Double hardHigh;

    @Column(name = "swimmingLevel")
    private char swimmingLevel;
    @Column(name = "ownAggressiveness")
    private boolean isAggressiveSelf = false;
    @Column(name = "otherAggressiveness")
    private boolean isAggressiveOther = false;
    @Column(name = "idealNumber")
    private int idealNumber;
    @Column(name = "livePlants")
    private boolean livePlants = false;
    @Column(name = "foodType")
    private String foodType = "";
    @Column(name = "foodOptions")
    private String foodOptions = "";
    @Column(name = "substrate")
    private String substrate = "";
    @Column(name = "light")
    private char light = 'n';
    @Column(name = "current")
    private char current = 'n';
    @Column(name = "decorations")
    private char decorations = 'n';
    @Column(name = "tankSize")
    private int minimumTankSize;

    public Fish() {
    }

    public Fish(String commonName, String scientificName, String speciesGroup, char careLevel, int averageSize,
            Double lifespan, Double phLow, Double phHigh, Double tempLow, Double tempHigh, Double hardLow,
            Double hardHigh, char swimmingLevel, boolean isAggressiveSelf, boolean isAggressiveOther, int idealNumber,
            boolean livePlants, String foodType, String foodOptions, String substrate, char light, char current,
            char decorations, int minimumTankSize) {
        this.commonName = commonName;
        this.scientificName = scientificName;
        this.speciesGroup = speciesGroup;
        this.careLevel = careLevel;
        this.averageSize = averageSize;
        this.lifespan = lifespan;
        this.phLow = phLow;
        this.phHigh = phHigh;
        this.tempLow = tempLow;
        this.tempHigh = tempHigh;
        this.hardLow = hardLow;
        this.hardHigh = hardHigh;
        this.swimmingLevel = swimmingLevel;
        this.isAggressiveSelf = isAggressiveSelf;
        this.isAggressiveOther = isAggressiveOther;
        this.idealNumber = idealNumber;
        this.livePlants = livePlants;
        this.foodType = foodType;
        this.foodOptions = foodOptions;
        this.substrate = substrate;
        this.light = light;
        this.current = current;
        this.decorations = decorations;
        this.minimumTankSize = minimumTankSize;
    }

    public String getCommonName() {
        return this.commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getScientificName() {
        return this.scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getSpeciesGroup() {
        return this.speciesGroup;
    }

    public void setSpeciesGroup(String speciesGroup) {
        this.speciesGroup = speciesGroup;
    }

    public char getCareLevel() {
        return this.careLevel;
    }

    public void setCareLevel(char careLevel) {
        this.careLevel = careLevel;
    }

    public int getAverageSize() {
        return this.averageSize;
    }

    public void setAverageSize(int averageSize) {
        this.averageSize = averageSize;
    }

    public Double getLifespan() {
        return this.lifespan;
    }

    public void setLifespan(Double lifespan) {
        this.lifespan = lifespan;
    }

    public Double getPhLow() {
        return this.phLow;
    }

    public void setPhLow(Double phLow) {
        this.phLow = phLow;
    }

    public Double getPhHigh() {
        return this.phHigh;
    }

    public void setPhHigh(Double phHigh) {
        this.phHigh = phHigh;
    }

    public Double getTempLow() {
        return this.tempLow;
    }

    public void setTempLow(Double tempLow) {
        this.tempLow = tempLow;
    }

    public Double getTempHigh() {
        return this.tempHigh;
    }

    public void setTempHigh(Double tempHigh) {
        this.tempHigh = tempHigh;
    }

    public Double getHardLow() {
        return this.hardLow;
    }

    public void setHardLow(Double hardLow) {
        this.hardLow = hardLow;
    }

    public Double getHardHigh() {
        return this.hardHigh;
    }

    public void setHardHigh(Double hardHigh) {
        this.hardHigh = hardHigh;
    }

    public char getSwimmingLevel() {
        return this.swimmingLevel;
    }

    public void setSwimmingLevel(char swimmingLevel) {
        this.swimmingLevel = swimmingLevel;
    }

    public boolean isIsAggressiveSelf() {
        return this.isAggressiveSelf;
    }

    public boolean getIsAggressiveSelf() {
        return this.isAggressiveSelf;
    }

    public void setIsAggressiveSelf(boolean isAggressiveSelf) {
        this.isAggressiveSelf = isAggressiveSelf;
    }

    public boolean isIsAggressiveOther() {
        return this.isAggressiveOther;
    }

    public boolean getIsAggressiveOther() {
        return this.isAggressiveOther;
    }

    public void setIsAggressiveOther(boolean isAggressiveOther) {
        this.isAggressiveOther = isAggressiveOther;
    }

    public int getIdealNumber() {
        return this.idealNumber;
    }

    public void setIdealNumber(int idealNumber) {
        this.idealNumber = idealNumber;
    }

    public boolean isLivePlants() {
        return this.livePlants;
    }

    public boolean getLivePlants() {
        return this.livePlants;
    }

    public void setLivePlants(boolean livePlants) {
        this.livePlants = livePlants;
    }

    public String getFoodType() {
        return this.foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public String getFoodOptions() {
        return this.foodOptions;
    }

    public void setFoodOptions(String foodOptions) {
        this.foodOptions = foodOptions;
    }

    public String getSubstrate() {
        return this.substrate;
    }

    public void setSubstrate(String substrate) {
        this.substrate = substrate;
    }

    public char getLight() {
        return this.light;
    }

    public void setLight(char light) {
        this.light = light;
    }

    public char getCurrent() {
        return this.current;
    }

    public void setCurrent(char current) {
        this.current = current;
    }

    public char getDecorations() {
        return this.decorations;
    }

    public void setDecorations(char decorations) {
        this.decorations = decorations;
    }

    public int getMinimumTankSize() {
        return this.minimumTankSize;
    }

    public void setMinimumTankSize(int minimumTankSize) {
        this.minimumTankSize = minimumTankSize;
    }

}