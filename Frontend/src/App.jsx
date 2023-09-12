import React from 'react';
import { Route, Routes, useLocation } from 'react-router-dom';
import Layout from './Layout/Layout';
import Main from './Pages/Main';
import Test from './Pages/Test';
import Auth from './Login/Auth'

function App() {
  const location = useLocation();

  const isLoginPage = location.pathname === '/Login';

  const RoutesWithOptionalLayout = () => (
    <Routes>
      <Route path='/' element={<Main />} />
      <Route path='/Test' element={<Test />} />
      <Route path='/Login' element={<Auth />} />
    </Routes>
  );

  return (
    <>
      {isLoginPage ? (
        <RoutesWithOptionalLayout />
      ) : (
        <Layout>
          <RoutesWithOptionalLayout />
        </Layout>
      )}
    </>
  );
}

export default App;
