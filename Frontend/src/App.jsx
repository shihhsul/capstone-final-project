import {Route, Routes} from 'react-router'
import Layout from './Layout/Layout';
import Main from './Pages/Main';
import Test from './Pages/Test';

function App() {
  return (
    <Layout>
    <Routes>
      <Route path='/' element={<Main/>}></Route>
      <Route path='/Test' element={<Test/>}></Route>
    </Routes>
    </Layout>
  );
}
export default App;
