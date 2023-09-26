import React from 'react';
import { Route, Routes, useLocation } from 'react-router-dom';
import Layout from './Layout/Layout';
import Main from './Pages/Main';
import About from './Pages/About';
import Auth from './Login/Auth';
import Homepage from './Pages/Homepage';
import { UserProvider } from './UserContext';
import Edit from './Pages/Edit';
import View from  './Pages/View';
function App() {
  const location = useLocation();
  const layoutRoutes = ['/Main', '/About','/Edit','/View']; // Routes that should have the Layout
  const shouldHaveLayout = layoutRoutes.includes(location.pathname);

  return (
    <UserProvider>
      {shouldHaveLayout ? (
        <Layout>
          <Routes>
            <Route path='/Main' element={<Main />} />
            <Route path='/About' element={<About />} />
            <Route path='/Edit' element={<Edit />} />
            <Route path='/View' element={<View />} />
          </Routes>
        </Layout>
      ) : (
        <Routes>
          <Route path='/' element={<Homepage />} />
          <Route path='/Login' element={<Auth />} />
        </Routes>
      )}
    </UserProvider>
  );
}


export default App;
