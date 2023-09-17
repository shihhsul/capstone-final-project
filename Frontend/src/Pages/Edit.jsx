import React, { useContext, useState } from "react";
import { useLocation } from "react-router-dom";
import { UserContext } from '../UserContext';
import { useNavigate } from "react-router-dom";

const AquariumPage = ({ aquarium }) => {
  if (!aquarium) {
    return <p>No selected aquarium.</p>;
  }

  return (
    <div>
      <h2>Aquarium Name: {aquarium.name}</h2>
      <ul>
        {aquarium.fishSchools.map((fishSchool, i) => (
          <li key={i}>
            Fish School {i + 1}: {fishSchool.name} <br />
            Fish Name: {fishSchool.fishType.commonName} <br />
            Fish Amount:{fishSchool.amountFish}
          </li>
        ))}
      </ul>
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
