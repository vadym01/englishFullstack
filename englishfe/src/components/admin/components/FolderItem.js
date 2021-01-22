import React, { useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { Link } from 'react-router-dom';
import '../css/folderItem.css';
import { AiFillStar } from 'react-icons/ai';
import { AiOutlineStar, AiOutlineDelete } from 'react-icons/ai';
import {
  setFavFolder,
  getAllFolders,
  deleteFolder,
} from '../../../redux/actions/projectActions';
import './folderItem.css';

const FolderItem = () => {
  const dispatch = useDispatch();
  const folderItems = useSelector((state) => state.project.folderItems);

  const { roles } = useSelector((state) => state.user.user);
  const roleAccess =
    roles[0] === 'ROLE_ADMIN' ? '/admin/fileManage/' : '/user/audioDictation/';
  useEffect(() => {
    dispatch(getAllFolders());
  }, []);

  const onClickSelected = (selected, folderId) => {
    dispatch(setFavFolder(selected, folderId));
  };

  const deleteFolderClickHandler = (folderName, imageFileName, index) => {
    dispatch(deleteFolder(folderName, imageFileName, index));
  };

  return (
    <div className="wrapper">
      {folderItems.length === 0
        ? ''
        : folderItems.map((folder, index) => (
            <div
              key={folder.id}
              className="folderCardsContainer"
              style={{
                backgroundImage: `url( ${folder.imageForFolder})`,
              }}
              // onMouseOver={onMouseOverHandler}
              // onMouseLeave={onMouseOverHandler}
            >
              <div className="actionButtonsContainer">
                {folder.favoriteSelected ? (
                  <AiFillStar
                    onClick={() => onClickSelected(true, folder.id)}
                    className="favIcon"
                  />
                ) : (
                  <AiOutlineStar
                    onClick={() => onClickSelected(false, folder.id)}
                    className="favIcon"
                  />
                )}

                {roles[0] === 'ROLE_ADMIN' && (
                  <AiOutlineDelete
                    onClick={() =>
                      deleteFolderClickHandler(
                        folder.folderName,
                        folder.imageFileName,
                        index
                      )
                    }
                    className="deleteIcon"
                  >
                    Delete folder
                  </AiOutlineDelete>
                )}
              </div>

              <Link to={roleAccess + folder.folderName}>
                <div className="cardContainer">
                  <div className="cardName">{folder.folderName}</div>
                  <div className="textDescription">{folder.description}</div>
                </div>
              </Link>
            </div>
          ))}
    </div>
  );
};

export default FolderItem;

{
  /* <Link to={roleAccess + folder.folderName}>
            <AiFillFolderOpen size={30} className="icon" />
            <p className="folderInfo">{folder.folderName}</p>
            <span>{folder.description}</span>
          </Link> */
}
