import { useLocation } from "react-router-dom";
import { Link } from "react-router-dom";
import React, { useRef, useEffect, useState } from "react";
import fishSwimming from "../Homepage Components/fishSwimming.mp4";
import bigFish from "../Homepage Components/big-fish.png";
import school from "../Homepage Components/school.png";
import fishBowl from "../Homepage Components/fish-bowl.png";
import fishFood from "../Homepage Components/fish_1.png";
import "../Homepage Components/Homepage.css";

const Homepage = () => {
  // const location = useLocation();
  // const message = location.state?.message || "";

  const videoRef = useRef(null);

  useEffect(() => {
    // Hide the default MP4 control bar
    videoRef.current.controls = false;
  }, []);

  const [selected, setSelected] = useState(null);

  const toggleInfo = (item) => {
    if (selected === item) {
      setSelected(null);
    } else {
      setSelected(item);
    }
  };

  return (
    <div className='bg-blue-950 min-h-screen'>
      <div className='relative flex flex-col'>
        <div className='relative w-full h-0 py-52 overflow-hidden'>
          <video
            className='absolute top-0 left-0 w-full h-full object-cover z-0'
            ref={videoRef}
            src={fishSwimming}
            controls
            autoPlay
            loop
            muted
          >
            Your browser does not support the video tag.
          </video>
          <div className='absolute w-full h-full gradient-overlay z-10'></div>
          <div className='absolute bottom-0 left-0 p-4 z-20'>
            <h1 className='text-5xl font-black text-yellow-500'>
              Welcome To Finding Nemo
            </h1>
            <span className=''> </span>
            <p className='text-3xl text-white text-center'>
              Discover best practices for taking care of your very own aquatic
              friends!
            </p>
          </div>
        </div>
      </div>

      {/* <div className='h-1 bg-black w-full'></div> */}
      <h1 className='text-center text-2xl font-bold text-yellow-500 p-8'>
        App Details
      </h1>
      <p className='text-center text-xl text-white p-5'>
        Design your own aquarium, mix and match fresh and saltwater species, and
        uncover the ideal setup to bring aquatic life into your actual living
        space.
      </p>

      <div className='flex justify-center items-center'>
        <ul className='flex text-white font-bold mx-auto'>
          <div
            className='flex flex-col items-center mx-4 px-4 cursor-pointer clickable-div'
            onClick={() => toggleInfo("Fish Compatibility")}
          >
            <img className='w-28 h-28' src={bigFish} alt='largeFish' />
            <li className=''>
              Fish Compatibility
              {selected === "Fish Compatibility" && (
                <div className='text-gray-400'>ff</div>
              )}
            </li>
          </div>
          <div
            className='flex flex-col items-center mx-4 px-4 cursor-pointer clickable-div'
            onClick={() => toggleInfo("Saltwater Vs Freshwater")}
          >
            <img className='w-28 h-28' src={school} alt='school' />
            <li>
              Saltwater Vs Freshwater
              {selected === "Saltwater Vs Freshwater" && (
                <div className='text-gray-400'>
                  Saltwater Vs Freshwater Info Here
                </div>
              )}
            </li>
          </div>
          <div
            className='flex flex-col items-center mx-4 px-4 cursor-pointer clickable-div'
            onClick={() => toggleInfo("Optimal Housing")}
          >
            <img className='w-28 h-28' src={fishBowl} alt='bowl' />
            <li>
              Optimal Housing
              {selected === "Optimal Housing" && (
                <div className='text-gray-400'>Optimal Housing Info Here</div>
              )}
            </li>
          </div>
          <div
            className='flex flex-col items-center mx-4 px-4 cursor-pointer clickable-div'
            onClick={() => toggleInfo("Fish Diet")}
          >
            <img className='w-28 h-28' src={fishFood} alt='food' />
            <li>
              Fish Diet
              {selected === "Fish Diet" && (
                <div className='text-gray-400'>Fish Diet Info Here</div>
              )}
            </li>
          </div>
        </ul>
      </div>
      <div className='text-center py-10'>
        <Link
          to='/Main'
          className='inline-block w-48 text-white bg-gradient-to-r from-cyan-500 to-blue-500 hover:bg-gradient-to-bl focus:ring-4 focus:outline-none focus:ring-cyan-300 dark:focus:ring-cyan-800 font-medium rounded-lg text-sm px-5 py-2.5 mb-2 p-8'
        >
          Get Started
        </Link>
      </div>
    </div>
  );
};

export default Homepage;
