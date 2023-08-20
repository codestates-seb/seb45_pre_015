import React, { useState } from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import './App.css';
import Header from './component/header';
import Footer from './component/footer';
import Login from './page/Login';
import Ask from './page/ask';
import Question from './page/question';
import Mypage from './page/Mypage/Mypage'
import Signup from './page/Signup';
import Main from './page/main';
import MyTokens from "./util/MyTokens";
import styled from "styled-components";

const MainSection = styled.main`
  min-height: 90vh;
  width: 100%;
`


const App: React.FC = () => {

  return (
    <BrowserRouter>
      <Header />
      <MainSection>
        <Routes>
            <Route path="/login" element={<Login />} />
            <Route path="/signup" element={<Signup />} />
            <Route path="/" element={<Main />} />
            <Route path="/ask" element={<Ask />} />
            <Route path="/question" element={<Question />} />
            <Route path='/mypage' element={<Mypage />} />

            <Route path="/mytokens" element={<MyTokens />} />
        </Routes>
      </MainSection>
      <Footer />
    </BrowserRouter>
  );
};

export default App;