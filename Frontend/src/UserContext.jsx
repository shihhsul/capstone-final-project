import React, { createContext, useState } from 'react';

const UserContext = createContext();

const UserProvider = ({ children }) => {
  const initialUserData = {
    id: null,
    userName: null,
    password: null,
    fullName: null,
    email: null,
    aquariums: [],  // Initialize as an empty array
    selectedAquarium: null,
  };

  const [userData, setUserData] = useState(initialUserData);

  const updateAquariumInUserData = (updatedAquarium) => {
    console.log('Updating aquarium in userData:', updatedAquarium);
    const updatedAquariums = userData.aquariums.map((aquarium) =>
      aquarium.name === updatedAquarium.name ? updatedAquarium : aquarium
    );

    setUserData((prevUserData) => ({
      ...prevUserData,
      aquariums: updatedAquariums,
    }));
  };

  return (
    <UserContext.Provider value={{ userData, setUserData, updateAquariumInUserData }}>
      {children}
    </UserContext.Provider>
  );
};

export { UserContext, UserProvider };
