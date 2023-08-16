import { styled } from "styled-components";
import {AskButton, PageButton } from "../component/buttons";
import { useState } from "react";
import { Link } from "react-router-dom";
import PostSummary from "./postsummary";

const AllQuestionPage = styled.div`
    display: flex;
    align-items:center;
    justify-content: center;
    flex-direction: column;
.header {
  margin-left: 24px;
  display: flex;
  align-items: center;
  justify-content: space-between;
}
h1 {
    font-size:33px;
}
`;



function QuestionList() {
    const [page, setPage] = useState(1);

    const pageHandle = (pageValue) => {
    if (pageValue === 'Next') {
      if (page >= 5) {
        return;
      }
      setPage(page + 1);
    } else if (pageValue === 'Prev') {
      if (page <= 1) {
        return;
      }
      setPage(page - 1);
    } else {
      setPage(pageValue);
    }
  };
    return (
        <AllQuestionPage>
            <div className="header">
                <h1>All Questions</h1>
                <Link to="/ask"><AskButton>Ask Question</AskButton></Link>
            </div>
            <div>
                <PostSummary />
            
            </div>
            <div>
          <PageButton
            onClick={() => {
              pageHandle('Prev');
            }}
          >
            Prev
          </PageButton>
          <PageButton
            bgColor={page === 1}
            onClick={() => {
              pageHandle(1);
            }}
          >
            1
          </PageButton>
          <PageButton
            bgColor={page === 2}
            onClick={() => {
              pageHandle(2);
            }}
          >
            2
          </PageButton>
          <PageButton
            bgColor={page === 3}
            onClick={() => {
              pageHandle(3);
            }}
          >
            3
          </PageButton>
          <PageButton
            bgColor={page === 4}
            onClick={() => {
              pageHandle(4);
            }}
          >
            4
          </PageButton>
          <PageButton
            bgColor={page === 5}
            onClick={() => {
              pageHandle(5);
            }}
          >
            5
          </PageButton>
          <PageButton
            onClick={() => {
              pageHandle('Next');
            }}
          >
            Next
          </PageButton>
        </div>
        </AllQuestionPage>
    )
}

export default QuestionList;