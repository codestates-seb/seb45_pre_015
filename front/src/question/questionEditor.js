import styled from 'styled-components';
import { Link } from 'react-router-dom';

const QuestionsEditor = styled.div`
    border: 1px solid hsl(210,8%,90%) ;
    border-radius: 5px ;
    padding: 24px;
    display: flex;
    width: 100%;
    .ask-title {
        display: flex;
        flex-direction: column ;
    }
    .title {
        font-weight: 600;
        font-size: 15px;
        padding-top: 5px;
        cursor: pointer ;
    }
    .notification {
        font-weight: normal;
            font-size: 13px;
    }
    input {
        width: 100%;
        height: 200px;
        margin-top: 16px;
        padding: 8px 9px;
        background-color: #fff;
        color: hsl(210, 8%, 5%);
        font-size: 13px;
        border: 1px solid hsl(210,8%,80%);
        border-radius: 3px;
        outline: none;

  &:focus {
    box-shadow: 0px 0px 0px 4px hsl(206,96%,90%);
    border-color: hsl(206,85%,57.5%);;
  }
    }
  
    .post-button {
  background-color: rgb(10, 149, 255);
  color: white;
  border: none;
  border-radius: 0.5rem;
  padding: 5px;
  cursor: pointer;
  text-decoration: none;
  width: 125px;
  height: 40px;
  font-size: 13px;
  margin-top: 20px ;

  &:hover {
    background-color:hsl(206,100%,40%);
  }

  &:focus {
    box-shadow: 0px 0px 0px 4px hsl(206,96%,90%);
    border-color: hsl(206,85%,57.5%);
  }
}  
      `;

function QuestionEditor() {
    return (
        <QuestionsEditor>
            <div>
                <div className="ask-title">
                      <label className='title' htmlFor="ask-body">
                        What are the details of your problem?
                      </label>
                      <label className='notification' htmlFor="ask-body">
                        Introduce the problem and expand on what you put in the title.
                        Minimum 20 characters.
                      </label>
                    </div>
                    <div className="ask-form-body" id="ask-body">
                    <input/>
                  </div>
                  <Link to="/"><button className="post-button">Post Your Question</button></Link>
            </div>
        </QuestionsEditor>
    )
}

export default QuestionEditor;