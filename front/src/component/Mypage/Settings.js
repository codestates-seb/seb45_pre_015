import styled from "styled-components";
import Myeditor from "../TextEditor/TextEditor";
import Delete from "../DeleteProfile/DeleteProfile";
import { useState } from "react";

const Section = styled.section`
  display: flex;
  margin: 1.25rem 0;
`

const Cate = styled.article`
  width: 100%;
  display: flex;
  gap: 1.875rem;
  
  .btn_wrap{
    display: flex;
    flex-direction: column;
    gap: .9375rem;
  }
  .edit-btn,
  .delete-btn{
    width: 7.5rem;
    padding: .3125rem .625rem;
    border-radius: 15px;
    text-align: left;
  }
  .edit-btn.active,
  .delete-btn.active{
    background-color: #f1f2f3;
  }

  .content_wrap{
    width: calc(100% - 7.5rem);
  }
`

const Settings = () => {
  const [addClass, setAddClass] = useState(true);
  
  return(
    <Section>
      <Cate>
        <div className="btn_wrap">
          <button onClick={() => setAddClass(true)} className={addClass ? "edit-btn active" : "edit-btn"}>Edit Profile</button>
          <button onClick={() => setAddClass(false)} className={addClass ? "delete-btn" : "delete-btn active"}>Delete Profile</button>
        </div>
        <div className="content_wrap">
          {addClass ? <Myeditor /> : <Delete />}
        </div>
      </Cate>
    </Section>
  )
}

export default Settings;