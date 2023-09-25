import React, { useContext, useState,useEffect } from "react";
import { useLocation } from "react-router-dom";
import { UserContext } from '../UserContext';
import { useNavigate } from "react-router-dom";
import fish from '../FishImages/download.jpg'

import AfricanCichlid1Img from '../FishImages/AfricanCichlids1.jpg'
import AfricanCichlid2Img from '../FishImages/AfricanCichlids2.jpg'
import AfricanCichlid3Img from '../FishImages/AfricanCichlids3.jpg'
import AfricanCichlid4Img from '../FishImages/AfricanCichlids4.jpg'
import AfricanCichlid5Img from '../FishImages/AfricanCichlids5.jpg'
import AmericanCichlid1Img from '../FishImages/AmericanCichlids1.jpg';
import AmericanCichlid2Img from '../FishImages/AmericanCichlids2.jpg';
import AmericanCichlid3Img from '../FishImages/AmericanCichlids3.jpg';
import AmericanCichlid4Img from '../FishImages/AmericanCichlids4.jpg';
import AmericanCichlid5Img from '../FishImages/AmericanCichlids5.jpg';
import Angelfish1Img from '../FishImages/Angelfish1.jpg';
import Angelfish2Img from '../FishImages/Angelfish2.jpg';

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
};


const AquariumPage = ({ aquarium }) => {
  if (!aquarium) {
    return <p>No selected aquarium.</p>;
  }

  const [stringArray, setStringArray] = useState([]);

  useEffect(() => {
    fetch('http://127.0.0.1:8080/aquariums/compatibility/'+aquarium.name)
      .then(response => response.json())
      .then(data => {
        setStringArray(data);
      })
      .catch(error => console.error('Error fetching data:', error));
  }, []); 

  let additionalInfo = null;
  if (stringArray.length > 0) {
    const firstElement = stringArray[0];
    if(firstElement!="Valid Tank"){
      additionalInfo = (
        <div>
          <h3>Warning Invalid Tank</h3>
        </div>
      );
    }
  }
  
    return (
          <div className="bg-gradient-to-b from-light-blue-300 to-deep-blue-900 min-h-screen p-8">
            <div className="bg-gradient-to-br from-white to-blue-100 border border-blue-200 p-6 rounded-lg shadow-2xl mb-8">
              <h2 className="text-xl font-semibold mb-4">Aquarium Name: {aquarium.name}</h2>
              <ul className="mb-8">
                {aquarium.fishSchools.map((fishSchool, i) => (
                  <li key={fishSchool.name}>
                    <img src={imageMap[fishSchool.fishType.picUrl]} alt={`${"error"} image`} className="w-20 h-20 rounded-full mr-2" /> 
                    Fish Species: {fishSchool.fishType.commonName} <br />
                    Fish Amount: {fishSchool.amountFish}
                  </li>
                ))}
              </ul>
              <div>
                {additionalInfo}
                <h3>String Array:</h3>
                <ul>
                  {stringArray.map((item, index) => (
                    <li key={index}>{item}</li>
                  ))}
                </ul>
              </div>
            </div>
          </div>
        );
      };

const View = () => {
  const { userData } = useContext(UserContext);
  const selectedAquarium = userData.aquariums.find(
    (aquarium) => aquarium.name === userData.selectedAquarium
  );

  return <AquariumPage aquarium={selectedAquarium} />;
};

export default View;
