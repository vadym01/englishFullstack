import {
  DELETE_FILE,
  GET_ALL_FROM_FOLDER,
  CREATE_PROJECT,
  CHANGE_TEXT_AUDIO_SENTENCE,
  CHANGE_TEXT_AUDIO_LOAD_THROUGH,
  GET_ALL_FROM_GOLDER_USER_ACCESS,
  CHANGE_DISPLAY_STATUS,
  START_REPEAT_FROM_SELECTED_FOR_REPEAT,
  REPEAT_ALL_FROM_USER_REPEAT_LIST,
  REPEAT_FAVORITE_USER_WORDS,
} from '../actions/types';

const initialState = {
  files: [],
  errors: {},
};

export default function (state = initialState, action) {
  switch (action.type) {
    case DELETE_FILE:
      // return {
      //   ...state,
      //   files: initialState.files.push(
      //     initialState.files.filter((item) => item.fileName !== action.payload)
      //   ),
      // };
      const stateForDelete = [...state.files];
      stateForDelete.splice(action.payload, 1);

      return {
        ...state,
        files: initialState.files.concat(stateForDelete),
      };
    case GET_ALL_FROM_FOLDER:
      return {
        ...state,
        files: initialState.files.concat(action.payload),
      };
    case GET_ALL_FROM_GOLDER_USER_ACCESS:
      return {
        ...state,
        files: initialState.files.concat(action.payload),
      };
    case CREATE_PROJECT:
      return {
        ...state,
        files: state.files.concat(action.payload),
        // folderItems: state.folderItems.concat(action.payload), //should by push not concat/should by only one value
      };
    case CHANGE_TEXT_AUDIO_SENTENCE:
      const f = state.files.find((el) => el.textAudio.id === action.payload.id);
      const ind = state.files.findIndex((x) => x.id === f.id);

      const newArray = [...state.files]; //Copying state array
      newArray.splice(ind, 1, action.payload);
      return {
        ...state,
        files: newArray, //reassigning todos array to new array
      };

    case CHANGE_TEXT_AUDIO_LOAD_THROUGH:
      console.log(action.payload);
      const fl = state.files.find(
        (el) => el.textAudio.id === action.payload.id
      );
      const index = state.files.findIndex((x) => x.id === fl.id);

      const arr = [...state.files]; //Copying state array
      arr.splice(index, 1, action.payload);
      return {
        ...state,
        files: arr,
      };

    case CHANGE_DISPLAY_STATUS:
      const element = state.files.find(
        (el) => el.audioId === parseInt(action.payload)
      );
      // element.show = !element.show;

      const indexOfElement = state.files.findIndex(
        (x) => x.audioId === element.audioId
      );
      const newArr = [...state.files];
      newArr.splice(indexOfElement, 1, element);

      return {
        ...state,
        files: newArr,
      };

    case START_REPEAT_FROM_SELECTED_FOR_REPEAT:
      console.log(action.payload);
      return {
        ...state,
        files: action.payload,
      };
    case REPEAT_ALL_FROM_USER_REPEAT_LIST:
      return {
        ...state,
        files: action.payload,
      };
    case REPEAT_FAVORITE_USER_WORDS:
      return {
        ...state,
        files: action.payload,
      };

    default:
      return state;
  }
}
