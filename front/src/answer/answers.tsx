import AnswerUsers from "./answerUsers";
import Vote from "../component/vote";
import styled from "styled-components";
import { SetStateAction, useEffect, useState } from "react";
import AnswerForm from "./answerForm";
import {fetchAnswerById, fetchQuestionById} from "../util/fetchquestion";
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
  }, [answerId]);

  const handleAnswerSubmit = (newAnswer: SetStateAction<string>) => {
    setSubmittedAnswer(newAnswer);
  };

  return (
    <Content>
      <AnswerForm questionId={questionId} onAnswerSubmit={handleAnswerSubmit} />
      <div className="question-container">
        <Vote />
        <div className="question-section">
          {answerData.map((answer: any) => (
              <div key={answer.id}>
                <p>답변 내용: {answer.body}</p>
              </div>
          ))}

          <AnswerUsers />
        </div>
      </div>
    </Content>
  );
}

export default Answers;
