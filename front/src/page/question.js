import Questions from "../question/questions";
import Sidebar from "../component/Sidebar";
import Aside from "../component/aside";
import styled from 'styled-components';

const MainContainer = styled.div`
    display: flex;
    flex-direction: row;
    margin: 0 120px;
`;

const SidebarContainer = styled.div`
    width: 15%;
`;

const QuestionsContainer = styled.div`
    width: 55%;
`;

const AsideContainer = styled.div`
    width: 30%;
`;

function Question() {
    return(
        <div>
            <MainContainer>
            <SidebarContainer>
                <Sidebar />
            </SidebarContainer>
            <QuestionsContainer>
                <Questions />
            </QuestionsContainer>
            <AsideContainer>
                <Aside />
            </AsideContainer>
        </MainContainer>       
        </div>
    )
}

export default Question;