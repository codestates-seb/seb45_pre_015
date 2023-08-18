import React from "react"
import axios from "axios"
import Activity from '../../component/Mypage/Activity'
import Settings from '../../component/Mypage/Settings'
import { useState , useEffect } from "react"
import { Section } from './Mypage.styled'


export default function Mypage() {
  
  const [userInfo, setUserInfo] = useState(null);
  const [addClass, setAddClass] = useState(true);

  const dataHanlder = () => {
    return axios
    .get("http://localhost:8080//members/mypage")
    .then((res) => {
      setUserInfo(res.data)
    })
    .catch((err) => {
      if(err.response.status === 401){
        console.log(err.response.data);
      }
    })
  }

  useEffect(() => {
    dataHanlder()
  }, []);

  return (
    <Section>
      <div className="profile">
        <article className="profile_img">
          <span>이미지</span>
        </article>
        <div className="profile_info">
          <h2 className="profile_user_name">유저네임</h2>
          <div className="profile_user_state">
            <button className="logout-btn">Logout</button>
          </div>
        </div>ㄴ
      </div>
      <div className="mypage_status">
        <div className="status_button">
          <button onClick={() => setAddClass(true)} className={addClass ? "active" : ""}>Activity</button>
          <button onClick={() => setAddClass(false)} className={addClass ? "" : "active"}>Settings</button>
        </div>
        <div className="state_main_wrap">
          { addClass ? <Activity userInfo={userInfo}/> : <Settings />}
        </div>
      </div>
    </Section>
  )
}