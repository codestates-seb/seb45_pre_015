import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import './App.css';
import Header from './component/header';
import Footer from './component/footer';
import Login from './page/Login';
import Signup from './page/Signup';

const App:React.FC = () => {
  return (
    <BrowserRouter>
    
      <Header />
      <main className="flex flex-col flex-grow">
        <Routes>
          <Route path="/" element={''} />
          <Route path="/login" element={<Login />} />
          <Route path="/signup" element={<Signup />} />
        </Routes>
      </main>
      <Footer />
    </BrowserRouter>
  );
}

export default App;
