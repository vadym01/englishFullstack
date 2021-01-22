import React, { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { registerNewUser } from '../../redux/actions/authActions';
import './authorization.css';

const Authorization = (props) => {
  const dispatch = useDispatch();
  const errors = useSelector((state) => state.errors);
  const [state, setState] = useState({
    username: '',
    firstName: '',
    lastName: '',
    password: '',
    confirmPassword: '',
  });

  // useEffect(() => {}, [errors]);

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
      firstName: state.firstName,
      lastName: state.lastName,
      password: state.password,
      confirmPassword: state.confirmPassword,
    };
    dispatch(registerNewUser(obj, props.history));
  };

  return (
    <div className="container">
      <div className="authorizationWindow">
        <div className="content">
          <div className="registrationTxt">Registration</div>
          <div className="input-fields">
            <form onSubmit={onSubmitHandler}>
              <input
                type="text"
                name="username"
                autoComplete="off"
                placeholder="Email"
                onChange={onChangeHandler}
                value={state.username || ''}
                className="input-line full-width"
              />
              {errors.username && <div>{errors.username}</div>}
              <input
                type="text"
                name="firstName"
                autoComplete="off"
                placeholder="First name"
                onChange={onChangeHandler}
                value={state.firstName || ''}
                className="input-line full-width"
              />
              {errors.firstName && <div>{errors.firstName}</div>}
              <input
                type="text"
                name="lastName"
                autoComplete="off"
                placeholder="Last name"
                onChange={onChangeHandler}
                value={state.lastName || ''}
                className="input-line full-width"
              />
              {errors.lastName && <div>{errors.lastName}</div>}
              <input
                type="password"
                name="password"
                autoComplete="off"
                placeholder="Password"
                onChange={onChangeHandler}
                value={state.password || ''}
                className="input-line full-width"
              />
              {errors.password && <div>{errors.password}</div>}
              <input
                type="password"
                name="confirmPassword"
                autoComplete="off"
                placeholder="Confirm password"
                onChange={onChangeHandler}
                value={state.confirmPassword || ''}
                className="input-line full-width"
              />
              {errors.confirmPassword && <div>{errors.confirmPassword}</div>}
              <button type="submit" className="ghost-round full-width">
                Register me
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Authorization;
