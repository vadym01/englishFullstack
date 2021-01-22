import axios from 'axios';
import {
  GET_ERRORS,
  GET_ALL_FROM_USER_LEARNING_COLLECTION,
  SET_CUSTOM_LEARNING_UNITS,
  SET_FAV_LEARNING_UNIT,
  SELECT_FOR_REPEAT_ITEM,
  DELETE_FROM_USER_LEARNING_LIST,
} from '../actions/types';

export const saveToUserRepeatCollection = (arr, history) => async (
  dispatch
) => {
  try {
    const res = await axios.post('/user/saveToUserRepeatCollection', arr);

    if (res.status === 200) {
      history.push('/userLearningList');
    }
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data,
    });
  }
};

export const getAllFromUserLearningCollection = () => async (dispatch) => {
  try {
    const res = await axios.get('/user/getRepeatCollection');
    dispatch({
      type: GET_ALL_FROM_USER_LEARNING_COLLECTION,
      payload: res.data,
    });
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err,
    });
  }
};

export const setCustomLearningUnits = (unitsArr) => (dispatch) => {
  try {
    dispatch({
      type: SET_CUSTOM_LEARNING_UNITS,
      payload: unitsArr,
    });
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data,
    });
  }
};

export const setFavLearningUnit = (id) => async (dispatch) => {
  try {
    const res = await axios.patch(`/user/setFavLearningUnit/${id}`);

    dispatch({
      type: SET_FAV_LEARNING_UNIT,
      payload: res.data.id,
    });
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data,
    });
  }
};

export const selectForRepeatItem = (index) => (dispatch) => {
  dispatch({
    type: SELECT_FOR_REPEAT_ITEM,
    payload: index,
  });
};

export const deleteFromUserLearningList = (id, index) => (dispatch) => {
  try {
    axios.delete(`/user/deleteFromLearningList/${id}`);
    dispatch({
      type: DELETE_FROM_USER_LEARNING_LIST,
      payload: index,
    });
  } catch (error) {
    dispatch({
      type: GET_ERRORS,
      payload: error.response.data,
    });
  }
};
