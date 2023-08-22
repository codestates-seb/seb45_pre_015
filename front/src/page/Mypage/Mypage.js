import React , { useState } from "react"
import Activity from '../../component/Mypage/Activity'
import Settings from '../../component/Mypage/Settings'
import { Section } from './Mypage.styled'


export default function Mypage() {
  const [addClass, setAddClass] = useState(true);
  const [username, setUsername] = useState(sessionStorage.getItem('username'));
  
  const onChangeUsername = (newUsername) => {
    setUsername(newUsername);
  }

  const LogoutClick = () => {
    sessionStorage.clear();
    document.location.href = '/'
  }

  return (
    <Section>
      <div className="profile">
        <article className="profile_img">
          <span>이미지</span>
        </article>
        <div className="profile_info">
          <h2 className="profile_user_name">{username}</h2>
          <p className="profile_user_title"></p>
          <div className="profile_user_state">
            <button onClick={LogoutClick} className="logout-btn">Logout</button>
          </div>
        </div>
      </div>
      <div className="mypage_status">
        <div className="status_button">
          <button onClick={() => setAddClass(true)} className={addClass ? "active" : ""}>Activity</button>
          <button onClick={() => setAddClass(false)} className={addClass ? "" : "active"}>Settings</button>
        </div>
        <div className="state_main_wrap">
          { addClass ? <Activity /> : <Settings  username={username} onChangeUsername={onChangeUsername}/>}
        </div>
      </div>
    </Section>
  )
}