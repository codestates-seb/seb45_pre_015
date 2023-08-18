import React, { useState } from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import './App.css';
import Header from './component/header';
import Footer from './component/footer';
import Login from './page/Login';
import Signup from './page/Signup';

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
            <Route path="/" element={''} />
            <Route path="/login" element={<Login />} />
            <Route path="/signup" element={<Signup />} />
        </Routes>
      </main>
      <Footer />
    </BrowserRouter>
  );
};

export default App;