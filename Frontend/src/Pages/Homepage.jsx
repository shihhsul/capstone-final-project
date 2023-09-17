import { useLocation } from "react-router-dom";
import { Link } from "react-router-dom";
import React, { useRef, useEffect } from "react";
import fishSwimming from "../App Components/fishSwimming.mp4";

const Homepage = () => {
  // const location = useLocation();
  // const message = location.state?.message || "";

  const videoRef = useRef(null);

  useEffect(() => {
    // Hide the default MP4 control bar
    videoRef.current.controls = false;
  }, []);

  return (
    <div className=' bg-blue-950 min-h-screen'>
      <div className='relative flex flex-col'>
        <div className='relative w-full h-0 py-52 overflow-hidden'>
          <video
            className='absolute top-0 left-0 w-full h-full object-cover'
            ref={videoRef}
            src={fishSwimming}
            controls
            autoPlay
            loop
            muted
          >
            Your browser does not support the video tag.
          </video>
        </div>
        <div className='md:absolute flex flex-col justify-end h-full bg-blue-800'>
          <div className='container max-w-xl pt-16 pb-24 md:pb-60'>
            <h1 className='text-center text-2xl md:text-3xl font-black text-white static'>
              Welcome To Finding Nemo
            </h1>
            {/* : Where Every Tank Tells a Tale */}
            <span className=''> </span>
            <p className='md:text-2xl text-white text-center'>
              Discover best practices for taking care of your very own aquatic
              friends! 
            </p>
            {/* //"one stop shop for all your aquarium needs" */}
          </div>
        </div>
      </div>
      <h1 className='text-center text-2xl font-bold text-yellow-300 p-8'>
        App Details
      </h1>
      <p className="text-center text-xl font-bold text-white p-5">
        This App allows users to create virtual aquariums and discover ways to bring your aquatic friends to the real world. Experiment with different tanks, fresh water vs salt water fish, fish combinations, and more to find the best ways to bring an aquatic friend to your home. 
      </p>
      <div className='text-center'>
        <Link
          to='/Main'
          className='inline-block w-48 text-white bg-gradient-to-r from-cyan-500 to-blue-500 hover:bg-gradient-to-bl focus:ring-4 focus:outline-none focus:ring-cyan-300 dark:focus:ring-cyan-800 font-medium rounded-lg text-sm px-5 py-2.5 mb-2'
        >
          Get Started
        </Link>
      </div>
    </div>
  );
};

export default Homepage;
