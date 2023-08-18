import styled from "styled-components";
import Myeditor from "../TextEditor/TextEditor";

const Section = styled.section`
  display: flex;
  margin: 1.25rem 0;
`

const Cate = styled.article`
  display: flex;
  flex-direction: column;
  gap: .9375rem;

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
`

const Settings = () => {
  return(
    <Section>
      <Cate>
        <button className="edit-btn">Edit Profile</button>
        <button className="delete-btn">Delete Profile</button>
        <Myeditor />
      </Cate>
    </Section>
  )
}

export default Settings;