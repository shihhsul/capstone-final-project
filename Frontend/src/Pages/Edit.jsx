import React, { useContext, useState } from "react";
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
  const [newFishSchoolAmount, setnewFishSchoolAmount] = useState('');
  
  const handleFishSchoolSelect = (fishSchool) => {
    console.log(fishSchool);
    setSelectedFishSchool(fishSchool);
  };

  const handleDelete = async() => {
    try {
      const response = await fetch("http://127.0.0.1:8080/fishschools/delete/"+selectedFishSchool.id, {
        method: "DELETE",
        headers: {
          "Content-Type": "application/json",
        },
      });

      if (response.ok) {
        const updatedAquariums =aquarium.fishSchools.filter(fishSchool => fishSchool !== selectedFishSchool);
        aquarium.fishSchools=updatedAquariums;
        navigate("/Edit", {});
        
      } else {
        console.error("Login failed");
      }
    } catch (error) {
      console.error("There was a problem with the login request", error);
    }
  };

  const handleEdit = () => {
    setIsDialogOpen(true);
  };

  const handleDialogClose = () => {
    setIsDialogOpen(false);
  };

  const handleDialogSave = async () => {
    console.log("New aquarium name:", newFishSchoolAmount);

    try {
      const response = await fetch("http://127.0.0.1:8080/fishschools/modify/"+selectedFishSchool.id, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
      });

      if (response.ok) {
        selectedFishSchool.amountFish=newFishSchoolAmount;
        navigate("/Edit", {});
        
      } else {
        console.error("Login failed");
      }
    } catch (error) {
      console.error("There was a problem with the login request", error);
    }

    setIsDialogOpen(false);
    setnewFishSchoolAmount(''); 
  };

  return (
    <div>
      <h2>Aquarium Name: {aquarium.name}</h2>
      <ul>
        {aquarium.fishSchools.map((fishSchool, i) => (
          <li key={i} onClick={() => handleFishSchoolSelect(fishSchool)}>
            Fish School {i + 1}: {fishSchool.name} <br />
            Fish Name: {fishSchool.fishType.commonName} <br />
            Fish Amount:{fishSchool.amountFish}
          </li>
        ))}
      </ul>
      <h2>Actions</h2>
        <button>New</button>
        {/* onClick={} */}
        <button onClick={handleEdit}>Edit</button>
        <button onClick={handleDelete}>Delete</button>

        {isDialogOpen && (
        <div className="dialog">
          <div className="dialog-content">
            <h2>Create a New Aquarium</h2>
            <input
              type="text"
              placeholder="Enter aquarium name"
              value={newFishSchoolAmount}
              onChange={(e) => setnewFishSchoolAmount(e.target.value)}
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

  // Find the selected aquarium
  const selectedAquarium = userData.aquariums.find(
    (aquarium) => aquarium.name === userData.selectedAquarium
  );

  return <AquariumPage aquarium={selectedAquarium} />;

};

export default Edit;
