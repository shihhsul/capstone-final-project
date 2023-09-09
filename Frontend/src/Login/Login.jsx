import React, { useState } from "react";
import { Link } from "react-router-dom";
import { useHistory} from 'react-router-dom';

export const Login = (props) => {
  const [email, setEmail ]= useState("");
  const [ pass, setPass ] = useState("");

  const history = useHistory();

  const handleSubmit = async (e) => {
    e.preventDefault();

    const response = await fetch('https://your-api.com/login', { // need to set to our api link but it currently isnt working for me 
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({ email, password: pass }), // stringify basically takes an object (email, pass, name) and converts to JSON-String
  });

  if (response.ok) {
    const data = await response.json();
    localStorage.setItem('token', data.token); // not concrete not sure what to do yet 
    history.push('/');
  } else {
    alert('Incorrect email or password!');
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
export default Login;
