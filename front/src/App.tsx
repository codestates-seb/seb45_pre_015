import React, { useState } from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import './App.css';
import Header from './component/header';
import Footer from './component/footer';
import Login from './page/Login';
import Mypage from './page/Mypage/Mypage'
import Signup from './page/Signup';
import MyTokens from "./page/MyTokens";
import MyRefreshToken from "./page/MyRefreshToken";

const App: React.FC = () => {
  const [isSidebarOpen, setIsSidebarOpen] = useState(false);

  const toggleSidebar = () => {
    setIsSidebarOpen(!isSidebarOpen);
  };

  return (
    <BrowserRouter>
      <Header onSidebarToggle={toggleSidebar} />
      <main
        className={`flex flex-col flex-grow transition-all ${
          isSidebarOpen ? 'ml-64' : 'ml-0'
        }`}
      >
        <Routes>
            <Route path="/" element={''} />
            <Route path="/login" element={<Login />} />
            <Route path="/signup" element={<Signup />} />
            <Route path='/mypage' element={<Mypage />} />
            <Route path="/mytokens" element={<MyTokens />} />
            <Route path="/myrefreshtoken" element={<MyRefreshToken />} />
        </Routes>
      </main>
      <Footer />
    </BrowserRouter>
  );
};

export default App;