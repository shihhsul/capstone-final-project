package com.fish.api.entitity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.result.Output;

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
        String warnings = "";

        output[0] = "Valid Tank";
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
        output[2] = "Average Size: " + sum + " inches";

        sum = 0;
        for (FishSchool fishSchool : this.fishSchools) {
            sum = sum + Double.parseDouble(fishSchool.getFishType().getLifespan())
                    * Double.valueOf(fishSchool.getAmountFish());
        }
        sum = sum / Double.valueOf(fishCount);
        output[3] = "Average Lifespan: " + sum + " years";

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

        if (phHigh < phLow) {
            warnings = warnings + "pH Range,";
            output[4] = "WARN-No Valid pH Range";
        } else {
            output[4] = "Acceptable pH range: " + phLow.toString() + "-" + phHigh.toString();
        }

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
        if (tempHigh < tempLow) {
            warnings = warnings + "Temperature Range,";
            output[5] = "WARN-No Valid temperature Range";
        } else {
            output[5] = "Acceptable temperature range: " + tempLow.toString() + "-" + tempHigh.toString() + "°F";
        }

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

        if (hardHigh < hardLow) {
            warnings = warnings + "Hardness Range,";
            output[6] = "WARN-No Valid Hardness Range";
        } else {
            output[6] = "Acceptable Hardness range: " + hardLow.toString() + "-" + hardHigh.toString() + " dGh";
        }

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
            output[7] = "WARN-The following species are aggressive to the same species: " + temp;
            warnings = warnings + "Self Species Conflict,";
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
        if (temp.equals("") || this.fishSchools.size() < 2) {
            output[8] = "No Other Species Conflicts";
        } else {
            output[8] = "WARN-The following species have issues with other fish species: " + temp;
            warnings = warnings + "Other Species Conflict,";
        }

        output[9] = "Live Plants Needed: No";
        for (FishSchool fishSchool : this.fishSchools) {
            if (fishSchool.getFishType().getLivePlants().equals("Yes")) {
                output[9] = "Live Plants Needed: Yes";
            }
        }

        // placeholder
        output[11] = "Substrate placeholder";
        Set<String> substrate = new HashSet<>();
        boolean isAtLeastOne = false;
        for (FishSchool fishSchool : this.fishSchools) {
            String[] array = fishSchool.getFishType().getSubstrate().split("/");
            if (substrate.size() == 0 && !array[0].equals("Any")) {
                for (String current : array) {
                    substrate.add(current);
                }
                isAtLeastOne = true;
            } else {
                Set currentSubstrate = new HashSet<>();
                for (String current : array) {
                    currentSubstrate.add(current);
                }
                if (currentSubstrate.contains("Any")) {

                } else {
                    currentSubstrate.retainAll(substrate);
                    if (currentSubstrate.size() == 0) {
                        output[11] = "WARN-No valid substrate";
                        warnings = warnings + "Substrate,";
                    } else {
                        substrate = currentSubstrate;
                    }
                }
            }
        }
        temp = "";
        if (isAtLeastOne && substrate.size() > 0) {
            for (String possibleSubstrate : substrate) {
                temp = temp + possibleSubstrate + ",";
            }
        }
        if (!isAtLeastOne) {
            temp = "Any";
        }
        if (!output[11].equals("WARN-No valid substrate")) {
            output[11] = "Possible Substrates: " + temp;
        }

        temp = "";
        temp = "Moderate";
        boolean hasModerate = false;
        for (FishSchool fishSchool : this.fishSchools) {
            if (fishSchool.getFishType().getLight().equals("Low")) {
                if (temp.equals("Moderate-High")) {
                    temp = "WARN-Wide Range of Light Needs";
                    warnings = warnings + "Light,";
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
                    temp = "WARN-Wide Range of Light Needs";
                    warnings = warnings + "Light,";
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
        if (temp.equals("WARN-Wide Range of Light Needs")) {
            output[12] = temp;
        } else {
            output[12] = "Recommended Light: " + temp;
        }

        temp = "";
        temp = "Moderate";
        hasModerate = false;
        for (FishSchool fishSchool : this.fishSchools) {
            if (fishSchool.getFishType().getCurrent().equals("Low")) {
                if (temp.equals("Moderate-High")) {
                    temp = "WARN-Wide Range of Current Needs";
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
                    temp = "WARN-Wide Range of Current Needs";
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
        if (temp.equals("WARN-Wide Range of Current Needs")) {
            output[13] = temp;
        } else {
            output[13] = "Recommended Current: " + temp;
        }

        temp = "";
        temp = "Moderate";
        hasModerate = false;
        for (FishSchool fishSchool : this.fishSchools) {
            if (fishSchool.getFishType().getDecorations().equals("Low")) {
                if (temp.equals("Moderate-High")) {
                    temp = "WARN-Wide Range of Decorations Needs";
                    warnings = warnings + "Decorations";
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
                    temp = "WARN-Wide Range of Decorations Needs";
                    warnings = warnings + "Decorations";
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
        if (temp.equals("WARN-Wide Range of Decorations Needs")) {
            output[14] = temp;
        } else {
            output[14] = "Recommended Decorations: " + temp;
        }

        // placeholder
        Double tankSize = 0.0;
        temp = "";
        for (FishSchool fishSchool : this.fishSchools) {
            if (fishSchool.getFishType().getIdealNumber().equals("Any")) {
                tankSize = tankSize + Double.valueOf(fishSchool.getFishType().getMinimumTankSize());
                temp = temp + fishSchool.getFishType().getCommonName() + "(" + fishSchool.getFishType().getIdealNumber()
                        + "), ";
            } else {
                tankSize = tankSize + (Double.valueOf(fishSchool.getAmountFish())
                        / Double.valueOf(fishSchool.getFishType().getIdealNumber()))
                        * Double.valueOf(fishSchool.getFishType().getMinimumTankSize());
            }
        }
        if (temp.equals("")) {
            output[15] = "Tank Size: " + tankSize + " Gallons";
        } else {
            output[15] = "WARN-Tanksize assumes ideal number for " + temp + " Tank Size: " + tankSize + " Gallons";
        }
        if (!warnings.equals("")) {
            output[0] = "Warnings:" + warnings;
        }
        return output;
    }

}