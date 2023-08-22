import { useState } from "react";
import { styled } from "styled-components";
import { fetchQuestionById } from "../util/fetchquestion";
import AnswerEditor from "./answerEditor";

const AnswerFormBody = styled.div`

h1 {
    font-size: 25px;
    border-top: solid 1px hsl(210,8%,90%);
    padding: 20px 0;
}


textarea {
  height: 200px;
  width: 100%;
  border: 1px solid hsl(210,8%,80%);
  border-radius: 3px;

  &:focus {
    box-shadow: 0px 0px 0px 4px hsl(206,96%,90%);
    border-color: blue;
  }
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
  margin-top: 20px ;

  &:hover {
    background-color:hsl(206,100%,40%);
  }

  &:focus {
    box-shadow: 0px 0px 0px 4px hsl(206,96%,90%);
    border-color: hsl(206,85%,57.5%);
  }
}
`;


function AnswerForm({ questionId, onAnswerSubmit }) {
    const [answer, setAnswer] = useState("");
  
    const handleAnswerPost = (newAnswer) => {
      setAnswer((prevAnswer) => prevAnswer + '\n' + newAnswer);
    };
  
    const handleAnswerChange = (event) => {
      setAnswer(event.target.value);
    };
  
    const handlePostAnswer = async () => {
      try {
        const response = await fetchQuestionById(questionId);
        alert("답변이 작성되었습니다:", response);
  
        handleAnswerPost(response.answer);
  
        setAnswer("");
        
        // 전달받은 콜백 함수를 호출하여 답변 내용을 전달
        onAnswerSubmit(response.answer);
      } catch (error) {
        alert("답변 작성 중 오류 발생:", error);
      }
    };
  
    return (
      <AnswerFormBody>
        <h1>Your Answer</h1>
        <AnswerEditor
          value={answer}
          onChange={handleAnswerChange}
        />
        <button className="post-button" onClick={handlePostAnswer}>
          Post Your Answer
        </button>
      </AnswerFormBody>
    );
  }
  export default AnswerForm;
