import {
  GET_ALL_FROM_USER_LEARNING_COLLECTION,
  SET_FAV_LEARNING_UNIT,
  SELECT_FOR_REPEAT_ITEM,
  DELETE_FROM_USER_LEARNING_LIST,
  START_REPEAT_FROM_SELECTED_FOR_REPEAT,
} from '../actions/types';

const initialState = {
  learningUnits: [],
  errors: {},
};

export default function (state = initialState, action) {
  switch (action.type) {
    case GET_ALL_FROM_USER_LEARNING_COLLECTION:
      return {
        ...state,
        learningUnits: action.payload,
      };

    case SET_FAV_LEARNING_UNIT:
      const unit = state.learningUnits.filter(
        (ind) => ind.userPageInfoId === action.payload
      );
      unit[0].favorite = !unit[0].favorite;
      const index = state.learningUnits.findIndex(
        (x) => x.userPageInfoId === unit[0].userPageInfoId
      );

      const newArr = [...state.learningUnits];

      newArr.splice(index, 1, unit[0]);
      return {
        ...state,
        learningUnits: initialState.learningUnits.concat(newArr),
      };

    case SELECT_FOR_REPEAT_ITEM:
      const unitForRepeat = state.learningUnits[action.payload];
      unitForRepeat.hasOwnProperty('repeat')
        ? (unitForRepeat.repeat = !unitForRepeat.repeat)
        : (unitForRepeat.repeat = true);
      const newArrForRepeatSelect = [...state.learningUnits];

      newArrForRepeatSelect.splice(action.payload, 1, unitForRepeat);
      return {
        ...state,
        learningUnits: initialState.learningUnits.concat(newArrForRepeatSelect),
      };
    case DELETE_FROM_USER_LEARNING_LIST:
      const newStateForDelete = [...state.learningUnits];

      newStateForDelete.splice(action.payload, 1);

      return {
        ...state,
        learningUnits: initialState.learningUnits.concat(newStateForDelete),
      };

    case START_REPEAT_FROM_SELECTED_FOR_REPEAT:
      const units = [...state.learningUnits].filter(
        (unit) => unit.hasOwnProperty('repeat') && unit.repeat === true
      );
      return {
        ...state,
        learningUnits: initialState.learningUnits.concat(units),
      };

    default:
      return state;
  }
}
