import React, { useContext, useState,useEffect } from "react";
import { useLocation } from "react-router-dom";
import { UserContext } from '../UserContext';
import { useNavigate } from "react-router-dom";
import fish from '../FishImages/download.jpg'

import AfricanCichlid1Img from '../FishImages/AfricanCichlids1.jpg'
import AfricanCichlid2Img from '../FishImages/AfricanCichlids2.jpg'

const imageMap = {
  "AfricanCichlids1": AfricanCichlid1Img,
  "AfricanCichlids2": AfricanCichlid2Img,
};


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
<div className="bg-gradient-to-b from-light-blue-300 to-deep-blue-900 min-h-screen p-8">
    
    <div className="bg-gradient-to-br from-white to-blue-100 border border-blue-200 p-6 rounded-lg shadow-2xl mb-8">
      <h2 className="text-xl font-semibold mb-4">Aquarium Name: {aquarium.name}</h2>
      <ul className="mb-8">
        {aquarium.fishSchools.map((fishSchool, i) => (
          <li
            key={fishSchool.name}
            onClick={() => handleFishSchoolSelect(fishSchool)}
            className={
              selectedFishSchool === fishSchool
                ? "selected fish-school bg-blue-200 p-2 rounded mt-2 cursor-pointer"
                : "fish-school bg-gray-200 p-2 rounded mt-2 cursor-pointer"
            }
          >
            <img src={imageMap[fishSchool.fishType.picUrl]} alt={`${"error"} image`} className="w-10 h-10 rounded-full mr-2" /> 
            Fish Species: {fishSchool.fishType.commonName} <br />
            Fish Amount: {fishSchool.amountFish}
          </li>
        ))}
      </ul>

      <h2 className="text-xl font-semibold mb-4">Actions</h2>
      <button className="bg-blue-500 text-white p-2 rounded mr-2 hover:bg-blue-600" onClick={handleNew}>New</button>
      <button className="bg-yellow-500 text-white p-2 rounded mr-2 hover:bg-yellow-600" onClick={(e) => { e.stopPropagation(); handleEdit(selectedFishSchool); }}>Edit</button>
      <button className="bg-red-500 text-white p-2 rounded hover:bg-red-600" onClick={() => handleDelete(selectedFishSchool)}>Delete</button>
    </div>

    {isFishTypeListVisible && (
  <div className="bg-gradient-to-br from-white to-blue-100 border border-blue-200 p-6 rounded-lg shadow-2xl mb-8">
    <h3 className="text-lg font-semibold mb-4">Fish Types</h3>
    <ul>
      {fishTypes.map((fishType) => (
        <li key={fishType.commonName} className="mb-2">
          <div className="flex items-center">
          <img src={imageMap[fishType.picUrl]} alt={`${fishType.commonName} image`} className="w-10 h-10 rounded-full mr-2" /> 
            {/* <img src={fishType.pictureUrl} alt={`${fishType.commonName} image`} className="w-10 h-10 rounded-full mr-2" /> */}
            <button className="text-blue-600 hover:underline" onClick={() => handleFishTypeSelect(fishType.commonName)}>
              {fishType.commonName}
            </button>
          </div>
        </li>
      ))}
    </ul>
  </div>
)}

          {selectedFishSchool && (
            <div className="bg-gradient-to-br from-white to-blue-100 border border-blue-200 p-6 rounded-lg shadow-2xl mb-8">
              <h3>Viewing Fish School: {selectedFishSchool.name}</h3>
              <img src={imageMap[selectedFishSchool.fishType.picUrl]}  alt={`${"error"} image`} className="w-10 h-10 rounded-full mr-2" /> 
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
              <p>Aggressive to other Species?: {selectedFishSchool.fishType.isAggressiveOther}</p>
              <p>Aggressive to same Species?: {selectedFishSchool.fishType.isAggressiveSelf}</p>
              <p>Swimming Level: {selectedFishSchool.fishType.swimmingLevel}</p>

              <h3>Tank Requirements:</h3>
              <p>Temperature Range: {selectedFishSchool.fishType.tempLow}-{selectedFishSchool.fishType.tempHigh} °F</p>
              <p>pH Range: {selectedFishSchool.fishType.phLow}-{selectedFishSchool.fishType.phHigh}</p>
              <p>Water Hardness: {selectedFishSchool.fishType.hardLow}-{selectedFishSchool.fishType.hardHigh}</p>
              <p>Minimum Tank Size: {selectedFishSchool.fishType.minimumTankSize}</p>
              <p>Substrate: {selectedFishSchool.fishType.substrate}</p>
              <p>Light Level: {selectedFishSchool.fishType.light}</p>
              <p>Live Plants: {selectedFishSchool.fishType.livePlants}</p>
              <p>Current Level: {selectedFishSchool.fishType.current}</p>
              <p>Decorations Present: {selectedFishSchool.fishType.decorations}</p>
            </div>
          )}
          {isDialogOpen && (
      <div className="fixed top-0 left-0 w-full h-full bg-black bg-opacity-50 flex items-center justify-center">
        <div className="dialog-content bg-white p-8 rounded-lg shadow-xl w-1/3">
          <h2 className="text-xl font-semibold mb-4">Edit Fish School</h2>
          <input
            type="text"
            placeholder="Enter new amount of fish"
            value={newFishSchoolAmount}
            onChange={(e) => setNewFishSchoolAmount(e.target.value)}
            className="border p-2 w-full rounded mb-4"
          />
          <button className="bg-green-500 text-white p-2 rounded mr-2 hover:bg-green-600" onClick={handleDialogSave}>Save</button>
          <button className="bg-gray-500 text-white p-2 rounded hover:bg-gray-600" onClick={handleDialogClose}>Cancel</button>
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
