import { AUTH_USER, LOG_OUT } from '../actions/types';

const initialState = {
  user: {},
  validToken: false,
};

const booleanActionPayload = (payload) => {
  if (payload) {
    return true;
  } else {
    return false;
  }
};

export default function (state = initialState, action) {
  switch (action.type) {
    case AUTH_USER:
      return {
        ...state,
        user: action.payload,
        validToken: booleanActionPayload(action.payload),
      };
    case LOG_OUT:
      return {
        ...state,
        user: {},
        validToken: false,
      };

    default:
      return state;
  }
}
