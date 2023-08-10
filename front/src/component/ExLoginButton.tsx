import React from 'react';

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
        Login with Google
      </button>
    </div>
  );
};

export default ExLoginButtons;