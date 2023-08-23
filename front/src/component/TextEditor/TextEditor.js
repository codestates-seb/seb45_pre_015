import { Editor } from '@toast-ui/react-editor';
import { useState ,useRef } from 'react';
import '@toast-ui/editor/dist/toastui-editor.css';
import '@toast-ui/editor/dist/i18n/ko-kr';
import styled from "styled-components";
import axios from 'axios';

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
    background-color: #f49335;
    color: #fff;
    border: 1px solid #fff;
    border-radius: 20px;
    margin: 1.25rem 0 0;
    transition: 0.3s;
  }
  .submit_btn:hover{
    background-color: #f48225;
  }
`


function Myeditor( {username, onChangeUsername } ){
  const editorRef = useRef();
  const inputRef = useRef();
  const [newDisplayName, setNewDisplayName] = useState(username);

  const handleRegisterBtn = () => {
    // const data = editorRef.current.getInstance().getHTML();
    // console.log(data);

    const newUsername = inputRef.current.value;
    onChangeUsername(newUsername);
    console.log(onChangeUsername(newUsername))
  }

  const onInputChange = (e) => {
    setNewDisplayName(e.target.value);
  }

  const onSubmit = async (e) => {
    e.preventDefault();

    // // try{
    // //   await axios.patch("http://ec2-3-35-10-64.ap-northeast-2.compute.amazonaws.com:8080/members/{member-id}" , {
    // //     method: 'POST',
    // //     headers: {
    // //       'Content-Type': 'application/json',
    // //       'ngrok-skip-browser-warning': '69420',
    // //       Authorization: "Bearer " + sessionStorage.getItem('access_token') ?? '',
    // //       Refresh: "Bearer " + sessionStorage.getItem('refresh_token') ?? '',
    // //     },
    // //     body: {
    // //       name : newDisplayName
    // //     },
    // //   }, {withCredentials: true,})
    // //   onChangeUsername(newDisplayName); // 변경된 username을 상위 컴포넌트로 전달
    // // } catch (error) {
    // //   console.error("서버에 요청을 보낼 수 없습니다:", error)
    // // }
    

    try {
      const response = await axios.patch(`http://ec2-3-35-10-64.ap-northeast-2.compute.amazonaws.com:8080/members/{member-id}`, {
        name: newDisplayName
      });

      // 서버가 성공적으로 처리한 경우, 상위 컴포넌트로 변경된 username을 전달
      if (response.status === 200) {
        onChangeUsername(newDisplayName);
      }
    } catch (error) {
      console.error("서버에 요청을 보낼 수 없습니다:", error);
      // 오류 처리
    }
  }
  return (
    <Section className='form_wrap'>
      <form onSubmit={onSubmit} className='form' >
        <div className='form_input_wrap'>
          <div className='form_input_name'>
            <label htmlFor='name'>Display name</label>
            <input type='text' name='name' id='name' required onChange={onInputChange}  defaultValue={username} ref={inputRef}/>
          </div>
          {/* <div className='editor_wrap'>
            <h4>Title</h4>
            <Editor
              ref={editorRef}
              initialValue="텍스트를 입력하세요"
              previewStyle="vertical"
              height="300px"
              initialEditType="wysiwyg"
              useCommandShortcut={false}
              language='ko-KR'
            />
          </div> */}
        </div>
      <button className='submit_btn' onClick={handleRegisterBtn}>등록</button>
      </form>
    </Section>
);
}

export default Myeditor