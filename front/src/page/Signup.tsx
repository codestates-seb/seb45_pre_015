import React from 'react';
import Signicon from '../component/Signicon';
import SignupInfo from '../component/SignupInfo';

const Signup: React.FC = () => {
  return (
    <div className="flex items-center justify-center h-screen bg-gray-100">
      <div className="flex w-full max-w-lg p-8 bg-white rounded-lg shadow">
        <div className="flex items-center justify-center mb-6">
          <Signicon />
        </div>
        <SignupInfo />
      </div>
    </div>
  );
};

export default Signup;