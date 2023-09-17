import React, { useState, useContext } from "react";
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import { UserContext } from '../UserContext';

export const Login = (props) => {
  const [username, setusername] = useState("");
  const [pass, setPass] = useState("");
  const { userData, setUserData } = useContext(UserContext);

  const navigate = useNavigate(); 

  const handleSubmit = async (e) => {
    e.preventDefault();

    const attemptData = {
      id: null,
      userName: username,
      password: pass,
      fullName: null,
      email: null,
      aquariums: null,
    };

    try {
      const response = await fetch("http://127.0.0.1:8080/users/login", {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(attemptData),
      });

      if (response.ok) {
        const userDataFromResponse = await response.json();
        console.log("UserData from API:", userDataFromResponse);
        setUserData(userDataFromResponse);
        navigate("/Main", { state: { message: `Thanks for registering ${username}` } });
        console.log(userData);
      } else {
        console.error("Login failed");
      }
    } catch (error) {
      console.error("There was a problem with the login request", error);
    }
  };

  return (
    <>
      <div className='justify-center items-center flex min-h-screen text-center bg-h-14 bg-gradient-to-r from-sky-300 to-indigo-700'>
        <form
          className='flex flex-col p-20 border border-white rounded-md'
          onSubmit={handleSubmit}
        >
          <h2 className='justify-center items-center flex text-center text-white font-bold text-2xl m-2'>
            Login
          </h2>
          <label
            className='text-left text-white p-1 rounded-sm'
            htmlFor='username'
          >
            Username:
          </label>
          <input
            className='m-1 rounded-md'
            value={username}
            onChange={(e) => setusername(e.target.value)}
            type='username'
            placeholder=' username'
            id='username'
            name='username'
          ></input>
          <label className='text-left text-white p-1' htmlFor='password'>
            Password:
          </label>
          <input
            className='m-1 rounded-md'
            value={pass}
            onChange={(e) => setPass(e.target.value)}
            type='password'
            placeholder=' **********'
            id='password'
            name='password'
          ></input>
          <button
            className='text-white border p-1 m-1 rounded-md cursor-pointer hover:'
            type='submit'
          >
            Log In
          </button>
          <button
            className='text-white underline cursor-pointer'
            onClick={() => props.onFormSwitch("register")}
          >
            Don't have an account? Register Here!
          </button>
        </form>
      </div>
    </>
  );
};
export default Login;
