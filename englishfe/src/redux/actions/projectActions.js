import axios from 'axios';
import {
  GET_ERRORS,
  CREATE_FOLDER,
  GET_ALL_FOLDERS,
  SET_FAV_FOLDER,
  DELETE_FOLDER,
} from './types';

export const getAllFolders = () => async (dispatch) => {
  try {
    const res = await axios.get('/user/folderManage');
    dispatch({
      type: GET_ALL_FOLDERS,
      payload: res.data,
    });
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data,
    });
  }
};

export const createFolder = (folderName) => async (dispatch) => {
  try {
    const res = await axios.post('/admin/folderManage', folderName);
    dispatch({
      type: CREATE_FOLDER,
      payload: res.data,
    });
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err,
    });
  }
};

export const setFavFolder = (selected, folderId) => async (dispatch) => {
  try {
    selected
      ? await axios.delete(`/user/markFavFolder/${folderId}`)
      : await axios.post(`/user/markFavFolder/${folderId}`);

    dispatch({
      type: SET_FAV_FOLDER,
      payload: folderId,
    });
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data,
    });
  }
};

export const deleteFolder = (folderName, imageFileName, index) => async (
  dispatch
) => {
  try {
    await axios.delete(`/admin/folderManage/${folderName}/${imageFileName}`);
    dispatch({
      type: DELETE_FOLDER,
      payload: index,
    });
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data,
    });
  }
};
