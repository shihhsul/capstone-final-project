import React, { useContext, useState,useEffect } from "react";
import { useLocation } from "react-router-dom";
import { UserContext } from '../UserContext';
import { useNavigate } from "react-router-dom";

const AquariumPage = ({ aquarium }) => {
  if (!aquarium) {
    return <p>No selected aquarium.</p>;
  }

  const { updateAquariumInUserData } = useContext(UserContext);
  const [selectedFishSchool, setSelectedFishSchool] = useState(null);
  const [isDialogOpen, setIsDialogOpen] = useState(false);
  const [newFishSchoolAmount, setNewFishSchoolAmount] = useState('');
  const [fishTypes, setFishTypes] = useState([]);
  const [isFishTypeListVisible, setIsFishTypeListVisible] = useState(false);
  const [editedFishSchool, setEditedFishSchool] = useState(null);

  useEffect(() => {
    const fetchFishTypes = async () => {
      try {
        const response = await fetch("http://127.0.0.1:8080/fish/findall");
        if (response.ok) {
          const data = await response.json();
          setFishTypes(data); 
        } else {
          console.error("Failed to fetch fishTypes");
        }
      } catch (error) {
        console.error("Error fetching fishTypes:", error);
      }
    };

    fetchFishTypes(); 
  }, []); 

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

  const handleFishSchoolSelect = (fishSchool) => {
    if (selectedFishSchool === fishSchool) {
      setSelectedFishSchool(null);
    } else {
      setSelectedFishSchool(fishSchool); 
    }
  };
    const handleEdit = () => {
      setEditedFishSchool(selectedFishSchool);
      setIsDialogOpen(true);
    };

    const handleDialogClose = () => {
      setIsDialogOpen(false);
    };

    const handleNew = () => {
      setIsFishTypeListVisible(true);
    };

    const handleFishTypeSelect = async (fishType) => {
      try {
        const response = await fetch(`http://127.0.0.1:8080/fishschools/new/${aquarium.name}`, {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: fishType
        });
        if (response.ok) {
          const responseData = await response.json();
          const updatedAquarium = {
            ...aquarium,
            fishSchools: (aquarium.fishSchools || []).concat(responseData),
          };
          updateAquariumInUserData(updatedAquarium);
  
        } else {
          console.error("Save failed");
        }
      } catch (error) {
          console.error("There was a problem with the save request", error);
        }
      
    };

    const handleDialogSave = async () => {
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
        } else {
          console.error("Save failed");
        }
      } catch (error) {
          console.error("There was a problem with the save request", error);
        }
        setIsDialogOpen(false);
        setNewFishSchoolAmount('');
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
            updateAquariumInUserData(aquarium);
          } else {
            console.error("Delete failed");
          }
        } catch (error) {
            console.error("There was a problem with the delete request", error);
          }
        };

      return (
        <div>
          <h2>Aquarium Name: {aquarium.name}</h2>
          <ul>
      {aquarium.fishSchools.map((fishSchool, i) => (
        <li
          key={fishSchool.name}
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
              <li key={fishType.commonName}>
                <button onClick={() => handleFishTypeSelect(fishType.commonName)}>{fishType.commonName}</button>
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
