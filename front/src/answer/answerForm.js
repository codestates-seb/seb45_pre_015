import { useState, useEffect } from "react";
import { styled } from "styled-components";
import { postAnswer, fetchQuestionById } from "../util/fetchquestion";
import AnswerEditor from "./answerEditor";

const AnswerFormBody = styled.div`
  .answer-container {
    display: flex;
    width: 100%;
    height: 5em;
  }

  h1 {
    font-size: 25px;
    border-top: solid 1px hsl(210, 8%, 90%);
    padding: 20px 0;
  }

  .post-button {
    background-color: rgb(10, 149, 255);
    color: white;
    border: none;
    border-radius: 0.5rem;
    padding: 5px;
    cursor: pointer;
    text-decoration: none;
    width: 120px;
    height: 35px;
    font-size: 13px;
    margin-top: 20px;

    &:hover {
      background-color: hsl(206, 100%, 40%);
    }

    &:focus {
      box-shadow: 0px 0px 0px 4px hsl(206, 96%, 90%);
      border-color: hsl(206, 85%, 57.5%);
    }
  }
`;

function AnswerForm({ questionId, onAnswerSubmit }) {
  const [answer, setAnswer] = useState("");
  const [questionData, setQuestionData] = useState(null);

  useEffect(() => {
    fetchQuestionById(questionId)
      .then((data) => {
        setQuestionData(data);
      })
      .catch((error) => {
        console.error("에러 발생: ", error);
      });
  }, [questionId]);

  const handleAnswerChange = (event) => {
    setAnswer(event.target.value);
  };

  const handlePostAnswer = async () => {
    try {
      const data = {
        questionId: questionId,
        body: answer,
      };

      await postAnswer(data);

      alert("답변이 성공적으로 게시되었습니다.");

      setAnswer("");
      onAnswerSubmit(answer);
    } catch (error) {
      alert("답변 게시 중 오류가 발생했습니다: " + error.message);
    }
  };

  return (
    <AnswerFormBody>
      <h1>Your Answer</h1>
      <AnswerEditor // AnswerEditor 컴포넌트로 교체
        onChange={setAnswer} // AnswerEditor 내에서 사용할 onChange 함수 전달
        value={answer} // AnswerEditor 내에서 사용할 value 전달
      />
      <input type="submit" className="post-button" value="Post Your Answer" onClick={handlePostAnswer} />
    </AnswerFormBody>
  );
}

export default AnswerForm;