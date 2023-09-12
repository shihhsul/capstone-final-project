import { useLocation } from "react-router-dom";


const Main = () => {
const location = useLocation();
const message = location.state?.message || ''; // registration message, not even sure if this works haven't tested

  return (
    <div>
    {message && <h2>{message}</h2>}
  
  </div>
);
};

export default Main;
