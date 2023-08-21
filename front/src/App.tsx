import React, { useState } from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import './App.css';
import Header from './component/header';
import Footer from './component/footer';
import Login from './page/Login';
import Ask from './page/ask';
import AllQuestion from './page/Allquestion';

import Mypage from './page/Mypage/Mypage'

import Signup from './page/Signup';
import Main from './page/main';
import MyTokens from "./util/MyTokens";
import Question from './page/question';


const App: React.FC = () => {
  const [isSidebarOpen, setIsSidebarOpen] = useState(false);

  const toggleSidebar = () => {
    setIsSidebarOpen(!isSidebarOpen);
  };

  return (
    <BrowserRouter>
      <Header onSidebarToggle={toggleSidebar} />
      <main
        className={`flex flex-col flex-grow transition-all w-full h-full ${
          isSidebarOpen ? 'ml-64' : 'ml-0'
        }`}
      >
        <Routes>
            <Route path="/login" element={<Login />} />
            <Route path="/signup" element={<Signup />} />
            <Route path="/" element={<Main />} />
            <Route path="/ask" element={<Ask />} />
            <Route path="/question" element={<AllQuestion />} />
            <Route path='/mypage' element={<Mypage />} />
            <Route path='/question/:questionId' element={<Question/>}/>
            <Route path="/mytokens" element={<MyTokens />} />
        </Routes>
      </main>
      <Footer />
    </BrowserRouter>
  );
};

export default App;