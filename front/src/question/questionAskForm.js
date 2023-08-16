import React from 'react';
import styled from 'styled-components';

const AskFormAll = styled.div`
  display: flex;
  .ask-form {
    border: 1px solid hsl(210, 8%, 90%);
    border-radius: 5px;
    padding: 24px;
    display: flex;
    width: 100%;
    margin-bottom: 24px;
  }
  .ask-box {
    display: flex;
    flex-direction: column;
    width: 100%;
  }
  .title {
    font-weight: 600;
    font-size: 15px;
    padding-top: 5px;
    cursor: pointer;
  }
  .notification {
    font-weight: normal;
    font-size: 13px;
  }
  label {
    display: flex;
  }
  input {
    width: 100%;
    margin-top: 16px;
    padding: 8px 9px;
    background-color: #fff;
    color: hsl(210, 8%, 5%);
    font-size: 13px;
    border: 1px solid hsl(210, 8%, 80%);
    border-radius: 3px;
    outline: none;

    &:focus {
      box-shadow: 0px 0px 0px 4px hsl(206, 96%, 90%);
      border-color: hsl(206, 85%, 57.5%);
    }
  }
`;

function QuestionAskForm() {
  return (
    <AskFormAll>
      <div className='ask-form'>
        <div className='ask-box'>
          <div>
            <label className='title' htmlFor='title-input'>
              Title
            </label>
            <label className='notification' htmlFor='title-input'>
              Be specific and imagine you’re asking a question to another person.
            </label>
          </div>
          <div>
            <input
              type='text'
              placeholder='e.g. Is there an R function for finding the index of an element in a vector?'
              id='title-input'
            />
          </div>
        </div>
      </div>
    </AskFormAll>
  );
}

export default QuestionAskForm;
