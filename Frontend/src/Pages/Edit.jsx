import React, { useContext, useState,useEffect } from "react";
import { useLocation } from "react-router-dom";
import { UserContext } from '../UserContext';
import { useNavigate } from "react-router-dom";
import fish from '../FishImages/download.jpg'

import AfricanCichlid1Img from '../FishImages/AfricanCichlids1.jpg';
import AfricanCichlid2Img from '../FishImages/AfricanCichlids2.jpg';
import AfricanCichlid3Img from '../FishImages/AfricanCichlids3.jpg';
import AfricanCichlid4Img from '../FishImages/AfricanCichlids4.jpg';
import AfricanCichlid5Img from '../FishImages/AfricanCichlids5.jpg';
import AmericanCichlid1Img from '../FishImages/AmericanCichlids1.jpg';
import AmericanCichlid2Img from '../FishImages/AmericanCichlids2.jpg';
import AmericanCichlid3Img from '../FishImages/AmericanCichlids3.jpg';
import AmericanCichlid4Img from '../FishImages/AmericanCichlids4.jpg';
import AmericanCichlid5Img from '../FishImages/AmericanCichlids5.jpg';
import Angelfish1Img from '../FishImages/Angelfish1.jpg';
import Angelfish2Img from '../FishImages/Angelfish2.jpg';
import Betta1Img from '../FishImages/Bettas1.jpg';
import Betta2Img from '../FishImages/Bettas2.jpg';
import Betta3Img from '../FishImages/Bettas3.jpg';
import Eels1Img from '../FishImages/Eels1.jpg';
import Eels2Img from '../FishImages/Eels2.jpg';
import Eels3Img from '../FishImages/Eels3.jpg';
import Koi1Img from '../FishImages/Koi1.jpg';

const imageMap = {
  "AfricanCichlids1": AfricanCichlid1Img,
  "AfricanCichlids2": AfricanCichlid2Img,
  "AfricanCichlids3": AfricanCichlid3Img,
  "AfricanCichlids4": AfricanCichlid4Img,
  "AfricanCichlids5": AfricanCichlid5Img,
  "AmericanCichlids1": AmericanCichlid1Img,
  "AmericanCichlids2": AmericanCichlid2Img,
  "AmericanCichlids3": AmericanCichlid3Img,
  "AmericanCichlids4": AmericanCichlid4Img,
  "AmericanCichlids5": AmericanCichlid5Img,
  "Angelfish1": Angelfish1Img,
  "Angelfish2": Angelfish2Img,
  "Bettas1": Betta1Img,
  "Bettas2": Betta2Img,
  "Bettas3": Betta3Img,
  "Eels1": Eels1Img,
  "Eels2": Eels2Img,
  "Eels3": Eels3Img,
  "Koi1": Koi1Img,
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

  const [isFishSpeciesListVisible, setIsFishSpeciesListVisible] = useState(false);
  const [selectedSpecies, setSelectedSpecies] = useState(null);

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
      setIsFishSpeciesListVisible(true);
    };

    const handleFishSpeciesSelect = (fishSpecies) => {
      setIsFishSpeciesListVisible(false);
      setSelectedSpecies(fishSpecies);
      setIsFishTypeListVisible(true);
    }
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
        setIsFishTypeListVisible(false);
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
            <img src={imageMap[fishSchool.fishType.picUrl]} alt={`${"error"} image`} className="w-20 h-20 rounded-full mr-2" /> 
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

    {isFishSpeciesListVisible&& (
    <div className="bg-gradient-to-br from-white to-blue-100 border border-blue-200 p-6 rounded-lg shadow-2xl mb-8">
    <h3 className="text-lg font-semibold mb-4">Fish Types</h3>
    <ul>
    {[...new Set(fishTypes.map(fishType => fishType.speciesGroup))]
        .map((speciesGroup) => (
        <li key={speciesGroup} className="mb-2">
          <div className="flex items-center">
            <button className="text-blue-600 hover:underline" onClick={() => handleFishSpeciesSelect(speciesGroup)}>
              {speciesGroup}
            </button>
          </div>
        </li>
      ))}
    </ul>
  </div>
)}

    {isFishTypeListVisible && (
  <div className="bg-gradient-to-br from-white to-blue-100 border border-blue-200 p-6 rounded-lg shadow-2xl mb-8">
    <h3 className="text-lg font-semibold mb-4">Fish Types</h3>
    <ul>
    {fishTypes
        .filter((fishType) => fishType.speciesGroup === selectedSpecies) 
        .map((fishType) => (
        <li key={fishType.commonName} className="mb-2">
          <div className="flex items-center">
          <img src={imageMap[fishType.picUrl]} alt={`${fishType.commonName} image`} className="w-20 h-20 rounded-full mr-2" /> 
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
        <h3 style={{ fontWeight: 'bold' }}>Viewing Fish School: {selectedFishSchool.name}</h3>
        <img src={imageMap[selectedFishSchool.fishType.picUrl]} alt={`${"error"} image`} className="w-20 h-20 rounded-full mr-2" />
        <h3 style={{ fontWeight: 'bold' }}>Species Information:</h3>
        <p><span style={{ fontWeight: 'bold' }}>Fish Name:</span> {selectedFishSchool.fishType.commonName}</p>
        <p><span style={{ fontWeight: 'bold' }}>Scientific Name:</span> {selectedFishSchool.fishType.scientificName}</p>
        <p><span style={{ fontWeight: 'bold' }}>Species Group:</span> {selectedFishSchool.fishType.speciesGroup}</p>
        <p><span style={{ fontWeight: 'bold' }}>Lifespan:</span> {selectedFishSchool.fishType.lifespan}</p>
        <p><span style={{ fontWeight: 'bold' }}>Average Size:</span> {selectedFishSchool.fishType.averageSize}</p>
        <h3 style={{ fontWeight: 'bold' }}>Fish Care Details</h3>
        <p><span style={{ fontWeight: 'bold' }}>Care Level:</span> {selectedFishSchool.fishType.careLevel}</p>
        <p><span style={{ fontWeight: 'bold' }}>Food Options:</span> {selectedFishSchool.fishType.foodOptions}</p>
        <p><span style={{ fontWeight: 'bold' }}>FoodType:</span> {selectedFishSchool.fishType.foodType}</p>
        <p><span style={{ fontWeight: 'bold' }}>Ideal Number:</span> {selectedFishSchool.fishType.idealNumber}</p>
        <p><span style={{ fontWeight: 'bold' }}>Aggressive to other Species?:</span> {selectedFishSchool.fishType.isAggressiveOther}</p>
        <p><span style={{ fontWeight: 'bold' }}>Aggressive to same Species?:</span> {selectedFishSchool.fishType.isAggressiveSelf}</p>
        <p><span style={{ fontWeight: 'bold' }}>Swimming Level:</span> {selectedFishSchool.fishType.swimmingLevel}</p>
        <h3 style={{ fontWeight: 'bold' }}>Tank Requirements:</h3>
        <p><span style={{ fontWeight: 'bold' }}>Temperature Range:</span> {selectedFishSchool.fishType.tempLow}-{selectedFishSchool.fishType.tempHigh} °F</p>
        <p><span style={{ fontWeight: 'bold' }}>pH Range:</span> {selectedFishSchool.fishType.phLow}-{selectedFishSchool.fishType.phHigh}</p>
        <p><span style={{ fontWeight: 'bold' }}>Water Hardness:</span> {selectedFishSchool.fishType.hardLow}-{selectedFishSchool.fishType.hardHigh}</p>
        <p><span style={{ fontWeight: 'bold' }}>Minimum Tank Size:</span> {selectedFishSchool.fishType.minimumTankSize}</p>
        <p><span style={{ fontWeight: 'bold' }}>Substrate:</span> {selectedFishSchool.fishType.substrate}</p>
        <p><span style={{ fontWeight: 'bold' }}>Light Level:</span> {selectedFishSchool.fishType.light}</p>
        <p><span style={{ fontWeight: 'bold' }}>Live Plants:</span> {selectedFishSchool.fishType.livePlants}</p>
        <p><span style={{ fontWeight: 'bold' }}>Current Level:</span> {selectedFishSchool.fishType.current}</p>
        <p><span style={{ fontWeight: 'bold' }}>Decorations Present:</span> {selectedFishSchool.fishType.decorations}</p>
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
