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

  const handleView = () => {
    userData.selectedAquarium=selectedAquarium.name;
    navigate("/View", {});
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
        setSelectedAquarium(null);
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
        fishSchools:[],
      };

      const response = await fetch("http://127.0.0.1:8080/aquariums/new/"+userData.userName, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(newAquarium),
      });

      if (response.ok) {
        userData.aquariums = userData.aquariums || [];
        userData.aquariums.push(newAquarium);
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
    <div className="bg-gradient-to-b from-light-blue-300 to-deep-blue-900 min-h-screen p-8">
      {userData && (
        <div className="bg-white p-6 rounded-lg shadow-md mb-8">
          <h2 className="text-xl font-semibold mb-4">Hello {userData.fullName}</h2>
          <h2 className="text-xl font-semibold mb-4">User Data</h2>
          <p className="mb-2"><span className="font-medium">Username:</span> {userData.userName}</p>
          <p className="mb-2"><span className="font-medium">Full Name:</span> {userData.fullName}</p>
          <p className="mb-4"><span className="font-medium">Email:</span> {userData.email}</p>
  
          <h2 className="text-xl font-semibold mb-4">Your Aquariums</h2>
          <ul>
            {userData.aquariums && userData.aquariums.map((aquarium, index) => (
              <li key={index} className="cursor-pointer hover:bg-gray-200 p-2 rounded" onClick={() => handleAquariumSelect(aquarium)}>
                {aquarium.name}
              </li>
            ))}
          </ul>
        </div>
      )}
  
      {selectedAquarium && (
        <div className="bg-white p-6 rounded-lg shadow-md mb-8">
          <h2 className="text-xl font-semibold mb-4">Selected Aquarium</h2>
          <p className="mb-2"><span className="font-medium">Name:</span> {selectedAquarium.name}</p>
        </div>
      )}
  
      <div className="bg-gradient-to-br from-white to-blue-100 border border-blue-200 p-6 rounded-lg shadow-2xl transform transition-transform duration-300 hover:scale-105">
        <h2 className="text-xl font-semibold mb-4">Actions</h2>
        <button className="bg-blue-500 text-white p-2 rounded mr-2 hover:bg-blue-600" onClick={handleNew}>New</button>
        <button className="bg-yellow-500 text-white p-2 rounded mr-2 hover:bg-yellow-600" onClick={handleEdit}>Edit</button>
        <button className="bg-red-500 text-white p-2 rounded p-2 rounded mr-2 hover:bg-red-600" onClick={handleDelete}>Delete</button>
        <button className="bg-green-500 text-white p-2 rounded hover:bg-green-600" onClick={handleView}>View</button>
      </div>
  
      {isDialogOpen && (
        <div className="fixed top-0 left-0 w-full h-full bg-black bg-opacity-50 flex items-center justify-center">
          <div className="dialog-content bg-white p-8 rounded-lg shadow-xl w-1/3">
            <h2 className="text-xl font-semibold mb-4">Create a New Aquarium</h2>
            <input
              type="text"
              placeholder="Enter aquarium name"
              value={newAquariumName}
              onChange={(e) => setNewAquariumName(e.target.value)}
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

export default Main;
