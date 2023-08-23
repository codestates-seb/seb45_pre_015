import React, { useState, useEffect } from "react"
import styled from "styled-components";
import axios from "axios";

const Section = styled.section`
  display: flex;
  margin: 1.25rem 0;
`
const Cate = styled.article`
  display: flex;
  flex-direction: column;
  gap: .9375rem;

  button{
    width: 7.5rem;
    padding: .3125rem .625rem;
    border-radius: 15px;
    text-align: left;
  }
  button.active{
    background-color: #f1f2f3;
  }
`

const StatusContainer = styled.div`
    display: flex;
  width: calc(100% - 120px);
  margin: 0 1.25rem;
  gap: 9.375rem;

  .status_title{
    font-size: 1.75rem;
    font-weight: 800;
    margin: 0 0 .625rem ;
  }
  .info_stats > div{
    width: 12.5rem;
    border: 1px solid #eee;
    padding: .9375rem;
  }
  .info_stats .view_box{
    margin: .3125rem 0;
  }
  .info_stats .view_box p{
    font-size: 1.125rem;
    margin: 0 0 .3125rem;
  }
  .info_stats .view_box span{
    font-size: .875rem;
  }
  .info_total{
    display: flex;
    flex-direction: column;
    gap: 1.25rem;
  }
  .qna{
    border: 1px solid #eee;
    padding: 0 .9375rem;
  }
  .qna .text_box{
    display: flex;
    align-items: center;
    gap: .9375rem;
    border-bottom: 1px solid #eee;
    padding: .625rem 0;
  }
  .qna .text_box:last-child{
    border-bottom: 0;
  }
  .qna .num{
    width: 1.875rem;
    border-radius: 5px;
    text-align: center;
    border: 1px solid #eee;
  }
  .qna .title{
    text-align: center;
    color: #5F9BCD;
    max-width: 300px;
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
  }
  .qna .date{
    font-size: 14px;
    align-self: flex-end;
    color: #000011;
  }
`

const Summary = ({activityData}) => {
   const totalVotes = (activityData.answers.reduce((acc, answer) => acc + answer.vote, 0)) +
                    (activityData.questions.reduce((acc, question) => acc + question.vote, 0));
  return(
    <StatusContainer>
      <div className="info_stats">
        <h4 className="status_title">Stats</h4>
        <div>
          <div className="view_box">
            <p>{totalVotes}</p>
            <span>Total Votes</span>
          </div>
          <div className="view_box">
            <p>{activityData.answers.vote}</p>
            <span>Answers</span>
          </div>
          <div className="view_box">
            <p>{activityData.questions.vote}</p>
            <span>Questions</span>
          </div>
        </div>
      </div>
      <div className="info_total">
        <div className="info_answers">
          <h4 className="status_title">Answers</h4>
          <div className="qna">
          {activityData &&
          activityData.answers.map((answers, index) => (
            <div className="text_box" key={index}>
              <span className="num">{answers.vote}</span>
              <p className="title">{answers.title}</p>
              <span className="date">{answers.view}</span>
            </div>
          ))}
          </div>
        </div>
        <div className="info_questions">
          <h4 className="status_title">Questions</h4>
          <div className="qna">
          {activityData &&
          activityData.questions.map((question, index) => (
            <div className="text_box" key={index}>
              <span className="num">{question.vote}</span>
              <p className="title">{question.title}</p>
              <span className="date">{question.view}</span>
            </div>
          ))}
          </div>
        </div>
      </div>
    </StatusContainer>
  )
}

const Answers = ({activityData}) => {
  return(
    <StatusContainer>
      <div className="info_questions">
        <h4 className="status_title">Answers</h4>
        <div className="qna">
        {activityData &&
          activityData.answers.map((answers, index) => (
            <div className="text_box" key={index}>
              <span className="num">{answers.vote}</span>
              <p className="title">{answers.title}</p>
              <span className="date">{answers.view}</span>
            </div>
          ))}
        </div>
      </div>
    </StatusContainer>
  )
}

const Questions = ({activityData}) => {
  return(
    <StatusContainer>
      <div className="info_questions">
        <h4 className="status_title">Questions</h4>
        <div className="qna">
        {activityData &&
          activityData.questions.map((question, index) => (
            <div className="text_box" key={index}>
              <span className="num">{question.vote}</span>
              <p className="title">{question.title}</p>
              <span className="date">{question.view}</span>
            </div>
          ))}
        </div>
      </div>
    </StatusContainer>
  )
}

const Activity = () => {
  const [content , setContent] = useState("first");
  const [activityData, setActivityData] = useState(null);

  useEffect(() => {
    axios.get("https://659a-116-126-166-12.ngrok-free.app/members/mypage", {
      headers: {
        'Content-Type': 'application/json',
        'ngrok-skip-browser-warning': '69420',
        'Authorization': `Bearer ${sessionStorage.getItem('access_token') || ''}`,
        'Refresh': `Bearer ${sessionStorage.getItem('refresh_token') || ''}`,
      }
    })
      .then((response) => {
        setActivityData(response.data);
        console.log(response.data);
      })
      .catch((error) => {
        console.error("API 호출 중 오류 발생:", error);
      });
  }, []); 

  const BUTTON_DATA = [
    {
      "name" : "first",
      "id" : 1,
      "text" : "Summary",
    },
    {
      "name" : "second",
      "id" : 2,
      "text" : "Answers",
    },
    {
      "name" : "third",
      "id" : 3,
      "text" : "Questions",
    },
  ]

  const handleClickButton = e => {
    const {name} = e.target;
    setContent(name);
  }

  const selectComponent = {
    first : <Summary activityData={activityData} />,
    second : <Answers activityData={activityData} />,
    third : <Questions activityData={activityData} />
  }
  return(
    <Section>
      <Cate>
        {BUTTON_DATA.map(data => {
          return (
            <button
              onClick={handleClickButton}
              name={data.name}
              key={data.id}
            >
            {data.text}
            </button>
          )
        })}
      </Cate>
      {content && <>{selectComponent[content]}</>}
    </Section>
  )
}



export default Activity;