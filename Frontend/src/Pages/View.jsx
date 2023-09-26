import React, { useContext, useState,useEffect } from "react";
import { UserContext } from '../UserContext';
import EbayRequest from "./ApiRequest";

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

  const [stringArray, setStringArray] = useState([]);

  useEffect(() => {
    fetch('http://127.0.0.1:8080/aquariums/compatibility/'+aquarium.name)
      .then(response => response.json())
      .then(data => {
        setStringArray(data);
      })
      .catch(error => console.error('Error fetching data:', error));
  }, []); 

  const [ebaySearches, setebaySearches] = useState([]);

  useEffect(() => {
    fetch('http://127.0.0.1:8080/aquariums/tankinfo/'+aquarium.name)
      .then(response => response.json())
      .then(data => {
        setebaySearches(data);
      })
      .catch(error => console.error('Error fetching data:', error));
  }, []); 

  console.log(ebaySearches);

  let additionalInfo = null;

  if (stringArray.length > 0) {
    const firstElement = stringArray[0];
  
    if (firstElement !== "Valid Tank") {
      additionalInfo = (
        <div className="bg-red-500 p-4 mb-4 rounded-lg text-white">
          <h3>Warning: Possible Invalid Tank</h3>
        </div>
      );
    } else {
      additionalInfo = (
        <div className="bg-green-500 p-4 mb-4 rounded-lg text-white">
          <h3>Good to go: Valid Tank</h3>
        </div>
      );
    }
  }
  
  return (
    <div className="bg-gradient-to-bottom from-light-blue-300 to-deep-blue-900 min-h-screen p-8 flex-grow">
      <div className="bg-gradient-to-br from-white to-blue-100 border border-blue-200 p-6 rounded-lg shadow-2xl mb-8 flex-grow flex">
      <div className="flex-1 p-4 border border-blue-700">
        <h2 className="text-xl font-semibold mb-4">Aquarium Name: {aquarium.name}</h2>
        <div>
          {aquarium.fishSchools.map((fishSchool, i) => (
            <div key={fishSchool.name} className="mb-4">
              <img src={imageMap[fishSchool.fishType.picUrl]} alt={`${"error"} image`} className="w-20 h-20 rounded-full mr-2" /> 
              <div>
                <div className="border border-blue-500">
                  <span>Fish Species: {fishSchool.fishType.commonName}</span><br />
                  <span>Fish Amount: {fishSchool.amountFish}</span>
                </div>
                <div className="border border-blue-500">
                  <EbayRequest keyword={fishSchool.fishType.commonName} info="false"/>
                </div>
              </div>
            </div>
          ))}
        </div>
        </div>
        <div className="flex-1 p-4 border border-blue-700">
          <h2 className="text-xl font-semibold mb-4">Tank Information:</h2>
          {additionalInfo}
          <ul>
            {stringArray.map((item, index) => (
              <li key={index} className={item && item.startsWith("WARN") ? "text-red-500 font-bold" : ""}>
                {item}
              </li>
            ))}
          </ul>
          </div>
          <div className="flex-1 p-4 border border-blue-700">
          <h2 className="text-xl font-semibold mb-4">Products for You</h2>
          {ebaySearches.map((keyword, index) => (
            <div className="border border-blue-500">
            <EbayRequest key={index} keyword={keyword} info="True" />
            </div>
          ))}
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
