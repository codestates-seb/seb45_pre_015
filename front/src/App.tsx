import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import './App.css';
import Header from './component/header';
import Footer from './component/footer';

const App:React.FC = () => {
  return (
    <BrowserRouter>
      <Header />
      <main className="flex flex-auto min-h-[calc(100vh-180px)]">
        <Routes>
        <Route path="/" element={<Main />} />
        </Routes>
      </main>
      <Footer />
    </BrowserRouter>
  );
}

export default App;
