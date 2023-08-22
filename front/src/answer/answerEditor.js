import { Editor } from '@toast-ui/react-editor';
import { useRef } from 'react';
import '@toast-ui/editor/dist/toastui-editor.css';
import '@toast-ui/editor/dist/i18n/ko-kr';


function AnswerEditor(){
  const editorRef = useRef();

  return (
    <section className='form_wrap'>
      <form action='' method='get' className='form' >
        <div className='form_input_wrap'>
          <div className='form_input_name'>
          </div>
          <div className='editor_wrap'>
            <Editor
              ref={editorRef}
              previewStyle="vertical"
              height="300px"
              initialEditType="wysiwyg"
              useCommandShortcut={false}
              language='ko-KR'
            />
          </div>
        </div>
      </form>
    </section>
);
}

export default AnswerEditor