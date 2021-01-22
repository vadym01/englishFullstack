import React, { useState } from 'react';
import AudioPlayer from 'react-h5-audio-player';
import { AiFillStar, AiOutlineStar } from 'react-icons/ai';
import { RiDeleteBin5Line, RiPlayList2Fill } from 'react-icons/ri';
import { BsFillPlayFill } from 'react-icons/bs';
import { say } from '../../helperFunc';
import './table.css';

const Table = ({
  learningUnits,
  onSetFavHandler,
  onDeleteHandler,
  isSelectedForRepeat,
  onSelectForRepeatClickHandler,
  displayStatus,
  showOnlyFavorite,
}) => {
  const [state, setState] = useState({
    playStatus: '',
  });

  const onPlayAudioClickHandler = (audioUrl) => {
    console.log(audioUrl);
    setState((prevState) => ({ ...prevState, playStatus: audioUrl }));
  };

  return (
    <div className="mainTableGridContainer">
      <AudioPlayer src={state.playStatus} className="audioPlayer" />

      {learningUnits === undefined
        ? ''
        : displayStatus === true
        ? learningUnits !== undefined &&
          learningUnits.map(
            (unit, index) =>
              unit.numOfRepeats === 0 && (
                <div key={index} className="generableComponentContainer">
                  {isSelectedForRepeat === false &&
                    (unit.repeat ? (
                      <div className="learningActionButton">
                        <AiFillStar
                          onClick={() => onSelectForRepeatClickHandler(index)}
                        ></AiFillStar>
                      </div>
                    ) : (
                      <div className="learningActionButton">
                        <AiOutlineStar
                          onClick={() => onSelectForRepeatClickHandler(index)}
                        ></AiOutlineStar>
                      </div>
                    ))}

                  <div className="gridD">
                    <div>{unit.textAudioUserAccessDto.sentence}</div>
                    <div>
                      {
                        learningUnits[
                          index
                        ].textAudioUserAccessDto.wordFromSentenceList.filter(
                          (item) =>
                            item.wordId ===
                            learningUnits[index].wordFromSentenceId
                        )[0].word
                      }
                    </div>
                    <div>{unit.wrongTypedWord}</div>
                    <div className="learningActionButton">
                      {unit.favorite ? (
                        <AiFillStar onClick={() => onSetFavHandler(index)} />
                      ) : (
                        <AiOutlineStar onClick={() => onSetFavHandler(index)} />
                      )}
                    </div>
                    <div>{learningUnits[index].numOfRepeats}</div>
                    <div className="learningActionButton">
                      <RiPlayList2Fill
                        onClick={() =>
                          onPlayAudioClickHandler(
                            unit.fileDataInfoUserLayerAccessDto.fileUrl
                          )
                        }
                      />
                    </div>
                    <div className="learningActionButton">
                      <BsFillPlayFill
                        onClick={() =>
                          say(
                            learningUnits[
                              index
                            ].textAudioUserAccessDto.wordFromSentenceList.filter(
                              (item) =>
                                item.wordId ===
                                learningUnits[index].wordFromSentenceId
                            )[0].word
                          )
                        }
                      />
                    </div>
                  </div>

                  <div className="learningActionButton">
                    <RiDeleteBin5Line
                      onClick={() =>
                        onDeleteHandler(
                          learningUnits[index].userPageInfoId,
                          index
                        )
                      }
                    ></RiDeleteBin5Line>
                  </div>
                </div>
              )
          )
        : learningUnits.map(
            (unit, index) =>
              unit.numOfRepeats > 0 && (
                <div key={index} className="generableComponentContainer">
                  {isSelectedForRepeat === false &&
                    (unit.repeat ? (
                      <div className="learningActionButton">
                        <AiFillStar
                          onClick={() => onSelectForRepeatClickHandler(index)}
                        ></AiFillStar>
                      </div>
                    ) : (
                      <div className="learningActionButton">
                        <AiOutlineStar
                          onClick={() => onSelectForRepeatClickHandler(index)}
                        ></AiOutlineStar>
                      </div>
                    ))}
                  <div>{unit.textAudioUserAccessDto.sentence}</div>
                  <div>
                    {
                      learningUnits[
                        index
                      ].textAudioUserAccessDto.wordFromSentenceList.filter(
                        (item) =>
                          item.wordId ===
                          learningUnits[index].wordFromSentenceId
                      )[0].word
                    }
                  </div>
                  <div>{unit.wrongTypedWord}</div>
                  <div className="learningActionButton">
                    {unit.favorite ? (
                      <AiFillStar onClick={() => onSetFavHandler(index)} />
                    ) : (
                      <AiOutlineStar onClick={() => onSetFavHandler(index)} />
                    )}
                  </div>
                  <div>{learningUnits[index].numOfRepeats}</div>
                  <div className="learningActionButton">
                    <RiPlayList2Fill
                      onClick={() =>
                        onPlayAudioClickHandler(
                          unit.fileDataInfoUserLayerAccessDto.fileUrl
                        )
                      }
                    />
                  </div>
                  <div className="learningActionButton">
                    <BsFillPlayFill
                      onClick={() =>
                        say(
                          learningUnits[
                            index
                          ].textAudioUserAccessDto.wordFromSentenceList.filter(
                            (item) =>
                              item.wordId ===
                              learningUnits[index].wordFromSentenceId
                          )[0].word
                        )
                      }
                    />
                  </div>

                  <div className="learningActionButton">
                    <RiDeleteBin5Line
                      onClick={() =>
                        onDeleteHandler(
                          learningUnits[index].userPageInfoId,
                          index
                        )
                      }
                    ></RiDeleteBin5Line>
                  </div>
                </div>
              )
          )}
    </div>
  );
};

export default Table;
