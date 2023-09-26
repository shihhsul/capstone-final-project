package com.fish.api.entitity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Fish {

    @Id
    @Column(name = "commonName")
    private String commonName;
    @Column(name = "scientificName")
    private String scientificName = "";
    @Column(name = "speciesGroup")
    private String speciesGroup = "";
    @Column(name = "careLevel")
    private String careLevel = "";
    @Column(name = "averageSize")
    private String averageSize;
    @Column(name = "lifespan")
    private String lifespan;

    @Column(name = "phLow")
    private String phLow;
    @Column(name = "phHigh")
    private String phHigh;

    @Column(name = "tempLow")
    private String tempLow;
    @Column(name = "tempHigh")
    private String tempHigh;

    @Column(name = "hardLow")
    private String hardLow;
    @Column(name = "hardHigh")
    private String hardHigh;

    @Column(name = "swimmingLevel")
    private String swimmingLevel;
    @Column(name = "ownAggressiveness")
    private String isAggressiveSelf = "false";
    @Column(name = "otherAggressiveness")
    private String isAggressiveOther = "false";
    @Column(name = "idealNumber")
    private String idealNumber;
    @Column(name = "livePlants")
    private String livePlants = "";
    @Column(name = "foodType")
    private String foodType = "";
    @Column(name = "foodOptions")
    private String foodOptions = "";
    @Column(name = "substrate")
    private String substrate = "";
    @Column(name = "light")
    private String light = "";
    @Column(name = "current")
    private String current = "";
    @Column(name = "decorations")
    private String decorations = "";
    @Column(name = "tankSize")
    private String minimumTankSize;
    @Column(name = "picUrl")
    private String picUrl;

    public Fish() {
    }

    public Fish(String commonName, String scientificName, String speciesGroup, String careLevel, String averageSize,
            String lifespan, String phLow, String phHigh, String tempLow, String tempHigh, String hardLow,
            String hardHigh, String swimmingLevel, String isAggressiveSelf, String isAggressiveOther,
            String idealNumber, String livePlants, String foodType, String foodOptions, String substrate, String light,
            String current, String decorations, String minimumTankSize, String picUrl) {
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
        this.picUrl = picUrl;
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

    public String getCareLevel() {
        return this.careLevel;
    }

    public void setCareLevel(String careLevel) {
        this.careLevel = careLevel;
    }

    public String getAverageSize() {
        return this.averageSize;
    }

    public void setAverageSize(String averageSize) {
        this.averageSize = averageSize;
    }

    public String getLifespan() {
        return this.lifespan;
    }

    public void setLifespan(String lifespan) {
        this.lifespan = lifespan;
    }

    public String getPhLow() {
        return this.phLow;
    }

    public void setPhLow(String phLow) {
        this.phLow = phLow;
    }

    public String getPhHigh() {
        return this.phHigh;
    }

    public void setPhHigh(String phHigh) {
        this.phHigh = phHigh;
    }

    public String getTempLow() {
        return this.tempLow;
    }

    public void setTempLow(String tempLow) {
        this.tempLow = tempLow;
    }

    public String getTempHigh() {
        return this.tempHigh;
    }

    public void setTempHigh(String tempHigh) {
        this.tempHigh = tempHigh;
    }

    public String getHardLow() {
        return this.hardLow;
    }

    public void setHardLow(String hardLow) {
        this.hardLow = hardLow;
    }

    public String getHardHigh() {
        return this.hardHigh;
    }

    public void setHardHigh(String hardHigh) {
        this.hardHigh = hardHigh;
    }

    public String getSwimmingLevel() {
        return this.swimmingLevel;
    }

    public void setSwimmingLevel(String swimmingLevel) {
        this.swimmingLevel = swimmingLevel;
    }

    public String getIsAggressiveSelf() {
        return this.isAggressiveSelf;
    }

    public void setIsAggressiveSelf(String isAggressiveSelf) {
        this.isAggressiveSelf = isAggressiveSelf;
    }

    public String getIsAggressiveOther() {
        return this.isAggressiveOther;
    }

    public void setIsAggressiveOther(String isAggressiveOther) {
        this.isAggressiveOther = isAggressiveOther;
    }

    public String getIdealNumber() {
        return this.idealNumber;
    }

    public void setIdealNumber(String idealNumber) {
        this.idealNumber = idealNumber;
    }

    public String getLivePlants() {
        return this.livePlants;
    }

    public void setLivePlants(String livePlants) {
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

    public String getLight() {
        return this.light;
    }

    public void setLight(String light) {
        this.light = light;
    }

    public String getCurrent() {
        return this.current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getDecorations() {
        return this.decorations;
    }

    public void setDecorations(String decorations) {
        this.decorations = decorations;
    }

    public String getMinimumTankSize() {
        return this.minimumTankSize;
    }

    public void setMinimumTankSize(String minimumTankSize) {
        this.minimumTankSize = minimumTankSize;
    }

    public String getPicUrl() {
        return this.picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
                commonName, scientificName, speciesGroup, careLevel, averageSize,
                lifespan, phLow, phHigh, tempLow, tempHigh, hardLow, hardHigh,
                swimmingLevel, isAggressiveSelf, isAggressiveOther, idealNumber,
                livePlants, foodType, foodOptions, substrate, light, current,
                decorations, minimumTankSize, picUrl);
    }

}