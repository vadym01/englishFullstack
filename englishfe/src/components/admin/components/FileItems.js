import React, { useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';

import { getAllFromFolder } from '../../../redux/actions/fileActions';
import ChangeTextAudioForm from './ChangeTextAudioForm';

import './changeTextAudioForm.css';

const FileItems = (props) => {
  const dispatch = useDispatch();
  const { folderName } = props.folderName;

  useEffect(() => {
    dispatch(getAllFromFolder(folderName));
  }, [dispatch]);
  const files = useSelector((state) => state.file.files);

  return (
    <div className="mainContainer">
      {files.length > 0
        ? files.map((item, index) => (
            <div key={item.id} className="fileInfo">
              <div className="fileDetail">
                <div className="fileRedactor">
                  <div className="actionIcons">
                    <ChangeTextAudioForm
                      id={item.textAudio.id}
                      sentence={item.textAudio.sentence}
                      time={item.textAudio.startNewAudioLoadThrough}
                      folderName={folderName}
                      fileName={item.fileName}
                      index={index}
                    />
                  </div>
                </div>
                <div className="fileTextInfo">
                  <p>
                    <i>File name: </i> {item.fileName}
                  </p>
                  <p>
                    <i>File size: </i> {item.fileSize}
                  </p>

                  <p>
                    <i>Sentence from audio: </i> {item.textAudio.sentence}
                  </p>

                  <p>
                    <i>Loading new audio through: </i>
                    {item.textAudio.startNewAudioLoadThrough}
                  </p>
                </div>
              </div>
              <div className="audioConfig"></div>
            </div>
          ))
        : ''}
    </div>
  );
};

export default FileItems;
