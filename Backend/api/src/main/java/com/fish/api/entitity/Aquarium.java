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
    @JoinColumn(name = "user_id")
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

    public boolean hasFishType(String fishType) {
        for (FishSchool current : this.getFishSchools()) {
            if (current.getFishType().getCommonName().equals(fishType)) {
                return true;
            }
        }
        return false;
    }

    public String[] aquariumCompatibility() {
        String[] output = new String[16];
        String temp = "";
        int fishCount = 0;

        output[0] = "Warning area";
        // carelevel
        temp = "Easy";
        for (FishSchool fishSchool : this.fishSchools) {
            if (temp.equals("Easy")) {
                if (fishSchool.getFishType().getCareLevel().equals("Moderate")
                        || fishSchool.getFishType().getCareLevel().equals("Hard")) {
                    temp = fishSchool.getFishType().getCareLevel();
                }
            } else {
                if (fishSchool.getFishType().getCareLevel().equals("Hard")) {
                    temp = fishSchool.getFishType().getCareLevel();
                }
            }
            if (temp.equals("Hard")) {
                break;
            }
        }
        output[1] = "Highest Care Level: " + temp;

        // average size
        double sum = 0;
        for (FishSchool fishSchool : this.fishSchools) {
            sum = sum + Double.parseDouble(fishSchool.getFishType().getAverageSize())
                    * Double.valueOf(fishSchool.getAmountFish());
            fishCount = fishCount + fishSchool.getAmountFish();
        }
        sum = sum / Double.valueOf(fishCount);
        output[2] = "Average Size: " + sum;

        sum = 0;
        for (FishSchool fishSchool : this.fishSchools) {
            sum = sum + Double.parseDouble(fishSchool.getFishType().getLifespan())
                    * Double.valueOf(fishSchool.getAmountFish());
        }
        sum = sum / Double.valueOf(fishCount);
        output[3] = "Average Lifespan: " + sum;

        Double phLow = null;
        Double phHigh = null;
        for (FishSchool fishSchool : this.fishSchools) {
            if (phLow == null) {
                phLow = Double.valueOf(fishSchool.getFishType().getPhLow());
                phHigh = Double.valueOf(fishSchool.getFishType().getPhHigh());
            }
            if (phLow < Double.valueOf(fishSchool.getFishType().getPhLow())) {
                phLow = Double.valueOf(fishSchool.getFishType().getPhLow());
            }
            if (phHigh > Double.valueOf(fishSchool.getFishType().getPhHigh())) {
                phHigh = Double.valueOf(fishSchool.getFishType().getPhHigh());
            }
        }
        output[4] = "Acceptable pH range: " + phLow.toString() + "-" + phHigh.toString();

        Double tempLow = null;
        Double tempHigh = null;
        for (FishSchool fishSchool : this.fishSchools) {
            if (tempLow == null) {
                tempLow = Double.valueOf(fishSchool.getFishType().getTempLow());
                tempHigh = Double.valueOf(fishSchool.getFishType().getTempHigh());
            }
            if (tempLow < Double.valueOf(fishSchool.getFishType().getTempLow())) {
                tempLow = Double.valueOf(fishSchool.getFishType().getTempLow());
            }
            if (tempHigh > Double.valueOf(fishSchool.getFishType().getTempHigh())) {
                tempHigh = Double.valueOf(fishSchool.getFishType().getTempHigh());
            }
        }
        output[5] = "Acceptable temperature range: " + tempLow.toString() + "-" + tempHigh.toString();

        Double hardLow = null;
        Double hardHigh = null;
        for (FishSchool fishSchool : this.fishSchools) {
            if (hardLow == null) {
                hardLow = Double.valueOf(fishSchool.getFishType().getHardLow());
                hardHigh = Double.valueOf(fishSchool.getFishType().getHardHigh());
            }
            if (hardLow < Double.valueOf(fishSchool.getFishType().getHardLow())) {
                hardLow = Double.valueOf(fishSchool.getFishType().getHardLow());
            }
            if (hardHigh > Double.valueOf(fishSchool.getFishType().getHardHigh())) {
                hardHigh = Double.valueOf(fishSchool.getFishType().getHardHigh());
            }
        }
        output[6] = "Acceptable Hardness range: " + hardLow.toString() + "-" + hardHigh.toString();

        temp = "";
        for (FishSchool fishSchool : this.fishSchools) {
            if (!fishSchool.getFishType().getIsAggressiveSelf().equals("Peaceful")) {
                if (fishSchool.getAmountFish() > 1) {
                    temp = temp + fishSchool.getFishType().getCommonName() + ",";
                }
            }
        }
        if (temp.equals("")) {
            output[7] = "No Same Species Conflicts";
        } else {
            output[7] = "The following species are aggressive to the same species: " + temp;
        }

        temp = "";
        for (FishSchool fishSchool : this.fishSchools) {
            if (!fishSchool.getFishType().getIsAggressiveOther().equals("Peaceful")) {
                if (fishSchool.getFishType().getIsAggressiveOther().equals("Semi Aggressive")) {
                    temp = temp + fishSchool.getFishType().getCommonName() + "(Semi Aggressive),";
                } else {
                    temp = temp + fishSchool.getFishType().getCommonName() + "(Aggressive),";
                }
            }
        }
        if (temp.equals("")) {
            output[8] = "No Other Species Conflicts";
        } else {
            output[8] = "The following species have issues with other fish species: " + temp;
        }

        output[9] = "Live Plants Needed: No";
        for (FishSchool fishSchool : this.fishSchools) {
            if (fishSchool.getFishType().getLivePlants().equals("Yes")) {
                output[7] = "Live Plants Needed: Yes";
            }
        }

        // placeholder
        output[10] = "food options placeholder";

        // placeholder
        output[11] = "Substrate placeholder";

        temp = "";
        temp = "Moderate";
        boolean hasModerate = false;
        for (FishSchool fishSchool : this.fishSchools) {
            if (fishSchool.getFishType().getLight().equals("Low")) {
                if (temp.equals("Moderate-High")) {
                    temp = "Wide Range of Current Needs";
                    hasModerate = false;
                    break;
                } else {
                    temp = "Low-Moderate";
                }
            }
            if (fishSchool.getFishType().getLight().equals("Moderate")) {
                hasModerate = true;
            }
            if (fishSchool.getFishType().getLight().equals("High")) {
                if (temp.equals("Low-Moderate")) {
                    temp = "Wide Range of Current Needs";
                    hasModerate = false;
                    break;
                } else {
                    temp = "Moderate-High";
                }
            }
        }
        if (temp.equals("Low-Moderate") && !hasModerate) {
            temp = "Low";
        }
        if (temp.equals("Moderate-High") && !hasModerate) {
            temp = "High";
        }
        output[12] = "Recommended Light: " + temp;

        temp = "";
        temp = "Moderate";
        hasModerate = false;
        for (FishSchool fishSchool : this.fishSchools) {
            if (fishSchool.getFishType().getCurrent().equals("Low")) {
                if (temp.equals("Moderate-High")) {
                    temp = "Wide Range of Current Needs";
                    hasModerate = false;
                    break;
                } else {
                    temp = "Low-Moderate";
                }
            }
            if (fishSchool.getFishType().getCurrent().equals("Moderate")) {
                hasModerate = true;
            }
            if (fishSchool.getFishType().getCurrent().equals("High")) {
                if (temp.equals("Low-Moderate")) {
                    temp = "Wide Range of Current Needs";
                    hasModerate = false;
                    break;
                } else {
                    temp = "Moderate-High";
                }
            }
        }
        if (temp.equals("Low-Moderate") && !hasModerate) {
            temp = "Low";
        }
        if (temp.equals("Moderate-High") && !hasModerate) {
            temp = "High";
        }
        output[13] = "Recommended Current: " + temp;

        temp = "";
        temp = "Moderate";
        hasModerate = false;
        for (FishSchool fishSchool : this.fishSchools) {
            if (fishSchool.getFishType().getDecorations().equals("Low")) {
                if (temp.equals("Moderate-High")) {
                    temp = "Wide Range of Current Needs";
                    hasModerate = false;
                    break;
                } else {
                    temp = "Low-Moderate";
                }
            }
            if (fishSchool.getFishType().getDecorations().equals("Moderate")) {
                hasModerate = true;
            }
            if (fishSchool.getFishType().getDecorations().equals("High")) {
                if (temp.equals("Low-Moderate")) {
                    temp = "Wide Range of Current Needs";
                    hasModerate = false;
                    break;
                } else {
                    temp = "Moderate-High";
                }
            }
        }
        if (temp.equals("Low-Moderate") && !hasModerate) {
            temp = "Low";
        }
        if (temp.equals("Moderate-High") && !hasModerate) {
            temp = "High";
        }
        output[14] = "Recommended Decorations: " + temp;

        // placeholder
        output[15] = "TankSize Placeholder";

        return output;
    }

}