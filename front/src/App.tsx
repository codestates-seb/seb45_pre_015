import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import './App.css';
import Header from './component/header';
import Footer from './component/footer';
import Login from './page/Login';
import Mypage from './page/Mypage/Mypage'

const App:React.FC = () => {
  return (
    <BrowserRouter>
      <Header />
      <main className="flex flex-auto min-h-[calc(100vh-180px)] justify-end">
        <Routes>
          <Route path='/' element={''}/>
          <Route path='/login' element={<Login />}/>
          {/* Mypage */}
          <Route path='/mypage' element={<Mypage />} />
        </Routes>
      </main>
      <Footer />
    </BrowserRouter>
  );
}

export default App;
