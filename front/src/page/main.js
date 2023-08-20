import React from "react";
import Sidebar from "../component/Sidebar";
import Aside from "../component/aside";
import QuestionList from "../question/questionlist";
import { styled } from "styled-components";

const MainContainerWrap = styled.section`
  display: flex;
  max-width: 78.75rem;
  margin: 0 auto;
`

const MainContainer = styled.div`
`;

const SidebarContainer = styled.div`
`;

const QuestionListContainer = styled.div`
`;

const AsideContainer = styled.div`
`;

function Main() {
  return (
    <MainContainerWrap>
      <Sidebar />
      <MainContainer>
        <QuestionListContainer>
          <QuestionList />
        </QuestionListContainer>
        <AsideContainer>
          <Aside />
        </AsideContainer>
      </MainContainer>
    </MainContainerWrap>
  );
}

export default Main;
