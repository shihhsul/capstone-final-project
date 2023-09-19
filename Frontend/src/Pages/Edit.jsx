import React, { useContext, useState,useEffect } from "react";
import { useLocation } from "react-router-dom";
import { UserContext } from '../UserContext';
import { useNavigate } from "react-router-dom";

const AquariumPage = ({ aquarium }) => {
  if (!aquarium) {
    return <p>No selected aquarium.</p>;
  }
  const navigate = useNavigate();
  const [selectedFishSchool, setSelectedFishSchool] = useState(null);
  const [isDialogOpen, setIsDialogOpen] = useState(false);
  const [newFishSchoolAmount, setNewFishSchoolAmount] = useState('');
  const [fishTypes] = useState([
    { id: 1, name: 'Fish Type 1', scientificName: 'Scientific Name 1', speciesGroup: 'Species Group 1', lifespan: 'Lifespan 1', averageSize: 'Average Size 1', careLevel: 'Care Level 1', foodOptions: 'Food Options 1', foodType: 'Food Type 1', idealNumber: 'Ideal Number 1', isAggressiveToOtherSpecies: 'Yes', isAggressiveToSameSpecies: 'No', swimmingLevel: 'Swimming Level 1', temperatureRange: 'Temperature Range 1', phRange: 'pH Range 1', waterHardness: 'Water Hardness 1', minTankSize: 'Minimum Tank Size 1', substrate: 'Substrate 1', lightLevel: 'Light Level 1', livePlants: 'Yes', currentLevel: 'Current Level 1', decorationsPresent: 'Yes' },
    { id: 2, name: 'Fish Type 2', scientificName: 'Scientific Name 2', speciesGroup: 'Species Group 2', lifespan: 'Lifespan 2', averageSize: 'Average Size 2', careLevel: 'Care Level 2', foodOptions: 'Food Options 2', foodType: 'Food Type 2', idealNumber: 'Ideal Number 2', isAggressiveToOtherSpecies: 'No', isAggressiveToSameSpecies: 'Yes', swimmingLevel: 'Swimming Level 2', temperatureRange: 'Temperature Range 2', phRange: 'pH Range 2', waterHardness: 'Water Hardness 2', minTankSize: 'Minimum Tank Size 2', substrate: 'Substrate 2', lightLevel: 'Light Level 2', livePlants: 'No', currentLevel: 'Current Level 2', decorationsPresent: 'No' },
    { id: 3, name: 'Fish Type 3', scientificName: 'Scientific Name 3', speciesGroup: 'Species Group 3', lifespan: 'Lifespan 3', averageSize: 'Average Size 3', careLevel: 'Care Level 3', foodOptions: 'Food Options 3', foodType: 'Food Type 3', idealNumber: 'Ideal Number 3', isAggressiveToOtherSpecies: 'No', isAggressiveToSameSpecies: 'No', swimmingLevel: 'Swimming Level 3', temperatureRange: 'Temperature Range 3', phRange: 'pH Range 3', waterHardness: 'Water Hardness 3', minTankSize: 'Minimum Tank Size 3', substrate: 'Substrate 3', lightLevel: 'Light Level 3', livePlants: 'Yes', currentLevel: 'Current Level 3', decorationsPresent: 'Yes' }
  ]);
  const [selectedFishType, setSelectedFishType] = useState(null);
  const [isFishTypeListVisible, setIsFishTypeListVisible] = useState(false);
  const [editedFishSchool, setEditedFishSchool] = useState(null);

  const handleFishSchoolSelect = (fishSchool) => {
    if (selectedFishSchool === fishSchool) {
      setSelectedFishSchool(null);
    } else {
      setSelectedFishSchool(fishSchool); 
    }
  };

  const handleDelete = async () => {
    try {
      const response = await fetch(`http://127.0.0.1:8080/fishschools/delete/${selectedFishSchool.id}`, {
        method: "DELETE",
        headers: {
          "Content-Type": "application/json",
        },
      });

      if (response.ok) {
        const updatedAquariums = aquarium.fishSchools.filter(fishSchool => fishSchool !== selectedFishSchool);
        aquarium.fishSchools = updatedAquariums;
        navigate("/Edit", {});
      } else {
        console.error("Delete failed");
      }
    } catch (error) {
        console.error("There was a problem with the delete request", error);
      }
    };

    const handleEdit = () => {
      setEditedFishSchool(selectedFishSchool);
      setIsDialogOpen(true);
    };

    const handleDialogClose = () => {
      setIsDialogOpen(false);
    };

    const handleFishTypeSelect = async (fishType) => {
      setSelectedFishType(fishType)
      try {
        console.log(aquarium);
        const response = await fetch(`http://127.0.0.1:8080/fishschools/new/${aquarium.name}`, {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: "Goldfish"
        });

        if (response.ok) {
          navigate("/Edit", {});
        } else {
          console.error("Save failed");
        }
      } catch (error) {
          console.error("There was a problem with the save request", error);
        }
      
    };

    const handleDialogSave = async () => {
      console.log("New aquarium name:", newFishSchoolAmount);

      try {
        const response = await fetch(`http://127.0.0.1:8080/fishschools/modify/${editedFishSchool.id}`, {
          method: "PUT",
          headers: {
            "Content-Type": "application/json",
          },
          body: newFishSchoolAmount
        });

        if (response.ok) {
          editedFishSchool.amountFish = newFishSchoolAmount;
          navigate("/Edit", {});
        } else {
          console.error("Save failed");
        }
      } catch (error) {
          console.error("There was a problem with the save request", error);
        }

        setIsDialogOpen(false);
        setNewFishSchoolAmount('');
      };

      const handleNew = () => {
        setIsFishTypeListVisible(true);
        setSelectedFishType(null);
      };

      useEffect(() => {
        const handleOutsideClick = (event) => {
          const editButton = document.getElementById('editButton');
          const dialogDiv = document.querySelector('.dialog-content');
      
          if (
            selectedFishSchool &&
            !event.target.closest(".fish-school") &&
            !event.target.closest(".fish-type-list") &&
            !(editButton && event.target === editButton) &&
            !(dialogDiv && event.target.closest(".dialog-content"))
          ) {
            setSelectedFishSchool(null);
          }
        };
      
        window.addEventListener("click", handleOutsideClick);
      
        return () => {
          window.removeEventListener("click", handleOutsideClick);
        };
      }, [selectedFishSchool]);



      return (
        <div>
          <h2>Aquarium Name: {aquarium.name}</h2>
          <ul>
      {aquarium.fishSchools.map((fishSchool, i) => (
        <li
          key={i}
          onClick={() => handleFishSchoolSelect(fishSchool)}
          className={
            selectedFishSchool === fishSchool
              ? "selected fish-school"
              : "fish-school"
          }
        >
          Fish School {i + 1}: {fishSchool.name} <br />
          Fish Name: {fishSchool.fishType.commonName} <br />
          Fish Amount: {fishSchool.amountFish}
        </li>
      ))}
    </ul>
          <h2>Actions</h2>
          <button onClick={handleNew}>New</button>
          <button onClick={(e) => { e.stopPropagation(); handleEdit(selectedFishSchool); }}>Edit</button>
          <button onClick={() => handleDelete(selectedFishSchool)}>Delete</button>

          {isFishTypeListVisible && (
            <div>
              <h3>Fish Types</h3>
              <ul>
                {fishTypes.map((fishType) => (
                  <li key={fishType.id}>
                    {fishType.name}
                    <button onClick={() => handleFishTypeSelect(fishType)}>Select</button>
                  </li>
                ))}
              </ul>
            </div>
          )}

          {selectedFishSchool && (
            <div>
              <h3>Viewing Fish School: {selectedFishSchool.name}</h3>
              <h3>Species Information:</h3>
              <p>Fish Name: {selectedFishSchool.fishType.commonName}</p>
              <p>Scientific Name: {selectedFishSchool.fishType.scientificName}</p>
              <p>Species Group: {selectedFishSchool.fishType.speciesGroup}</p>
              <p>Lifespan: {selectedFishSchool.fishType.lifespan}</p>
              <p>Average Size: {selectedFishSchool.fishType.averageSize}</p>

              <h3>Fish Care Details</h3>
              <p>Care Level: {selectedFishSchool.fishType.careLevel}</p>
              <p>Food Options: {selectedFishSchool.fishType.foodOptions}</p>
              <p>FoodType: {selectedFishSchool.fishType.foodType}</p>
              <p>Ideal Number: {selectedFishSchool.fishType.idealNumber}</p>
              <p>Aggressive to other Species?: {selectedFishSchool.fishType.isAggressiveToOtherSpecies}</p>
              <p>Aggressive to same Species?: {selectedFishSchool.fishType.isAggressiveToSameSpecies}</p>
              <p>Swimming Level: {selectedFishSchool.fishType.swimmingLevel}</p>

              <h3>Tank Requirements:</h3>
              <p>Temperature Range: {selectedFishSchool.fishType.temperatureRange}</p>
              <p>pH Range: {selectedFishSchool.fishType.phRange}</p>
              <p>Water Hardness: {selectedFishSchool.fishType.waterHardness}</p>
              <p>Minimum Tank Size: {selectedFishSchool.fishType.minTankSize}</p>
              <p>Substrate: {selectedFishSchool.fishType.substrate}</p>
              <p>Light Level: {selectedFishSchool.fishType.lightLevel}</p>
              <p>Live Plants: {selectedFishSchool.fishType.livePlants}</p>
              <p>Current Level: {selectedFishSchool.fishType.currentLevel}</p>
              <p>Decorations Present: {selectedFishSchool.fishType.decorationsPresent}</p>
            </div>
          )}

          {isDialogOpen && (
            <div className="dialog">
              <div className="dialog-content">
                <h2>Edit Fish School</h2>
                <input
                  type="text"
                  placeholder="Enter new amount of fish"
                  value={newFishSchoolAmount}
                  onChange={(e) => setNewFishSchoolAmount(e.target.value)}
                />
                <button onClick={handleDialogSave}>Save</button>
                <button onClick={handleDialogClose}>Cancel</button>
              </div>
            </div>
          )}
        </div>
      );
    };

const Edit = () => {
  const { userData } = useContext(UserContext);

  const selectedAquarium = userData.aquariums.find(
    (aquarium) => aquarium.name === userData.selectedAquarium
  );

  return <AquariumPage aquarium={selectedAquarium} />;
};

export default Edit;
