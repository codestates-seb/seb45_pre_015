import React, { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import { fetchQuestionById } from "../util/fetchquestion";
import QuestionUsers from "./questionusers";
import Answers from "../answer/answers";
import styled from "styled-components";
import Vote from "../component/vote";
import getTimeAgo from "../component/getTimeAgo";


const Content = styled.div`
  display: flex;
  padding: 30px;
  flex-direction: column;
  border-left: 1px solid hsl(210, 8%, 90%);

  .head-line {
    display: flex;
    justify-content: space-between;
  }

  h1 {
    font-size: 30px;
  }

  .ask-button {
    background-color: rgb(10, 149, 255);
    color: white;
    border: none;
    border-radius: 0.5rem;
    padding: 9px;
    cursor: pointer;
    text-decoration: none;
    font-size: 13px;

    &:hover {
      background-color: hsl(206, 100%, 40%);
    }

    &:focus {
      box-shadow: 0px 0px 0px 4px hsl(206, 96%, 90%);
      border-color: hsl(206, 85%, 57.5%);
    }
  }

  .activities {
    border-bottom: solid 1px hsl(210, 8%, 90%);
    font-size: 80%;
    padding: 15px 0 15px 0;
  }
  span {
    color: hsl(210,8%,45%);
  }

  .question-container {
    display: flex;
    flex-direction: row;
    align-items: center;
  }

  .question-section {
    display: flex;
    flex-direction: column;
  }

  h2 {
    border-top: 1px solid hsl(210, 8%, 90%);
    padding-top: 20px;
    margin: 0 0 1em;
    font-size: 1.46153846rem;
    font-weight: 400;
    line-height: 1.3;
  }
`;

function Questions() {
  const { questionId } = useParams();
  const [questionData, setQuestionData] = useState({});
  const timestamp = "2023-08-22T09:00:00";
  const elapsedTime = getTimeAgo(timestamp);

  useEffect(() => {
    const fetchQuestion = async () => {
      try {
        const data = await fetchQuestionById(Number(questionId));
        setQuestionData(data);
      } catch (error) {
        console.error(error.message);
      }
    };

    fetchQuestion();
  }, [questionId]);

  return (
    <Content>
      <div className="head-line">
        <h1>{questionData.title}</h1>
        <Link to="/ask">
          <button className="ask-button">Ask Question</button>
        </Link>
      </div>
      <div className="activities">
        <span>Asked </span>
        {elapsedTime}
        <span> Modified</span>
        {elapsedTime}
        <span> Viewed </span>
        {questionData.view}
      </div>
      <div className="question-container">
        <Vote />
        <div className="question-section">
          <p>{questionData.body}</p>
          <QuestionUsers />
        </div>
      </div>
      <Answers />
    </Content>
  );
}

export default Questions;
