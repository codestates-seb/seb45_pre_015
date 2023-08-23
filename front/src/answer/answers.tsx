import AnswerUsers from "./answerUsers";
import Vote from "../component/vote";
import styled from "styled-components";
import { SetStateAction, useEffect, useState } from "react";
import AnswerForm from "./answerForm";
import { fetchQuestionById } from "../util/fetchquestion";
import { useParams } from "react-router-dom";
import { answers } from "../type/types";

const Content = styled.div`
  display: flex;
  flex-direction: column;

  .question-container {
    display: flex;
    flex-direction: row;
    align-items: center;
    border-top: solid 1px hsl(210, 8%, 90%);
    margin: 20px 0;
  }

  .question-section {
    display: flex;
    flex-direction: column;
  }

  /* 새로운 스타일 추가: 실선 구분선 */
  .answer-divider {
    border-top: 1px solid hsl(210, 8%, 90%);
    margin-top: 10px;
    margin-bottom: 10px;
  }

  /* 새로운 스타일 추가: 각 답변 컨테이너 */
  .answer-container {
    margin-top: 10px;
  }
`;

function Answers() {
  const [submittedAnswer, setSubmittedAnswer] = useState('');
  const [answerData, setAnswerData] = useState<any[]>([]);
  const { questionId } = useParams();
  const { answerId } = useParams<{ answerId: string }>();

  useEffect(() => {
    const fetchAnswer = async () => {
      try {
        const data = await fetchQuestionById(Number(questionId));
        setAnswerData(data.answers);
      } catch (error) {
        console.error('Error fetching answer:', error);
      }
    };

    fetchAnswer();
  }, [questionId]);

  const handleAnswerSubmit = (newAnswer: SetStateAction<string>) => {
    setSubmittedAnswer(newAnswer);
    
    setAnswerData(prevAnswerData => [...prevAnswerData, { id: Date.now(), body: newAnswer }]);
  };

  return (
    <Content>
      <AnswerForm questionId={questionId} onAnswerSubmit={handleAnswerSubmit} />
      {answerData.map((answer: any, index: number) => (
    <div key={answer.id} className="answer-container">
      <div className="question-container">
        <Vote />
        <div className="question-section">
          <p className="answer-content">답변 내용: {answer.body}</p>
          {index < answerData.length - 1 && <div className="answer-divider" />}
          <AnswerUsers />
        </div>
      </div>
    </div>
  ))}
      </Content>
  );
}

export default Answers;