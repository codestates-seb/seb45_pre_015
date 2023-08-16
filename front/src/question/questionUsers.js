import styled from 'styled-components';

const ButtonAndUser = styled.div`
  display: flex;
  justify-content: space-between;

  .styled-Button {
  background: none;
  border: none;
  font-size: 16px;
  margin-right: 10px;
  color: rgb(106, 115, 124);
  cursor: pointer;
  }

  .user-infomation {
  display: flex;
  flex-direction: column;
  padding: 5px;
  }

  .user-inFo {
  display: flex;
  align-items: center;
  }

`

function QuestionUsers() {

  const currentDate = new Date().toLocaleDateString();

  return (
    <ButtonAndUser>
        <div className='styled-Button'>Share Edit Follow</div> 
         <div>edited</div>
        <div className='asked-users'>
            <div className='user-infomation'>
              <div>{currentDate}</div>
              <div className='user-inFo'>
                <div>유저사진</div>
                <div>유저이름</div>
              </div>
            </div>
        </div>
    </ButtonAndUser>
  );
}

export default QuestionUsers;
