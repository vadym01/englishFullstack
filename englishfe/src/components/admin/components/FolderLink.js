import React from 'react';

const FolderLink = ({ url }) => {
  return (
    <Link to={url}>
      <AiFillFolderOpen size={30} className="icon" />
      <p className="folderInfo">{folder.folderName}</p>
      <span>{folder.description}</span>
    </Link>
  );
};

export default FolderLink;
