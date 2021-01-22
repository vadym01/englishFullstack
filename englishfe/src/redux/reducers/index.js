import { combineReducers } from 'redux';
import errorReducer from './errorReducer';
import projectReducer from './projectReducer';
import fileReducer from './fileReducer';
import authReducer from './authReducer';
import userLearningReducer from './userLearningReducer';

export default combineReducers({
  errors: errorReducer,
  project: projectReducer,
  file: fileReducer,
  learningUnits: userLearningReducer,
  user: authReducer,
});
