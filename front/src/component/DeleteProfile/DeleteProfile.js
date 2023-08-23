import React from "react"
import axios from "axios"
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
    background-color: #de7477;
    color: #fff;
    transition: .3;
  }
  .profile_btn:hover{
    background-color: #d0393e;
  }
`

function Delete () {
  const handleDelete = async () => {
    try {
      await axios.delete(`https://659a-116-126-166-12.ngrok-free.app/members/{member-id}`);
      window.location.href = '/';

    } catch (error) {
      console.error("서버에 DELETE 요청을 보낼 수 없습니다:", error);
      // 오류 처리
    }
  };

  return (
    <Section>
      <h2 className="profile_msg">프로필을 삭제하시겠습니까?</h2>
      <button className="profile_btn" onClick={handleDelete}>프로필 삭제</button>
    </Section>
  )
}

export default Delete