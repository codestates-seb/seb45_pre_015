import React from 'react';
import {Link} from "react-router-dom";

type ExLoginButtonsProps = {
  onSubmit: () => void;
}

const ExLoginButtons: React.FC<ExLoginButtonsProps> = ({ onSubmit }) => {

  const handleGoogleLogin = () => {
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