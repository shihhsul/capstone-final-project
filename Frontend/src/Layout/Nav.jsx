import { Link } from "react-router-dom";
import logo from "../App Components/Fish.png";

const Nav = () => {
  return (
    <header className='w-full h-20 flex items-center justify-between bg-blue-600 sticky p-10'>
      <div className='text-2xl text-white font-bold flex items-center'>
        Finding Nemo
        <img className='w-10 h-15' src={logo} alt='Fish' />
      </div>
      <nav>
        <ul className='list-none m-0 p-0 flex items-baseline'>
          <li className='ml-10'></li>
          <li className='ml-5'>
            <Link to='/'>
              <a className='text-2xl text-white font-bold no-underline hover:opacity-80'>
                Main
              </a>
            </Link>
          </li>
          <li className='ml-5'>
            <Link to='/Test'>
              <a className='text-2xl text-white font-bold no-underline hover:opacity-80'>
                Test
              </a>
            </Link>
          </li>
        </ul>
      </nav>
    </header>
  );
};

export default Nav;
