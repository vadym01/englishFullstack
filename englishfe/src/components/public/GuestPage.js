import React from 'react';
import './guestPage.css';

import { HiUserAdd } from 'react-icons/hi';
import { Link } from 'react-router-dom';

const GuestPage = () => {
  return (
    <div className="guestPageContainer">
      <div>
        <div className="introductionCont">
          <h1 className="infoTxt">Listen and write</h1>
          <p className="infoTxt">
            Three steps to improve your english writing skills
          </p>
          <div id="stepOne">
            <p className="step">Step № 1:</p>
            <div className="infoTxt">
              Click here{'  '}
              {
                <Link to="/logIn" id="suggestRegistrLink">
                  <HiUserAdd size={25} />
                </Link>
              }{' '}
              fro registration
            </div>
          </div>
        </div>

        <div className="gPageInfoCont">
          <div>
            <img
              alt="img"
              src="https://images-for-folders.s3.eu-central-1.amazonaws.com/images-for-folders/firstImage"
              className="firstImg imgGen"
            />
          </div>

          <div className="textCont">
            <p className="step">Step № 2:</p>
            <p className="infoTxt">Choose audio dictation </p>
          </div>
        </div>

        <div className="gPageInfoCont">
          <div className="textCont">
            <p className="infoTxt">...and start typing</p>
          </div>
          <div>
            <img
              alt="img"
              src="https://images-for-folders.s3.eu-central-1.amazonaws.com/images-for-folders/secondIimg"
              className="secondImg imgGen"
            />
            <img
              alt="img"
              src="https://images-for-folders.s3.eu-central-1.amazonaws.com/images-for-folders/thirdImg"
              className="thirdImg imgGen"
            />
          </div>
        </div>

        <div className="lastGPageInfoCont">
          <div>
            <div className="textCont">
              <p className="step">Step № 3:</p>
              <p className="infoTxt">
                Submit for grammar checking and save your mistakes to your
                personal list for future repeat
              </p>
            </div>
            <div>
              <img
                alt="img"
                src="https://images-for-folders.s3.eu-central-1.amazonaws.com/images-for-folders/fifthImg"
                className="forthImg imgGen"
              />
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default GuestPage;
