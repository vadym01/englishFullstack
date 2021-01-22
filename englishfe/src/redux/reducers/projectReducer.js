import {
  GET_PROJECTS,
  CREATE_FOLDER,
  GET_ALL_FOLDERS,
  SET_FAV_FOLDER,
  DELETE_FOLDER,

  // GET_ALL_FROM_FOLDER,
  // CREATE_PROJECT,
} from '../actions/types';

const initialState = {
  folderItems: [],
};

export default function (state = initialState, action) {
  switch (action.type) {
    case GET_PROJECTS:
      return { ...state, folderItems: action.payload };
    // case CREATE_PROJECT:
    //   return {
    //     ...state,
    //     folderItems: initialState.folderItems.concat(action.payload), //should by push not concat/should by only one value
    //   };
    case CREATE_FOLDER:
      return {
        ...state,
        // projects: initialState.projects.concat(action.payload), //should by push not concat/hould by only one value
        folderItems: state.folderItems.concat(action.payload),
      };
    case GET_ALL_FOLDERS:
      return {
        ...state,
        folderItems: action.payload,
      };

    case SET_FAV_FOLDER:
      const folder = state.folderItems.find((el) => el.id === action.payload);
      folder.favoriteSelected = !folder.favoriteSelected;
      const index = state.folderItems.findIndex((x) => x.id === folder.id);
      const newArray = [...state.folderItems]; //Copying state array
      newArray.splice(index, 1, folder);

      // {
      //   console.log(folder);
      // }
      // {
      //   console.log(state);
      // }
      return {
        ...state,
        folderItems: newArray,
      };

    case DELETE_FOLDER:
      const arrForDeleteFolder = [...state.folderItems];
      arrForDeleteFolder.splice(action.payload, 1);
      return {
        ...state,
        folderItems: initialState.folderItems.concat(arrForDeleteFolder),
      };

    default:
      return state;
  }
}
