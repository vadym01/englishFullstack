import React from 'react';

import './index.css';
import LoadComponent from './components/admin/LoadComponent';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import { Provider } from 'react-redux';
import store from './redux/store';
import ProjectsFolders from './components/admin/ProjectsFolders';

import Authentication from './components/auth/Authentication';
import Authorization from './components/auth/Authorization';
import UserPage from './components/user/UserPage';
import jwt_decode from 'jwt-decode';
import storeJwtToken from './security/jwtSecurity';
import { AUTH_USER } from './redux/actions/types';
import { logOutUser } from './redux/actions/authActions';

import NavBar from './components/navBar/NavBar';
import AudioDictation from './components/user/AudioDictation';
import HomePage from './components/public/HomePage';
import GuestPage from './components/public/GuestPage';
import UserLearningList from './components/user/UserLearningList';

const jwtToken = localStorage.token;
if (jwtToken) {
  storeJwtToken(jwtToken);
  const decoded_jwtToken = jwt_decode(jwtToken);
  store.dispatch({
    type: AUTH_USER,
    payload: decoded_jwtToken,
  });

  const validTokenTime = Date.now() / 1000;
  if (decoded_jwtToken.exp < validTokenTime) {
    store.dispatch(logOutUser());
    window.location.href = '/';

    //   window.location.href="/"
  }
}

function App() {
  return (
    <Provider store={store}>
      <Router>
        <NavBar />

        <div className="App">
          {/* admin */}
          <Route
            path="/admin/fileManage/:folderName"
            component={LoadComponent}
          />

          <Route path="/admin/folderManage" component={ProjectsFolders} />

          {/* user */}
          <Route path="/userPage" component={UserPage} />
          <Route
            path="/user/audioDictation/:folderName"
            component={AudioDictation}
          />
          <Route exact path="/user/audioDiction" component={AudioDictation} />

          <Route path="/userLearningList" component={UserLearningList} />

          <Route exact path="/home" component={HomePage} />

          {/* public */}
          <Route path="/logIn" component={Authentication} />
          <Route path="/registration" component={Authorization} />
          <Route exact path="/" component={GuestPage} />
        </div>
      </Router>
    </Provider>
  );
}

export default App;
