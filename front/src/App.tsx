import React from 'react';
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';
import './App.css';
import Header from './component/header';
import Footer from './component/footer';
import Login from './page/Login';
import Ask from './page/ask';
import Question from './page/question';
import QuestionList from './question/questionlist';

const App:React.FC = () => {
  return (
    <BrowserRouter>
      <Header />
      <main className="flex flex-auto min-h-[calc(100vh-180px)]">
      <Link to="/ask"><button>QuestionAsk</button></Link>
        .......
        <Link to="/question"><button>Question</button></Link>
        ......
        <Link to ="/allquestion"><button>AllQuestions</button></Link>
        <Routes>
          <Route path="/" element={''} />
          <Route path="/login" element={<Login />} />
          <Route path="/ask" element={<Ask />} />
          <Route path="/question" element={<Question />} />
          <Route path="/allquestion" element={<QuestionList />}></Route>
        </Routes>
      </main>
      <Footer />
    </BrowserRouter>
  );
}

export default App;
