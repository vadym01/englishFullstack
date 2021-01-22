import React, { useEffect, useState } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import AudioPlayer from 'react-h5-audio-player';
import 'react-h5-audio-player/lib/styles.css';
import './audioDiction.css';
import { AiFillStar, AiFillExclamationCircle } from 'react-icons/ai';
import { BsFillPlayFill } from 'react-icons/bs';
import { RiDeleteBin5Line, RiPlayList2Fill } from 'react-icons/ri';
import { AiOutlineStar } from 'react-icons/ai';
import { getAllFromFolderUserAccess } from '../../redux/actions/fileActions';

import { saveToUserRepeatCollection } from '../../redux/actions/userLearningActions';

const AudioDictation = (props) => {
  const dispatch = useDispatch();

  const [state, setState] = useState({
    showInfo: false,
    playStatus: '',
    mistake: [],
    showResult: false,
  });

  const { folderName } = props.match.params;

  useEffect(() => {
    folderName !== undefined &&
      dispatch(getAllFromFolderUserAccess(folderName));
  }, [dispatch]);

  const { files } = useSelector((state) => state.file);
  const onChangeHandler = (event) => {
    const { name, value } = event.target;
    setState((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };

  const onSubmitHandler = (event) => {
    let mistakeArr = [];

    files.map((file, indexOf) => {
      const arrayCheck = state[file.audioId].trim().split(' ');
      arrayCheck.map((word, index) => {
        if (
          index < file.textAudioUserAccessDto.wordFromSentenceList.length &&
          file.textAudioUserAccessDto.wordFromSentenceList[index].word !== null
        ) {
          if (
            word !==
            file.textAudioUserAccessDto.wordFromSentenceList[index].word
          ) {
            mistakeArr.push({
              stateIndex: indexOf,
              sentenceId: file.audioId,
              audioUrl: file.fileUrl,
              userWord: word,
              numOfRepeats: file.hasOwnProperty('numOfRepeats')
                ? file.numOfRepeats - 1
                : 7,
              wordFromSentenceList: index,
              wordFromSentenceId:
                file.textAudioUserAccessDto.wordFromSentenceList[index].wordId,
              favorite: file.hasOwnProperty('favorite') ? file.favorite : false,
            });
          }
        }
      });
      event.preventDefault();
      if (
        file.textAudioUserAccessDto.wordFromSentenceList.length >
        arrayCheck.length
      ) {
        let txtAudioLength =
          file.textAudioUserAccessDto.wordFromSentenceList.length;

        let ind = arrayCheck.length;
        for (ind; ind < txtAudioLength; ind++) {
          mistakeArr.push({
            stateIndex: indexOf,
            sentenceId: file.audioId,
            audioUrl: file.fileUrl,
            numOfRepeats: file.hasOwnProperty('numOfRepeats')
              ? file.numOfRepeats - 1
              : 7,
            userWord: '---',
            wordFromSentenceList: ind,
            wordFromSentenceId:
              file.textAudioUserAccessDto.wordFromSentenceList[ind].wordId,
            favorite: file.hasOwnProperty('favorite') ? file.favorite : false,
          });
        }
      }
    });
    setState((prevState) => ({
      ...prevState,
      mistake: mistakeArr,
      showResult: !state.showResult,
    }));
  };

  const onSaveToCollectionHandler = () => {
    let objArr = [];
    state.mistake.map((item) =>
      objArr.push({
        wordFromSentenceId: item.wordFromSentenceId,
        favorite: item.favorite,
        numOfRepeats: item.numOfRepeats,
        wrongTypedWord: item.userWord,
        fileDataInfoId: item.sentenceId,
      })
    );

    dispatch(saveToUserRepeatCollection(objArr, props.history));
  };

  const onMouseOverInfoHandler = () => {
    setState((prevState) => ({ ...prevState, showInfo: true }));
  };

  const onMouseLeaveInfoHandler = () => {
    setState((prevState) => ({ ...prevState, showInfo: false }));
  };

  const onAudioClickHandler = (audio) => {
    setState((prevState) => ({
      ...prevState,

      playStatus: audio,
    }));
  };

  const onMouseOver = (index) => {
    setState((prevState) => ({
      ...prevState,
      inputHoverId: index,
    }));
  };

  const onMouseLeave = (event) => {
    setState((prevState) => ({
      ...prevState,
      inputHoverId: -1,
    }));
  };

  const handleSelected = (audio) => {
    setState((prevState) => ({ ...prevState, playStatus: audio }));
  };

  const say = (m) => {
    var msg = new SpeechSynthesisUtterance();
    var voices = window.speechSynthesis.getVoices();
    msg.voice = voices[10];
    msg.voiceURI = 'native';
    msg.volume = 1;
    msg.rate = 1;
    msg.pitch = 0.8;
    msg.text = m;
    msg.lang = 'en-US';
    speechSynthesis.speak(msg);
  };

  const onDeleteHandler = (index) => {
    setState((prevState) => ({
      ...prevState,
      mistake: state.mistake.filter((_, i) => i !== index),
    }));
  };

  const onSetFavHandler = (index) => {
    const setFav = state.mistake[index];
    setFav.favorite = !state.mistake[index].favorite;
    const newArr = [...state.mistake];
    newArr.splice(index, 1, setFav);
    // const newArr =

    setState((prevState) => ({
      ...prevState,
      mistake: newArr,
    }));
  };

  function handleEnter(event) {
    if (event.keyCode === 13) {
      const form = event.target.form;
      const index = Array.prototype.indexOf.call(form, event.target);
      form.elements[index + 1].focus();
      event.preventDefault();
    }
  }

  return (
    <div className="audioDictionContainer">
      <AudioPlayer src={state.playStatus} className="audioPlayer" />

      <AiFillExclamationCircle
        size="25"
        className="showInfo"
        onMouseOver={onMouseOverInfoHandler}
        onMouseLeave={onMouseLeaveInfoHandler}
        className="exclamationCircle"
      />
      <div className={state.showInfo ? 'infoActive' : 'infoHidden'}>
        <p>Don't use punctuation marks</p>
        <p>Press "Enter" or "Tab" to switch (to the next audio and input) </p>
        <p>
          Using your mouse hover to the input field to receive a hint( correct
          spelling of words)
        </p>
      </div>

      {files ? (
        <div>
          <div>
            {state.showResult ? (
              <div>
                <div>
                  {state.mistake.map((unit, index) => (
                    <div key={index} className="generableComponentContainer">
                      <div>
                        {files[unit.stateIndex].textAudioUserAccessDto.sentence}
                      </div>

                      <div>
                        {
                          files[unit.stateIndex].textAudioUserAccessDto
                            .wordFromSentenceList[unit.wordFromSentenceList]
                            .word
                        }
                      </div>

                      <div>{unit.userWord}</div>
                      <div className="learningActionButton">
                        {unit.favorite ? (
                          <AiFillStar onClick={() => onSetFavHandler(index)} />
                        ) : (
                          <AiOutlineStar
                            onClick={() => onSetFavHandler(index)}
                          />
                        )}
                      </div>
                      <div className="learningActionButton">
                        <RiPlayList2Fill
                          onClick={() => onAudioClickHandler(unit.audioUrl)}
                        />
                      </div>
                      <div className="learningActionButton">
                        <BsFillPlayFill
                          onClick={() =>
                            say(
                              files[unit.stateIndex].textAudioUserAccessDto
                                .wordFromSentenceList[unit.wordFromSentenceList]
                                .word
                            )
                          }
                        />
                      </div>
                      <div className="learningActionButton">
                        <RiDeleteBin5Line
                          onClick={() => onDeleteHandler(index)}
                        />
                      </div>
                    </div>
                  ))}
                </div>
                <button
                  onClick={onSaveToCollectionHandler}
                  className="learningListButton"
                >
                  Save to my collection for repeat
                </button>
              </div>
            ) : (
              ''
            )}
          </div>
          <div className="formInputMainContainer">
            <form onSubmit={onSubmitHandler} className="generableFormContainer">
              {files &&
                files.map((file, index) => (
                  <div key={index} className="generableInputContainer">
                    <div className="inputField">
                      <input
                        name={file.audioId}
                        onChange={onChangeHandler}
                        type="text"
                        style={{ padding: '0px' }}
                        size={file.textAudioUserAccessDto.sentenceLength}
                        // size="34"
                        value={state[file.audioId]}
                        onClick={() => onAudioClickHandler(file.fileUrl)}
                        onMouseOver={() => onMouseOver(index)}
                        onMouseLeave={() => onMouseLeave(index)}
                        id={index}
                        onKeyDown={handleEnter}
                        onSelect={() => handleSelected(file.fileUrl)}
                        autoComplete="off"
                        className="inputLine"
                        required
                      />
                    </div>

                    <div className="hintContainer">
                      <p
                        className={
                          state.inputHoverId === index
                            ? 'hintHidden'
                            : 'hintShow'
                        }
                      >
                        {file.textAudioUserAccessDto.sentence}
                      </p>
                    </div>
                  </div>
                ))}

              {state.showResult ? null : (
                <button type="submit" className="learningListButton">
                  submit
                </button>
              )}
            </form>
          </div>
        </div>
      ) : (
        ''
      )}
    </div>
  );
};

export default AudioDictation;
