
import { useState } from "react";
import { postAnswer } from "../util/answerApi";
import { styled } from "styled-components";

const AnswerFormBody = styled.div`

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


function AnswerForm () {

    const [answerText, setAnswerText] = useState('');

    const handlePostButton = async () => {
      try {
        await postAnswer(answerText); // 답변 게시 API 호출
        console.log('답변이 성공적으로 게시되었습니다');
        alert('답변이 성공적으로 게시되었습니다');
      } catch (error) {
        console.error('답변 게시 중 오류 발생', error);
        alert('답변 게시 중 오류가 발생했습니다');
      }
    };
  
    const handlePostAnswer = event => {
      setAnswerText(event.target.value);
    };
    return (
<AnswerFormBody>
    <textarea
            value={answerText}
            onChange={handlePostAnswer}
          />
          <button className="post-button" onClick={handlePostButton}>
            Post Your Answer
          </button>
</AnswerFormBody>
    )
}

export default AnswerForm;