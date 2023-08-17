import React from 'react';
import { Link } from 'react-router-dom';
import { fetchLogin } from '../util/fetchlogin';

type ExLoginButtonsProps = {
  onSubmit: () => void;
}

const ExLoginButtons: React.FC<ExLoginButtonsProps> = ({ onSubmit }) => {

  const handleGoogleLogin = async () => {
    try {
      const loginData = {
        email: 'user@example.com',
        password: 'password123'
      };
      const response = await fetchLogin(JSON.stringify(loginData));
      onSubmit();
    } catch (error) {
      console.error('Error during Google login:', error);
    }
  };

  return (
    <div>
      <button className='flex--item s-btn s-btn__icon s-btn__google bar-md ba bc-black-100' onClick={handleGoogleLogin}>
        <p className=''>로고</p>
        <Link to={'http://localhost:8080/oauth2/authorization/google'}>Login with Google</Link>
      </button>
    </div>
  );
};

export default ExLoginButtons;