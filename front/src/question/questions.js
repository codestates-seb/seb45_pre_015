import UserInfo from "../component/userinfo"; 
import Vote from "../component/vote";
import styled from 'styled-components';
import { Link } from 'react-router-dom';
import { AskButton } from "../component/buttons";
import { useState } from "react";
import { postAnswer } from "../util/answerApi";

import Answers from "../answer/answers";



const Content = styled.div`
  display: flex;
  padding: 30px;
  flex-direction: column;
  border-left: 1px solid hsl(210,8%,90%) ;

.head-line {
  display: flex;
  justify-content: space-between;
}

h1 {
    font-size: 30px;
    white-space: pre-line;
}

.ask-button{
  background-color: rgb(10, 149, 255);
  color: white;
  border: none;
  border-radius: 0.5rem;
  padding: 9px;
  cursor: pointer;
  text-decoration: none;
  font-size:13px;

  &:hover {
    background-color:hsl(206,100%,40%);
  }

  &:focus {
    box-shadow: 0px 0px 0px 4px hsl(206,96%,90%);
    border-color: hsl(206,85%,57.5%);
  }
}

.activities {
  border-bottom: solid 1px hsl(210,8%,90%);
  font-size: 80%;
  padding: 15px 0 15px 0;
}


.question-container{
  display: flex;
  flex-direction: row;
  align-items: center;
}

.question-section {
  display: flex;
  flex-direction: column;
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

h2 {
  border-top: 1px solid hsl(210,8%,90%);
  padding-top: 20px;
  margin: 0 0 1em;
  font-size: 1.46153846rem;
  font-weight: 400;
  line-height: 1.3;
}

`;

function Questions() {
  const currentDate = new Date().toLocaleDateString();
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
    <Content>
      <div>
        <div>
          <div className="head-line">
            <h1>제목입니다.</h1>
            <Link to="/ask"><AskButton>Ask Question</AskButton></Link>
          </div>
        </div>
        <div className="activities">
          <span>Asked {currentDate}</span>
          <span> Modified</span>
          <span> Viewed</span>
        </div>
      </div>
            <div className="question-container">
              <Vote />
              <div className="question-section">
                <p>질문글입니다.23324234324324234324324</p>
                <UserInfo />
              </div>
            </div>
       <textarea
        value={answerText}
        onChange={handlePostAnswer}
      />
      <button className="post-button" onClick={handlePostButton}>
        Post Your Answer
      </button>
      <Answers />
      {/* 완성되면 지울것 */}
    </Content>
  );
}

export default Questions;