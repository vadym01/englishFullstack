import React, { useEffect, useState } from 'react';
import { useSelector, useDispatch } from 'react-redux';

import {
  getAllFromUserLearningCollection,
  setFavLearningUnit,
  selectForRepeatItem,
  deleteFromUserLearningList,
} from '../../redux/actions/userLearningActions';
import '../user/userLearningList.css';
import Table from '../admin/components/Table';
import {
  startRepeatFromSelectedForRepeat,
  repeatAllFromUserRepeatList,
  repeatFavoriteUserWords,
} from '../../redux/actions/fileActions';

const UserLearningList = (props) => {
  const dispatch = useDispatch();
  let { learningUnits } = useSelector((state) => state.learningUnits);
  const [state, setState] = useState({
    playStatus: '',
    mistake: [],
    showResult: false,
    showRepeatActions: false,
    selectWordsOption: false,
    showButtonRepeatActions: true,
    showOnlyFavorite: false,
  });

  useEffect(() => {
    dispatch(getAllFromUserLearningCollection());
  }, []);

  const onSelectForRepeatClickHandler = (index) => {
    dispatch(selectForRepeatItem(index));
  };

  const showHideRepeatActions = () => {
    setState((prevState) => ({
      ...prevState,
      showButtonRepeatActions: !state.showButtonRepeatActions,
    }));
  };

  const onRepeatAllBClickHandler = () => {
    dispatch(repeatAllFromUserRepeatList(learningUnits, props.history));
  };

  const onSelectWordsForRepeatBClickHandler = () => {
    showHideRepeatActions();
  };

  const displayFavoriteBClickHandler = () => {
    setState((prevState) => ({
      ...prevState,
      showOnlyFavorite: !state.showOnlyFavorite,
    }));
  };

  const onRepeatFavBClickHandler = () => {
    const arr = learningUnits.filter((unit) => unit.favorite === true);
    dispatch(repeatFavoriteUserWords(arr, props.history));
  };

  const onRepeatClickHandler = () => {
    const arr = learningUnits.filter(
      (unit) => unit.hasOwnProperty('repeat') && unit.repeat === true
    );

    dispatch(startRepeatFromSelectedForRepeat(arr, props.history));
  };

  const onBackClickHandler = () => {
    showHideRepeatActions();
  };

  const onDeleteHandler = (id, index) => {
    console.log(id);
    dispatch(deleteFromUserLearningList(id, index));
  };

  const onSetFavHandler = (index) => {
    const id = learningUnits[index].userPageInfoId;
    dispatch(setFavLearningUnit(id));
  };

  return (
    <div className="mainContainer">
      {learningUnits.length !== 0 ? (
        <div>
          {state.showRepeatActions ? (
            state.showButtonRepeatActions ? (
              <div
                onMouseLeave={() =>
                  setState((prevState) => ({
                    ...prevState,
                    showRepeatActions: !state.showRepeatActions,
                  }))
                }
              >
                {state.showRepeatActions ? (
                  <ul>
                    <li>
                      <button
                        onClick={() => onRepeatAllBClickHandler()}
                        className="learningListButton"
                      >
                        Repeat all
                      </button>
                    </li>

                    <li>
                      <button
                        onClick={() => onSelectWordsForRepeatBClickHandler()}
                        className="learningListButton"
                      >
                        Select words for repeat
                      </button>
                    </li>

                    <li>
                      <button
                        onClick={() => onRepeatFavBClickHandler()}
                        className="learningListButton"
                      >
                        Repeat favorite words
                      </button>
                    </li>
                  </ul>
                ) : null}
              </div>
            ) : (
              <ul>
                <li>
                  <button
                    onClick={() => onRepeatClickHandler()}
                    className="learningListButton"
                  >
                    Start
                  </button>
                </li>
                <li>
                  <button
                    onClick={() => onBackClickHandler()}
                    className="learningListButton"
                  >
                    Back
                  </button>
                </li>
              </ul>
            )
          ) : (
            <div>
              <button
                onMouseOver={() =>
                  setState((prevState) => ({
                    ...prevState,
                    showRepeatActions: !state.showRepeatActions,
                  }))
                }
                className="learningListButton"
              >
                Repeat
              </button>
              <button
                onClick={() => displayFavoriteBClickHandler()}
                className="learningListButton"
              >
                {state.showOnlyFavorite ? 'Show All' : 'Show only Favorite'}
              </button>
            </div>
          )}
          <div className="tableContainer">
            <Table
              learningUnits={
                state.showOnlyFavorite
                  ? learningUnits.filter((unit) => unit.favorite === true)
                  : learningUnits
              }
              onSetFavHandler={onSetFavHandler}
              onDeleteHandler={onDeleteHandler}
              isSelectedForRepeat={state.showButtonRepeatActions}
              onSelectForRepeatClickHandler={onSelectForRepeatClickHandler}
              displayStatus={props.showLearned}
              showOnlyFavorite={state.showOnlyFavorite}
            />
          </div>
        </div>
      ) : (
        <div className="emptyLearningListContainer">
          <h1>Nothing to repeat</h1>
        </div>
      )}
    </div>
  );
};

export default UserLearningList;
