import React, { useState, useEffect, useRef } from 'react';
import { useNavigate, Link } from 'react-router-dom';
//아이콘등 추후 추가바람

const Header: React.FC = () => {
    const [isLogin, setIsLogin] = useState<boolean>(false);
    const [userProfileImage, setUserProfileImage] = useState<string>('');
    const [userProfileImageLink, setUserProfileImageLink] = useState<string>('');
    const [searchText, setSearchText] = useState<string>('');
    const search = useRef<HTMLInputElement | null>(null);
    const navigator = useNavigate();
  
    useEffect(() => {
      checkLoginState();
    });
  
    const handleSearch = (e:any) => {
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
  
    const checkLoginState = () => {
      const currentPath = window.location.pathname;
      const isLoginPath = currentPath === '/login' || currentPath === '/signup';
      
      if (sessionStorage.getItem('access_token') && !isLoginPath) {
        setIsLogin(true);
        getUserProfile();
        setUserProfileImageLink(`/mypage/${sessionStorage.getItem('accountId')}`);
      } else {
        setIsLogin(false);
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
        const data = await fetchUserInfo(); //나중에 받아와야되는거
        setUserProfileImage(data.profile);
  
        sessionStorage.setItem('userEmail', data.email);
        sessionStorage.setItem('accountId', data.accountId);
      } catch (error) {
        
      }
    };
  
    const LoginGNB: React.FC = () => {
      return (
        <div className="flex items-center">
          <button
            onClick={onLogoutClick}
            className="px-3 py-1 mx-1 text-gray hover:bg-[#eee] rounded"
          >
            <Link to="/">Logout</Link>
          </button>
          <div className="items-center p-2 hover:bg-soGray-light">
            <a href={userProfileImageLink}>
              <img
                src={userProfileImage}
                alt="userProfile"
                width={25}
                height={25}
              ></img>
            </a>
          </div>
        </div>
      );
    };
  
    const LogoutGNB: React.FC = () => {
      return (
        <>
          <button className="px-3 py-1 mx-2 border rounded text-blue hover:bg-buttonSecondaryHover bg-buttonSecondary border-secondary-200">
            <Link to="/login">Log in</Link>
          </button>
          <button className="px-2 py-1 mx-1 text-white border rounded hover:bg-buttonPrimaryHover bg-buttonPrimary border-secondary-300">
            <Link to="/signup">Sign up</Link>
          </button>
        </>
      );
    };
  
    const onClickRemove = () => {
      sessionStorage.removeItem('searchText');
    };
  
    const [isFocus, setIsFocus] = useState<boolean>(false);
  
    return (
      <div className="sticky top-0 z-20 flex-col w-full drop-shadow h-[60px] flex-nowrap">
        <div className="h-1 bg-primary-300"></div>
        <div className="flex justify-center px-2 py-3 bg-soGray-headerbg">
          <div className="items-center mx-2 my-1">
            <Link to="/" onClick={onClickRemove}>
              
            </Link>
          </div>
          <div className="flex items-center px-2 py-1 mx-2 mr-10 bg-white border rounded-md grow border-soGray-light focus:ring-secondary-300">
            <div className="flex mx-2 my-1 text-soGray-icon">
              
            </div>
            <input
              type="text"
              value={searchText}
              className="w-[calc(100%-40px)] focus:outline-none focus-visible:outline-none"
              placeholder="Search..."
              onChange={handleSearch}
              onFocus={onChangeSearch}
              onBlur={onChangeSearch}
              ref={search}
            />
          </div>
          <div>{isLogin ? <LoginGNB /> : <LogoutGNB />}</div>
        </div>
      </div>
    );
  };
  
  export default Header;