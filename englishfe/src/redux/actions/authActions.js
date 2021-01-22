import axios from 'axios';
import { AUTH_USER, GET_ERRORS, LOG_OUT } from './types';
import storeJwtToken from '../../security/jwtSecurity';
import jwt_decode from 'jwt-decode';

export const loginUser = (obj, history) => async (dispatch) => {
  //int thi end add login page through history
  try {
    const res = await axios.post('/auth/login', obj);
    const { token } = res.data;
    localStorage.setItem('token', token);
    storeJwtToken(token);
    const decoded = jwt_decode(token);
    dispatch({
      type: AUTH_USER,
      payload: decoded,
    });
    history.push('/userPage');
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data,
    });
  }
};

export const logOutUser = () => (dispatch) => {
  localStorage.removeItem('token');
  storeJwtToken(false);
  dispatch({
    type: LOG_OUT,
    payload: {},
  });
};

export const registerNewUser = (obj, history) => async (dispatch) => {
  try {
    await axios.post('/auth/registration', obj);
    history.push('/logIn');
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data,
    });
  }
};
