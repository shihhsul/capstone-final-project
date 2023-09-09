import React, { useState } from "react";
import { Link, } from "react-router-dom";
import { useHistory } from 'react-router-dom'


export const Register = (props) => {

  const histoy = useHistory;

  const [email, setEmail] = useState("");
  const [pass, setPass] = useState("");
  const [name, setName] = useState("");

  const handleSubmit = async  (e) => {
    e.preventDefault();
    try {
      const response = await fetch('https://your-api.com/register', {  // need to set to our api link but it currently isnt working for me
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ email, password: pass, name }),
      });
  
      if (response.ok) {
        const data = await response.json();
        // Takes user back to main page and displays the name they inputted. 
        history.push({
          pathname: '/',
          state: { message: `Thanks for registering ${name}` },
        });
      } else {
        // Handle error, maybe set some state to show an error message
        console.error('Registration failed'); // add an alert to tell user it errored 
      }
    } catch (error) {
      // If fetch itself fails
      console.error('There was a problem with the registration request', error);
    }

  }
  return (
    <>
      <div className='justify-center items-center flex min-h-screen text-center bg-h-14 bg-gradient-to-r from-sky-300 to-indigo-700'>
        <form
          className='flex flex-col p-20 border border-white rounded-md'
          onSubmit={handleSubmit}
        >
          <h2 className='justify-center items-center flex text-center text-white font-bold text-2xl m-2'>
            Register
          </h2>
          <label className='text-left text-white p-1 rounded-sm'>
            Full Name:
          </label>
          <input
            className='m-1 rounded-md'
            value={name}
            onChange={(e) => setName(e.target.value)}
            name='name'
            id='name'
            placeholder=' Full Name'
          ></input>
          <label
            className='text-left text-white p-1 rounded-sm'
            htmlFor='email'
          >
            Email:
          </label>
          <input
            className='m-1 rounded-md'
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            type='email'
            placeholder=' youremail@gmail.com'
            id='email'
            name='email'
          ></input>
          <label
            className='text-left text-white p-1 rounded-sm'
            htmlFor='password'
          >
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
            className='text-white border p-1 m-1 rounded-md cursor-pointer hover:opacity-80'
            type='submit'
          >
            Register
          </button>
          <button
            className='text-white underline cursor-pointer'
            onClick={() => props.onFormSwitch("login")}
          >
            If you already have an account? Log in Here!
          </button>
          <Link to='/'>
            <button className='text-white underline cursor-pointer'>
              Return to Main Page
            </button>
          </Link>
        </form>
      </div>
    </>
  );
};
