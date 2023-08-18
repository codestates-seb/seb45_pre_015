import styled from 'styled-components';

const ButtonAndUser = styled.div`
  .space {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin: 16px 0;
    padding-top: 10px;
    width: 550px;
  }
  .styled-Button {
  font-size: 13px;
  color: hsl(210,8%,45%);
  cursor: pointer;
  }

  .edited-date {
    font-size: 12px;
    color: hsl(206,100%,40%);
    padding-top: 7px;
  }

  .user-infomation {
  display: flex;
  flex-direction: column;
  width: 100%;
  padding: 7px 6px 7px 7px;
  border-radius: 3px;
  background-color: rgb(217, 234, 247);
  color: hsl(210,8%,45%);
  font-size: 12px;
  }

  .user-info {
  display: flex;
  align-items: center;
  justify-content: center;
  color: hsl(206,100%,40%);
  }

`

function QuestionUsers({ name }) {

  const currentDate = new Date().toLocaleDateString();

  return (
    <ButtonAndUser>
        <div className='space'>
          <div className='styled-Button'>
            Share Edit Follow
          </div> 
           <div className='edited-date'>edited {currentDate}</div>
          <div className='asked-users'>
              <div className='user-infomation'>
                <div>asked {currentDate}</div>
                <div className='user-info'>
                  <div>유저사진</div>
                  <div>유저이름: { name }</div>
                </div>
              </div>
          </div>
        </div>
    </ButtonAndUser>
  );
}

export default QuestionUsers;
