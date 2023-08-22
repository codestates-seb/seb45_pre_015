import { styled } from "styled-components";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { AskButton, PageButton, SortBtn } from "../component/buttons";
import { fetchQuestionList, fetchTotalQuestions } from "../util/fetchquestion";
import { QuestionData, TotalQuestionData } from "../type/types";
import getTimeAgo from "../component/getTimeAgo";
import { fetchUserInfo } from '../util/fetchlogin'


const PostSum = styled.div`
  display: flex;
  align-items: flex-end;
  justify-content: flex-start;
  flex-direction: column;
  width: 140px;
  padding: 2px 16px 4px 0;
  text-align: left;
  font-size: 13px;
  `

const AllQuestionPage = styled.div`
    display: flex;
    flex-direction: column;
    width: 100%;
    padding: 30px 24px 20px 0px;
    border-left: 1px solid hsl(210,8%,90%) ;

  .header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
  padding-bottom:10px;
}

  .question-count {
  display: flex;
  justify-content:space-between;
  margin-left: 24px;
}

h1 {
  font-weight: 500;
  font-size: 27px;
  margin-left:24px;
}

h4 {
    display: flex;
    align-items: center;
    font-size: 17px;
    padding: 10px 10px 10px 0;
}

.questions-container {
    display: flex;
    width: 100%;
    border-top: 1px solid hsl(210,8%,90%);
    padding: 16px;
}

span {
  color: hsl(210,8%,45%);
}

.questions {
  width: calc(100% - 140px);
    display: flex;
    flex-direction: column;
    justify-content: center;
}

.question-title {
 color: hsl(206,100%,40%);
}
.question-contents {
  font-size: 13px;
}

.question-user-container {
  padding: 5px;
  display: flex;
  justify-content: flex-end;
  align-items: center;
  width: 100%;
}

.question-user-info-container{
    display: flex;
    font-size: 13px;
    justify-content: flex-end;
}

.user {
  color: hsl(206,100%,40%);
  padding: 4px;
}

.asked-date {
  padding: 4px;
}

.button {
  display: flex;
  justify-content: center;
  align-items: center;
}

.border-right {
  border-radius: 6px 0 0 6px;
}

.border-left {
  border-radius: 0 6px 6px 0;
}

.user-img {
    width: 2rem;
    border-radius: 50%;
  }
`;

function QuestionList() {
  const timestamp = "2023-08-22T09:00:00";
  const elapsedTime = getTimeAgo(timestamp);
  const [page, setPage] = useState<number>(1);
  const [questions, setQuestions] = useState<QuestionData[]>([]);
  const [totalQuestions, setTotalQuestions] = useState<TotalQuestionData | null>(null);
  const filter = "newest";
  const searchText = null;
  const [userData, setUserData] = useState<any>({});
  const [userProfileImage, setUserProfileImage] = useState<string>('');


  const fetchTotalQuestionsData = async () => {
    try {
      const total = await fetchTotalQuestions();
      setTotalQuestions(total);
    } catch (error) {
    }
  };

  useEffect(() => {
    fetchQuestions();
    fetchTotalQuestionsData();
  }, [page, filter, searchText]);

  const fetchQuestions = async () => {
    try {
      const data = await fetchQuestionList(page, filter, searchText);
      const sortedData = data.sort((a, b) => b.questionId - a.questionId);
      const Questions = sortedData.slice(0, 10);
      setQuestions(Questions);
    } catch (error: any) {
      console.error(error.message);
    }
  };

  useEffect(() => {
    fetchQuestions();
  }, [page, filter, searchText]);

  useEffect(() => {
    const fetchTotal = async () => {
      const total = await fetchTotalQuestions();
      setTotalQuestions(total);
    };
    fetchTotal();
  }, []);

  useEffect(() => {
    const fetchUserData = async () => {
      try {
        const data = await fetchUserInfo();
        setUserData(data);

        if (data.profilePic) {
          setUserProfileImage(data.profilePic);
        }

        sessionStorage.setItem('userEmail', data.email);
        sessionStorage.setItem('accountId', data.accountId);
      } catch (error) {
        console.error('Error while getting user profile:', error);
      }
    };

    fetchUserData();
  }, []);


  const totalPages = Math.ceil(questions.length / 10);
  const questionId = questions.length > 0 ? questions[0].questionId : 0;
  const maxPages = Math.ceil(questionId / 10);

  return (
    <AllQuestionPage>
      <div className="container">
        <div className="header">
          <h1>All Questions</h1>
          <Link to="/ask">
            <AskButton>Ask Question</AskButton>
          </Link>
        </div>
        <div className="question-count">
        <h4>{totalQuestions !== null ? `${totalQuestions.totalQuestions} questions` : 'No questions'}</h4>
          <div>
            <SortBtn className="border-right">newest</SortBtn>
            <SortBtn className="border-left">voted</SortBtn>
          </div>
        </div>
        <ul>
        {questions.map((question) => (
          <li className="questions-container" key={question.questionId}>
            <PostSum>
              <div>{question.vote}<span> votes</span></div>
              <span> answers</span>
              <div>{question.view}<span> views</span></div>
            </PostSum>
            <div className="questions">
              <Link to={`/question/${question.questionId}`}>
                <div className="question-title">{question.title}</div>
              </Link>
              <div className="question-contents">{question.body}</div>
              <div className="question-user-info-container">
              <Link to={'/mypage'}><img src={userProfileImage} alt={userData.name} className='user-img' /></Link>
                <div className="asked-date">asked {elapsedTime}</div>
              </div>
            </div>
          </li>
        ))}
        </ul>
        <div className="button">
          {page > 1 && (
            <PageButton
              onClick={() => {
                setPage(page - 1);
              }}
            >
              Prev
            </PageButton>
          )}
          {Array.from({ length: maxPages }, (_, i) => (
            <PageButton
              key={i + 1}
              color={page === i + 1 ? 'orange' : 'white'}
              onClick={() => {
                setPage(i + 1);
              }}
            >
              {i + 1}
            </PageButton>
          ))}
          {page < maxPages && (
            <PageButton
              onClick={() => {
                setPage(page + 1);
              }}
            >
              Next
            </PageButton>
          )}
        </div>
      </div>
    </AllQuestionPage>
  );
}

export default QuestionList;