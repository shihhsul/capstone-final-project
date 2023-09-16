import React, { useContext } from "react";
import { useLocation } from "react-router-dom";
import { UserContext } from '../UserContext';

const Main = () => {
  const { userData } = useContext(UserContext);
  const location = useLocation();
  const message = location.state?.message || '';

  return (
    <div>
      {message && <h2>{message}</h2>}

      {userData && (
        <div>
          <h2>User Data</h2>
          <p>ID: {userData.id}</p>
          <p>Username: {userData.userName}</p>
          <p>Password: {userData.password}</p>
          <p>Username: {userData.fullName}</p>
          <p>email: {userData.email}</p>
          <p>Username: {userData.aquariums}</p>
        </div>
      )}
    </div>
  );
};

export default Main;
