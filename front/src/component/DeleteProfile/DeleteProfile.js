import styled from "styled-components"

const Section = styled.section`
  padding: 1.875rem;
  text-align: center;

  .profile_msg{
    font-size: 1.75rem;
    font-weight: 700;
    margin: 0 0 2.8125rem;
  }
  .profile_btn{
    margin: 1.25rem 0 0;
    padding: .9375rem 1.25rem;
    border-radius: 10px;
    border: 1px solid #fff;
    background-color: #d0393e;
    color: #fff;
  }
`

function Delete () {
  return (
    <Section>
      <h2 className="profile_msg">프로필을 삭제하시겠습니까?</h2>
      <button className="profile_btn">프로필 삭제</button>
    </Section>
  )
}

export default Delete