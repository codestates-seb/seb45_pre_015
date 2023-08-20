import UserInfo from "../component/userinfo";
import Vote from "../component/vote";
import styled from 'styled-components';

const Content = styled.div`
  display: flex;
  flex-direction: column;

  h1 {
  font-size: 30px;
  white-space: pre-line;
  }

  .row {
  display: flex;
  flex-direction: row;
  padding: 20px 20px 20px 0;
  gap: 60px;  
  }

  .answer-section {
  display: flex;
  flex-direction: row;
  align-items: center; 
  }

`;


function Answers({ body }) {
  return (
    <Content>
      <div>
        <div>
        </div>
      </div>
      <div className="row">
        <div>
          <div className="answer-section">
            <Vote />
            <div className="question-section">
              <p>내용: {body}</p>
              <UserInfo />
            </div>
          </div>
        </div>
      </div>
    </Content>
  );
}

export default Answers;
