import Sidebar from "../component/Sidebar";
import Aside from "../component/aside";
import { styled } from "styled-components";
import Questions from "../question/questions";

const MainContainerWrap = styled.section`
  display: flex;
  max-width: 78.75rem;
  margin: 0 auto;
`

const MainContainer = styled.div`
  display: flex;
  width: calc(100% - 200px);
`;

const QuestionListContainer = styled.div`
  width: 70%;
`;

const AsideContainer = styled.div`
  width: 30%;
`;

function Question() {
  return (
      <MainContainerWrap>
      <Sidebar />
      <MainContainer>
        <QuestionListContainer>
          <Questions />
          </QuestionListContainer>
        <AsideContainer>
          <Aside />
        </AsideContainer>
      </MainContainer>
    </MainContainerWrap>
  );
}

export default Question;
