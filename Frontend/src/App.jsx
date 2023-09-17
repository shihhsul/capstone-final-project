import React from 'react';
import { Route, Routes, useLocation } from 'react-router-dom';
import Layout from './Layout/Layout';
import Main from './Pages/Main';
import Test from './Pages/Test';
import Auth from './Login/Auth';
import Homepage from './Pages/Homepage';
import { UserProvider } from './UserContext';
import Edit from './Pages/Edit';
function App() {
  const location = useLocation();
  const layoutRoutes = ['/Main', '/Test','/Edit']; // Routes that should have the Layout
  const shouldHaveLayout = layoutRoutes.includes(location.pathname);

  return (
    <UserProvider>
      {shouldHaveLayout ? (
        <Layout>
          <Routes>
            <Route path='/Main' element={<Main />} />
            <Route path='/Test' element={<Test />} />
            <Route path='/Edit' element={<Edit />} />
          </Routes>
        </Layout>
      ) : (
        <Routes>
          <Route path='/Homepage' element={<Homepage />} />
          <Route path='/' element={<Auth />} />
        </Routes>
      )}
    </UserProvider>
  );
}


export default App;
