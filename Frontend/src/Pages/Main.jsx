import React, { useContext, useState } from "react";
import { useLocation } from "react-router-dom";
import { UserContext } from '../UserContext';
import { useNavigate } from "react-router-dom";
const Main = () => {
  const { userData } = useContext(UserContext);

  const location = useLocation();
  const message = location.state?.message || '';
  const [selectedAquarium, setSelectedAquarium] = useState(null);
  const [newAquariumName, setNewAquariumName] = useState('');
  const [isDialogOpen, setIsDialogOpen] = useState(false);
  const navigate = useNavigate(); 
  
  const handleAquariumSelect = (aquarium) => {
    setSelectedAquarium(aquarium);
  };

  const handleNew = () => {
    setIsDialogOpen(true);
  };

  const handleEdit = () => {
    userData.selectedAquarium=selectedAquarium.name;
    console.log(userData);
    navigate("/Edit", {});
  };

  const handleDelete = async() => {
    try {
      const response = await fetch("http://127.0.0.1:8080/aquariums/delete/"+selectedAquarium.name, {
        method: "DELETE",
        headers: {
          "Content-Type": "application/json",
        },
      });

      if (response.ok) {
        const updatedAquariums = userData.aquariums.filter(aquarium => aquarium !== selectedAquarium);
        userData.aquariums=updatedAquariums;
        navigate("/Main", {});
        
      } else {
        console.error("Login failed");
      }
    } catch (error) {
      console.error("There was a problem with the login request", error);
    }
  };

  const handleDialogClose = () => {
    setIsDialogOpen(false);
  };

  const handleDialogSave = async () => {
    console.log("New aquarium name:", newAquariumName);

    try {

      const newAquarium = {
        name: newAquariumName,
        user: null,
        fishSchools: null,
      };

      const response = await fetch("http://127.0.0.1:8080/aquariums/new/"+userData.userName, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(newAquarium),
      });

      if (response.ok) {
        userData.aquariums = [...userData.aquariums, newAquarium];
        navigate("/Main", {});
        
      } else {
        console.error("Login failed");
      }
    } catch (error) {
      console.error("There was a problem with the login request", error);
    }

    setIsDialogOpen(false);
    setNewAquariumName(''); 
  };

  return (
    <div>
      {message && <h2>{message}</h2>}

      {userData && (
        <div>
          <h2>User Data</h2>
          <p>ID: {userData.id}</p>
          <p>Username: {userData.userName}</p>
          <p>Password: {userData.password}</p>
          <p>Username: {userData.fullName}</p>
          <p>Email: {userData.email}</p>

          <h2>Aquariums</h2>
          <ul>
            {userData.aquariums && userData.aquariums.map((aquarium, index) => (
              <li key={index} onClick={() => handleAquariumSelect(aquarium)}>
                {aquarium.name}
              </li>
            ))}
          </ul>
        </div>
      )}

      {selectedAquarium && (
        <div>
          <h2>Selected Aquarium</h2>
          <p>Name: {selectedAquarium.name}</p>
          {/* add aqua properties */}
        </div>
      )}

      <div>
        <h2>Actions</h2>
        <button onClick={handleNew}>New</button>
        <button onClick={handleEdit}>Edit</button>
        <button onClick={handleDelete}>Delete</button>
      </div>

      {isDialogOpen && (
        <div className="dialog">
          <div className="dialog-content">
            <h2>Create a New Aquarium</h2>
            <input
              type="text"
              placeholder="Enter aquarium name"
              value={newAquariumName}
              onChange={(e) => setNewAquariumName(e.target.value)}
            />
            <button onClick={handleDialogSave}>Save</button>
            <button onClick={handleDialogClose}>Cancel</button>
          </div>
        </div>
      )}
    </div>
  );
};

export default Main;
