import React, { useState, useEffect, useRef } from 'react';
import {useNavigate, Link, redirect} from 'react-router-dom';
import { fetchUserInfo, checkIfLogined } from '../util/fetchlogin';
import './header.css'
import IconLogo from '../images/logo-stackoverflow.svg';

const Header: React.FC = () => {
  const [isLogin, setIsLogin] = useState<boolean>(false);
  const [userProfileImage, setUserProfileImage] = useState<string>('');
  const [userProfileImageLink, setUserProfileImageLink] = useState<string>('');
  const [searchText, setSearchText] = useState<string>('');
  const search = useRef<HTMLInputElement | null>(null);
  const [menu, setMenu] = useState<boolean>(false);

  const navigator = useNavigate();

  useEffect(() => {
    checkLoginState();
  }, []);

  const handleSearch = (e: any) => {
    const newValue = (e.target as HTMLInputElement).value;
    setSearchText(newValue);
    sessionStorage.setItem('searchText', newValue);

    if (e.key === 'Enter' && newValue) {
      if (window.location.pathname === '/question') {
        window.location.reload();
      } else {
        navigator('/question');
      }
      setSearchText('');
    }
  };

  const checkLoginState = async () => {
    try {
      await checkIfLogined();
      const currentPath = window.location.pathname;
      const isLoginPath = currentPath === '/login' || currentPath === '/signup';

      if (sessionStorage.getItem('access_token') && !isLoginPath) {
        setIsLogin(true);
        getUserProfile();
        setUserProfileImageLink(`/mypage/${sessionStorage.getItem('accountId')}`);
      } else {
        setIsLogin(false);
      }
    } catch (error) {
      console.error('Error', error);
    }
  };

  const onLogoutClick = () => {
    sessionStorage.clear();
    setIsLogin(false);
  };

  const onChangeSearch = () => {
    setIsFocus(true);
  };

  const getUserProfile = async () => {
    try {
      const data = await fetchUserInfo(); // 나중에 받아와야되는거
      setUserProfileImage(data.profilePic);

      sessionStorage.setItem('userEmail', data.email);
      sessionStorage.setItem('accountId', data.accountId);
      sessionStorage.setItem('profilePic', data.profilePic);
    } catch (error) {
      console.error('Error while getting user profile:', error);
    }
  };

  const LoginGNB: React.FC = () => {
    return (
      <>
        <button
          onClick={onLogoutClick}
          className="logout_btn"
        >
        Logout
        </button>
        <Link to='/mypage' className='mypage_btn'>
          <img
            className='user-img'
            src={userProfileImage}
            alt="userProfile"
            width={25}
            height={25}
          ></img>
        </Link>
      </>
    );
  };

  const LogoutGNB: React.FC = () => {
    return (
      <>
        <Link to="/login">Log in</Link>
        <Link to="/signup" className='signup_btn'>Sign up</Link>
      </>
    );
  };

  const onClickRemove = () => {
    sessionStorage.removeItem('searchText');
  };

  const [isFocus, setIsFocus] = useState<boolean>(false);

  return (
    <header>        
      <div className="top_bg"></div>
      <section className= "hd_wrap">
        <Link to="/" onClick={onClickRemove}>
          <img src={IconLogo} alt="Icon" />            
        </Link>
        <div className="hd_input">
          <input
            type="text"
            value={searchText}
            className="search-bar"
            placeholder="Search..."
            onChange={handleSearch}
            onFocus={onChangeSearch}
            onBlur={onChangeSearch}
            ref={search}
          />
        </div>
        <div className='hd_user'>{isLogin ? <LoginGNB /> : <LogoutGNB />}</div>
      </section>
  </header>
  );
};

export default Header;