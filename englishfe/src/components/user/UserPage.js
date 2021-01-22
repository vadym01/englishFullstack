import React from 'react';
import UserLearningList from './UserLearningList';

const UserPage = () => {
  return (
    <div>
      <UserLearningList showLearned={true} />
    </div>
  );
};

export default UserPage;
