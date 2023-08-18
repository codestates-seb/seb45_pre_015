import { styled } from "styled-components";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import PostSummary from "./postsummary";
import { AskButton, PageButton, SortBtn } from "../component/buttons";
import { fetchQuestionList } from "../util/fetchquestion";
import { QuestionData } from "../type/types";

const AllQuestionPage = styled.div`
    display: flex;
    flex-direction: column;
    width: 100%;
    padding: 30px 24px 20px 0px;
    border-left: 1px solid hsl(210,8%,90%) ;

  .container {
    /* display: flex; */
  }

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

.questions {
  width: calc(100% - 140px);
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: flex-start;
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
`;



function QuestionList() {
  const currentDate = new Date().toLocaleDateString();
  const [page, setPage] = useState<number>(1);
  const [questions, setQuestions] = useState<QuestionData[]>([]);
  const filter = "newest";
  const searchText = null;

  const pageHandle = (pageValue: number | "Prev" | "Next"): void => {
    if (pageValue === "Next") {
      if (page >= 5) {
        return;
      }
      setPage(page + 1);
    } else if (pageValue === "Prev") {
      if (page <= 1) {
        return;
      }
      setPage(page - 1);
    } else {
      setPage(pageValue);
    }
  };

  const fetchQuestions = async () => {
    try {
      const data = await fetchQuestionList(page, filter, searchText);
      setQuestions(data);
    } catch (error:any) {
      console.error(error.message);
    }
  };

  useEffect(() => {
    fetchQuestions();
  }, [page, filter, searchText]);


  

    return (
        <AllQuestionPage>
            <div className="container">
              <div className="header">
                  <h1>All Questions</h1>
                  <Link to="/ask"><AskButton>Ask Question</AskButton></Link>
              </div>
              <div className="question-count">
                <h4>만들어진 숫자 questions</h4>
                <div>
                  <SortBtn className="border-right">newest</SortBtn>
                  <SortBtn className="border-left">voted</SortBtn>
                </div>
                </div>
              <div className="questions-container">
                  <PostSummary />
                  <div className="questions">
                      <Link to="/question"><div className="question-title">질문 제목</div></Link>
                      <div className="question-contents">질문 내용</div>
                      <div className="question-user-container">
                        <div className="question-user-info-container">
                          <div className="user">질문유저 이름</div>
                          <div className="asked-date">asked {currentDate}</div>
                        </div>
                      </div>
                  </div>
              </div>
  
              <div className="button">
          <PageButton
            onClick={() => {
              pageHandle('Prev');
            }}
          >
            Prev
  
          </PageButton>
          {[1, 2, 3, 4, 5].map(btnPage => (
            <PageButton
            color={page === btnPage ? 'orange' : 'white'}
            onClick={() => {
              pageHandle(btnPage);
            }}
          >
            {btnPage}
          </PageButton>
          ))}
          <PageButton
            onClick={() => {
              pageHandle('Next');
            }}
          >
            Next
          </PageButton>
        </div>
      </div>
        </AllQuestionPage>
    )
}

export default QuestionList;