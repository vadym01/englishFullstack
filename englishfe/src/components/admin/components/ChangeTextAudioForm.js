import React, { useState, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import {
  changeTextAudioSentence,
  changeStartNewAudioLoadThrough,
} from '../../../redux/actions/fileActions';
import { MdDelete } from 'react-icons/md';
import { deleteFile } from '../../../redux/actions/fileActions';
import { BiText } from 'react-icons/bi';
import { CgTimelapse } from 'react-icons/cg';
import './changeTextAudioForm.css';

const ChangeTextAudioForm = ({
  id,
  sentence,
  time,
  folderName,
  fileName,
  index,
}) => {
  const files = useSelector((state) => state.file.files);
  const dispatch = useDispatch();
  const [state, setState] = useState({
    sentence: '',

    time: null,

    showFormChangeText: false,
    showFormChangeAudioTimeLoad: false,
  });

  useEffect(() => {}, [files]);

  const onChangeHandler = (event) => {
    const { name, value } = event.target;
    setState((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };

  const showFormChangeText = () => {
    setState({
      showFormChangeText: !state.showFormChangeText,
    });
  };

  const showFormChangeAudioTimeLoad = () => {
    setState({
      showFormChangeAudioTimeLoad: !state.showFormChangeAudioTimeLoad,
    });
  };

  const deleteFileHandler = (bucketFolder, fileName, index) => {
    dispatch(deleteFile(bucketFolder, fileName, index));
  };

  const onSubmitHandlerTxt = (event) => {
    event.preventDefault();
    const obj = {
      id: id,
      sentence: state.sentence,
    };
    dispatch(changeTextAudioSentence(obj));
  };

  const onSubmitHandlerAudioTime = (event) => {
    event.preventDefault();
    const obj = {
      id: id,
      time: state.time,
    };
    dispatch(changeStartNewAudioLoadThrough(obj));
  };

  const changeTextAudioForm = () => {
    return (
      <div className="inputForm">
        <form onSubmit={onSubmitHandlerTxt}>
          <input
            type="text"
            name="sentence"
            placeholder={sentence}
            onChange={onChangeHandler}
            value={state.sentence || ''}
          />
          <button type="submit" value="Submit">
            submit
          </button>
        </form>
      </div>
    );
  };

  const changeAudioTimeLoadForm = () => {
    return (
      <div>
        <form onSubmit={onSubmitHandlerAudioTime}>
          <input
            type="number"
            name="time"
            placeholder={time}
            onChange={onChangeHandler}
            value={state.time || time}
          />
          <button type="submit" value="Submit">
            submit
          </button>
        </form>
      </div>
    );
  };

  return (
    <div className="iconsContainer">
      <div className="actionIcons">
        <MdDelete
          size={25}
          onClick={() => deleteFileHandler(folderName, fileName, index)}
          className="icon"
        ></MdDelete>
      </div>

      {/* <div className="actionIcons"> */}
      <div
        className="
          actionIcons"
      >
        <BiText
          size={25}
          onClick={showFormChangeText}
          className="icon"
        ></BiText>
        {state.showFormChangeText && changeTextAudioForm()}
      </div>

      <div className="actionIcons">
        <CgTimelapse
          size={25}
          onClick={showFormChangeAudioTimeLoad}
          className="icon"
        ></CgTimelapse>

        {state.showFormChangeAudioTimeLoad && changeAudioTimeLoadForm()}
      </div>
    </div>
  );
};

export default ChangeTextAudioForm;
