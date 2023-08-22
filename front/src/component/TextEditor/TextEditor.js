import { Editor } from '@toast-ui/react-editor';
import { useRef } from 'react';
import '@toast-ui/editor/dist/toastui-editor.css';
import '@toast-ui/editor/dist/i18n/ko-kr';
import styled from "styled-components";

const Section = styled.section`
  padding: 1.25rem;

  .form_input_wrap{
    display: flex;
    flex-direction: column;
    gap: 1.5625rem;
  }
  .form_input_name{
    display: flex;
    flex-direction: column;
    gap: .9375rem;
  }
  .form_input_name label{
    font-size: 1.375rem;
    font-weight: 700;
  }
  .form_input_name input{
    width: 31.25rem;
    border: 1px solid #e8e8e8;
    padding: .3125rem .625rem;
    border-radius: 10px;
  }
  .editor_wrap h4{
    font-size: 1.375rem;
    font-weight: 700;
    margin: 0 0 1.25rem;
  }
  .submit_btn{
    font-size: 1.125rem;
    width: 7.5rem;
    padding: .625rem 1.25rem;
    background-color: #f48225;
    color: #fff;
    border: 1px solid #fff;
    border-radius: 20px;
    margin: 1.25rem 0 0;
  }
`


function Myeditor(){
  const editorRef = useRef();
  
  const handleRegisterBtn = () => {
    const data = editorRef.current.getInstance().getHTML();
    console.log(data);
  }
  return (
    <Section className='form_wrap'>
      <form action='' method='get' className='form' >
        <div className='form_input_wrap'>
          <div className='form_input_name'>
            <label htmlFor='name'>Display name</label>
            <input type='text' name='name' id='name' required />
          </div>
          <div className='editor_wrap'>
            <h4>Title</h4>
            <Editor
              ref={editorRef}
              initialValue="텍스트를 입력하세요"
              previewStyle="vertical"
              height="300px"
              width="300px"
              initialEditType="wysiwyg"
              useCommandShortcut={false}
              language='ko-KR'
            />
          </div>
        </div>
      <button className='submit_btn' onClick={handleRegisterBtn}>등록</button>
      </form>
    </Section>
);
}

export default Myeditor