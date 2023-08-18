import React from "react";
import Sidebar from "../component/Sidebar";
import Aside from "../component/aside";
import QuestionList from "../question/questionlist";
import { styled } from "styled-components";

const MainContainer = styled.div`
  display: flex;
  flex-direction: row;
  margin: 0 120px;
`;

const SidebarContainer = styled.div`
    width: 15%;
`;

const QuestionListContainer = styled.div`
    width: 55%;
`;

const AsideContainer = styled.div`
    width: 30%;

`;

function Main() {
  return (
    <main className="flex flex-grow">
      <Sidebar />
      <MainContainer className="flex flex-grow">
        <QuestionListContainer>
          <QuestionList />
        </QuestionListContainer>
        <AsideContainer>
          <Aside />
        </AsideContainer>
      </MainContainer>
    </main>
  );
}

export default Main;
