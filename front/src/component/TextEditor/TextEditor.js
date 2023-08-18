import { Editor } from '@toast-ui/react-editor';
import { useRef } from 'react';
import '@toast-ui/editor/dist/toastui-editor.css';
import '@toast-ui/editor/dist/i18n/ko-kr';


function Myeditor(){
  const editorRef = useRef();
  
  const handleRegisterBtn = () => {
    const data = editorRef.current.getInstance().getHTML();
    console.log(data);
  }
  return (
    <section className='form_wrap'>
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
              height="600px"
              initialEditType="wysiwyg"
              useCommandShortcut={false}
              language='ko-KR'
            />
          </div>
        </div>
      <button onClick={handleRegisterBtn}>등록</button>
      </form>
    </section>
);
}

export default Myeditor