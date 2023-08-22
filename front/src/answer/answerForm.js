import { useState, useEffect } from "react";
import { styled } from "styled-components";
import { postAnswer, fetchQuestionById } from "../util/fetchquestion";

const AnswerFormBody = styled.div`
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
      <h1>귀하의 답변</h1>
      <textarea
        rows="5"
        placeholder="답변을 입력하세요..."
        value={answer}
        onChange={handleAnswerChange}
      ></textarea>
      <button className="post-button" onClick={handlePostAnswer}>
        답변 게시
      </button>
    </AnswerFormBody>
  );
}

export default AnswerForm;
