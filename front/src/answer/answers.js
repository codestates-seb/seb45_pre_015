import AnswerUsers from "./answerUsers";
import Vote from "../component/vote";
import styled from 'styled-components';
import { useState } from "react";
import AnswerForm from "./answerForm";

const Content = styled.div`
  display: flex;
  flex-direction: column;

.question-container{
  display: flex;
  flex-direction: row;
  align-items: center;
  border-top: solid 1px hsl(210,8%,90%);
  margin: 20px 0;
}

.question-section {
  display: flex;
  flex-direction: column;
  
}

`;

function Answers() {
  const [submittedAnswer, setSubmittedAnswer] = useState("");

  const handleAnswerSubmit = (newAnswer) => {
    setSubmittedAnswer(newAnswer);
  };

  return (
    <Content>
      <AnswerForm questionId={4} onAnswerSubmit={handleAnswerSubmit} />
      <div className="question-container">
        <Vote />
        <div className="question-section">
          <p>{submittedAnswer}</p>
          <AnswerUsers />
        </div>
      </div>
    </Content>
  );
}

export default Answers;
