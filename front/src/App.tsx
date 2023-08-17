import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import './App.css';
import Header from './component/header';
import Footer from './component/footer';
import Login from './page/Login';
import Signup from './page/Signup';
import MyTokens from "./page/MyTokens";
import MyRefreshToken from "./page/MyRefreshToken";

const App:React.FC = () => {
  return (
    <BrowserRouter>
    
      <Header />
      <main className="flex flex-col flex-grow">
        <Routes>
            <Route path="/" element={''} />
            <Route path="/login" element={<Login />} />
            <Route path="/signup" element={<Signup />} />
            <Route path="/mytokens" element={<MyTokens />} />
            <Route path="/myrefreshtoken" element={<MyRefreshToken />} />
        </Routes>
          <h1> {sessionStorage.getItem("memberEmail")} </h1>
      </main>
      <Footer />
    </BrowserRouter>
  );
}

export default App;
