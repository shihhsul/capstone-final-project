package com.fish.api.entitity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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

    public String getCareLevel() {
        String temp = "Easy";
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
        return ("Highest Care Level: " + temp);
    }

    public String getAverageSize() {
        Double sum = 0.0;
        int fishCount = 0;
        for (FishSchool fishSchool : this.fishSchools) {
            sum = sum + Double.parseDouble(fishSchool.getFishType().getAverageSize())
                    * Double.valueOf(fishSchool.getAmountFish());
            fishCount = fishCount + fishSchool.getAmountFish();
        }
        sum = sum / Double.valueOf(fishCount);
        return ("Average Size: " + sum.intValue() + " inches");
    }

    public String getAverageLifeSpan() {
        Double sum = 0.0;
        int fishCount = 0;
        for (FishSchool fishSchool : this.fishSchools) {
            sum = sum + Double.parseDouble(fishSchool.getFishType().getLifespan())
                    * Double.valueOf(fishSchool.getAmountFish());
            fishCount = fishCount + fishSchool.getAmountFish();
        }
        sum = sum / Double.valueOf(fishCount);
        return ("Average Lifespan: " + sum.intValue() + " years");
    }

    public String getpH() {
        Double phLow = null;
        Double phHigh = null;

        for (FishSchool fishSchool : this.fishSchools) {
            Double fishPhLow = parseDouble(fishSchool.getFishType().getPhLow());
            Double fishPhHigh = parseDouble(fishSchool.getFishType().getPhHigh());

            if (isValueValid(fishPhLow) && (phLow == null || fishPhLow > phLow)) {
                phLow = fishPhLow;
            }

            if (isValueValid(fishPhHigh) && (phHigh == null || fishPhHigh < phHigh)) {
                phHigh = fishPhHigh;
            }
        }

        return calculateRange("pH", phLow, phHigh);
    }

    public String getTemp() {
        Double tempLow = null;
        Double tempHigh = null;

        for (FishSchool fishSchool : this.fishSchools) {
            Double fishTempLow = parseDouble(fishSchool.getFishType().getTempLow());
            Double fishTempHigh = parseDouble(fishSchool.getFishType().getTempHigh());

            if (isValueValid(fishTempLow) && (tempLow == null || fishTempLow > tempLow)) {
                tempLow = fishTempLow;
            }

            if (isValueValid(fishTempHigh) && (tempHigh == null || fishTempHigh < tempHigh)) {
                tempHigh = fishTempHigh;
            }
        }

        return calculateRange("temperature", tempLow, tempHigh);
    }

    public String getHardness() {
        Double hardLow = null;
        Double hardHigh = null;

        for (FishSchool fishSchool : this.fishSchools) {
            Double fishHardLow = parseDouble(fishSchool.getFishType().getHardLow());
            Double fishHardHigh = parseDouble(fishSchool.getFishType().getHardHigh());

            if (isValueValid(fishHardLow) && (hardLow == null || fishHardLow > hardLow)) {
                hardLow = fishHardLow;
            }

            if (isValueValid(fishHardHigh) && (hardHigh == null || fishHardHigh < hardHigh)) {
                hardHigh = fishHardHigh;
            }
        }

        return calculateRange("hardness", hardLow, hardHigh);
    }

    private Double parseDouble(String value) {
        try {
            return value != null ? Double.parseDouble(value) : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private boolean isValueValid(Double value) {
        return value != null && !Double.isNaN(value) && !Double.isInfinite(value);
    }

    private String calculateRange(String parameter, Double low, Double high) {
        if (isValueValid(low) && isValueValid(high) && low <= high) {
            return "Acceptable " + parameter + " range: " + low + "-" + high;
        } else {
            return "WARN-No Valid " + parameter + " Range";
        }
    }

    public String getSelfConflict() {
        String temp = "";
        for (FishSchool fishSchool : this.fishSchools) {
            if (!fishSchool.getFishType().getIsAggressiveSelf().equals("Peaceful")) {
                if (fishSchool.getAmountFish() > 1) {
                    temp = temp + fishSchool.getFishType().getCommonName() + ",";
                }
            }
        }
        if (temp.equals("")) {
            return ("No Same Species Conflicts");
        } else {
            return ("WARN-The following species are aggressive to the same species: " + temp);
        }
    }

    public String getOtherConflict() {
        String temp = "";
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
            return ("No Other Species Conflicts");
        } else {
            return ("WARN-The following species have issues with other fish species: " + temp);
        }
    }

    public String getLivePlants() {
        String output = "Live Plants Needed: No";
        for (FishSchool fishSchool : this.fishSchools) {
            if (fishSchool.getFishType().getLivePlants().equals("Yes")) {
                output = "Live Plants Needed: Yes";
            }
        }
        return output;
    }

    public String getSubstrate() {
        String output = "Substrate placeholder";
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
                        output = "WARN-No valid substrate";

                    } else {
                        substrate = currentSubstrate;
                    }
                }
            }
        }
        String temp = "";
        if (isAtLeastOne && substrate.size() > 0) {
            for (String possibleSubstrate : substrate) {
                temp = temp + possibleSubstrate + ",";
            }
        }
        if (!isAtLeastOne) {
            temp = "Any";
        }
        if (!output.equals("WARN-No valid substrate")) {
            output = "Possible Substrates: " + temp;
        }
        return output;
    }

    public String getLight() {
        String temp = "Moderate";
        boolean hasModerate = false;
        for (FishSchool fishSchool : this.fishSchools) {
            if (fishSchool.getFishType().getLight().equals("Low")) {
                if (temp.equals("Moderate-High")) {
                    temp = "WARN-Wide Range of Light Needs";
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
            return (temp);
        } else {
            return ("Recommended Light: " + temp);
        }
    }

    public String getCurrent() {
        String temp = "Moderate";
        boolean hasModerate = false;
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
            return (temp);
        } else {
            return ("Recommended Current: " + temp);
        }

    }

    public String getDecorations() {
        String temp = "Moderate";
        boolean hasModerate = false;
        for (FishSchool fishSchool : this.fishSchools) {
            if (fishSchool.getFishType().getDecorations().equals("Low")) {
                if (temp.equals("Moderate-High")) {
                    temp = "WARN-Wide Range of Decorations Needs";
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
            return (temp);
        } else {
            return ("Recommended Decorations: " + temp);
        }
    }

    public String getTankSize() {
        Double tankSize = 0.0;
        String temp = "";
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
            return ("Tank Size: " + tankSize.intValue() + " Gallons");
        } else {
            return ("WARN-Tanksize assumes ideal number for " + temp + " Tank Size: " + tankSize.intValue()
                    + " Gallons");
        }

    }

    public String[] aquariumCompatibility() {
        String[] output = new String[16];
        String warnings = "";

        output[0] = "Valid Tank";
        output[1] = this.getCareLevel();
        output[2] = this.getAverageSize();
        output[3] = this.getAverageLifeSpan();
        output[4] = this.getpH();
        if (output[4].startsWith("WARN")) {
            warnings = warnings + "pH,";
        }
        output[5] = this.getTemp();
        if (output[5].startsWith("WARN")) {
            warnings = warnings + "temperature,";
        }
        output[6] = this.getHardness();
        if (output[6].startsWith("WARN")) {
            warnings = warnings + "hardness,";
        }
        output[7] = this.getSelfConflict();
        if (output[7].startsWith("WARN")) {
            warnings = warnings + "Same Species Conflict,";
        }
        output[8] = this.getOtherConflict();
        if (output[8].startsWith("WARN")) {
            warnings = warnings + "Other Species Conflict,";
        }
        output[9] = this.getLivePlants();
        output[10] = this.getSubstrate();
        if (output[10].startsWith("WARN")) {
            warnings = warnings + "Substrate,";
        }
        output[11] = this.getLight();
        if (output[11].startsWith("WARN")) {
            warnings = warnings + "Light,";
        }
        output[12] = this.getCurrent();
        if (output[12].startsWith("WARN")) {
            warnings = warnings + "Current,";
        }
        output[13] = this.getDecorations();
        if (output[13].startsWith("WARN")) {
            warnings = warnings + "Decorations,";
        }
        output[14] = this.getTankSize();

        if (!warnings.equals("")) {
            output[0] = "Warnings:" + warnings;
        }
        return output;
    }

    public String[] tankInfo() {
        String[] output = new String[4];

        Double tankSize = 0.0;
        String temp = "";
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
        output[0] = tankSize.intValue() + " gallon fish tank";
        output[1] = "fish tank decorations";
        output[2] = "aquarium buffer";
        output[3] = "fish tank power head";
        return output;
    }
}