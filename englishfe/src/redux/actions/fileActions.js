import axios from 'axios';
import {
  GET_ERRORS,
  GET_ALL_FROM_FOLDER,
  DELETE_FILE,
  CREATE_PROJECT,
  CHANGE_TEXT_AUDIO_SENTENCE,
  CHANGE_TEXT_AUDIO_LOAD_THROUGH,
  GET_ALL_FROM_GOLDER_USER_ACCESS,
  START_REPEAT_FROM_SELECTED_FOR_REPEAT,
  REPEAT_ALL_FROM_USER_REPEAT_LIST,
  REPEAT_FAVORITE_USER_WORDS,
  CHANGE_DISPLAY_STATUS,
} from './types';

export const createProject = (project) => async (dispatch) => {
  try {
    const res = await axios.post('/admin/fileManage/addFile', project);
    dispatch({
      type: CREATE_PROJECT,
      payload: res.data,
    });
    console.log(res.data.textAudio.sentence);
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err,
    });
  }
};

// export const loadImageFolder = (imageFile) => async (dispatch) => {
//   try {
//     const res = await axios.post(
//       '/admin/loadImageFolder',
//       imageFile
//     );
//     dispatch({
//       type: LOAD_IMAGE_FOLDER,
//       payload: res.data,
//     });
//   } catch (err) {
//     dispatch({
//       type: GET_ERRORS,
//       payload: err,
//     });
//   }
// };

export const deleteFile = (folderName, fileName, index) => async (dispatch) => {
  try {
    await axios.delete(`/admin/fileManage/${folderName}/${fileName}`);
    dispatch({
      type: DELETE_FILE,
      payload: index,
    });
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data,
    });
  }
};

export const getAllFromFolder = (folderName) => (dispatch) => {
  try {
    axios.get(`/admin/fileManage/${folderName}`).then((res) => {
      dispatch({
        type: GET_ALL_FROM_FOLDER,
        payload: res.data,
      });
    });
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data,
    });
  }
};

export const getAllFromFolderUserAccess = (folderName) => (dispatch) => {
  try {
    // const res = await axios.get(
    //   `/user/getAllAudio/${folderName}`
    // );
    // dispatch({
    //   type: GET_ALL_FROM_GOLDER_USER_ACCESS,
    //   payload: res.data,
    // });

    return axios.get(`/user/getAllAudio/${folderName}`).then((res) => {
      dispatch({
        type: GET_ALL_FROM_GOLDER_USER_ACCESS,
        payload: res.data,
      });
    });
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data,
    });
  }
};

export const changeTextAudioSentence = (obj) => async (dispatch) => {
  try {
    const res = await axios.put('/admin/fileManage/newTxtAudioSentence', obj);
    dispatch({
      type: CHANGE_TEXT_AUDIO_SENTENCE,
      payload: res.data,
    });
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data,
    });
  }
};

export const changeStartNewAudioLoadThrough = (obj) => async (dispatch) => {
  try {
    const res = await axios.put('/admin/fileManage/newAudioLoadThrough', obj);
    dispatch({
      type: CHANGE_TEXT_AUDIO_LOAD_THROUGH,
      payload: res.data,
    });
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data,
    });
  }
};

export const hideShow = (audioId) => async (dispatch) => {
  await dispatch({
    type: CHANGE_DISPLAY_STATUS,
    payload: audioId,
  });
};

export const startRepeatFromSelectedForRepeat = (arr, history) => (
  dispatch
) => {
  dispatch({
    type: START_REPEAT_FROM_SELECTED_FOR_REPEAT,
    payload: arr,
  });
  history.push('/user/audioDiction');
};

export const repeatAllFromUserRepeatList = (arr, history) => (dispatch) => {
  dispatch({
    type: REPEAT_ALL_FROM_USER_REPEAT_LIST,
    payload: arr,
  });
  history.push('/user/audioDiction');
};

export const repeatFavoriteUserWords = (arr, history) => (dispatch) => {
  dispatch({
    type: REPEAT_FAVORITE_USER_WORDS,
    payload: arr,
  });
  history.push('user/audioDiction');
};
