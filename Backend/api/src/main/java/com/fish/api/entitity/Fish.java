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
    private String scientificName;
    @Column(name = "speciesGroup")
    private String speciesGroup;
    @Column(name = "careLevel")
    private char careLevel;
    @Column(name = "averageSize")
    private int averageSize;
    @Column(name = "lifespan")
    private double lifespan;

    @Column(name = "phLow")
    private double phLow;
    @Column(name = "phHigh")
    private double phHigh;

    @Column(name = "tempLow")
    private double tempLow;
    @Column(name = "tempHigh")
    private double tempHigh;

    @Column(name = "hardLow")
    private double hardLow;
    @Column(name = "hardHigh")
    private double hardHigh;

    @Column(name = "swimmingLevel")
    private char swimmingLevel;
    @Column(name = "ownAggressiveness")
    private boolean isAggressiveSelf;
    @Column(name = "otherAggressiveness")
    private boolean isAggressiveOther;
    @Column(name = "idealNumber")
    private int idealNumber;
    @Column(name = "livePlants")
    private boolean livePlants;
    @Column(name = "foodType")
    private String foodType;
    @Column(name = "foodOptions")
    private String foodOptions;
    @Column(name = "substrate")
    private String substrate;
    @Column(name = "light")
    private char light;
    @Column(name = "current")
    private char current;
    @Column(name = "decorations")
    private char decorations;
    @Column(name = "tankSize")
    private int minimumTankSize;

    public Fish() {
    }

    public Fish(String commonName, String scientificName, String speciesGroup, char careLevel, int averageSize,
            double lifespan, double phLow, double phHigh, double tempLow, double tempHigh, double hardLow,
            double hardHigh, char swimmingLevel, boolean isAggressiveSelf, boolean isAggressiveOther, int idealNumber,
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

    public double getLifespan() {
        return this.lifespan;
    }

    public void setLifespan(double lifespan) {
        this.lifespan = lifespan;
    }

    public double getPhLow() {
        return this.phLow;
    }

    public void setPhLow(double phLow) {
        this.phLow = phLow;
    }

    public double getPhHigh() {
        return this.phHigh;
    }

    public void setPhHigh(double phHigh) {
        this.phHigh = phHigh;
    }

    public double getTempLow() {
        return this.tempLow;
    }

    public void setTempLow(double tempLow) {
        this.tempLow = tempLow;
    }

    public double getTempHigh() {
        return this.tempHigh;
    }

    public void setTempHigh(double tempHigh) {
        this.tempHigh = tempHigh;
    }

    public double getHardLow() {
        return this.hardLow;
    }

    public void setHardLow(double hardLow) {
        this.hardLow = hardLow;
    }

    public double getHardHigh() {
        return this.hardHigh;
    }

    public void setHardHigh(double hardHigh) {
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