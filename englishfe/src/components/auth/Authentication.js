import React, { useState } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { loginUser } from '../../redux/actions/authActions';
import './authentication.css';

const Authentication = (props) => {
  const dispatch = useDispatch();
  const errors = useSelector((state) => state.errors);
  const [state, setState] = useState({ username: '', password: '' });

  const onChangeHandler = (event) => {
    const { name, value } = event.target;
    setState((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };

  const onSubmitHandler = (event) => {
    event.preventDefault();
    const obj = {
      username: state.username,
      password: state.password,
    };
    dispatch(loginUser(obj, props.history));
  };

  return (
    <div className="container">
      <div className="authenticationWindow">
        <div className="content">
          <div className="welcome">Hello There!</div>
          <div className="subtitle">
            We're almost done. Before using our services you need to{' '}
            <span className="actionText">Log in...</span>
          </div>
          <div className="input-fields">
            <form onSubmit={onSubmitHandler}>
              <input
                type="text"
                placeholder="Username"
                name="username"
                autoComplete="off"
                className="input-line full-width"
                onChange={onChangeHandler}
                value={state.username || ''}
              ></input>
              {errors.username && <div>{errors.username}</div>}
              <input
                type="password"
                placeholder="Password"
                name="password"
                autoComplete="off"
                className="input-line full-width"
                onChange={onChangeHandler}
                value={state.password || ''}
              ></input>
              {errors.password && <div>{errors.password}</div>}
              <button type="submit" className="ghost-round full-width">
                Log in
              </button>
            </form>
          </div>
          <div className="spacing">
            ...or
            <span className="actionText"> Create</span> an{' '}
            <span className="actionText">account</span>.
          </div>
          <div>
            <button
              onClick={() => props.history.push('/registration')}
              className="ghost-round full-width "
            >
              Create Account
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Authentication;
