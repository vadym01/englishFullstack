import React, { useState } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { logOutUser } from '../../redux/actions/authActions';

import './navBar.css';
// import '../../components/navBar/navBar.css';
import { GoTasklist } from 'react-icons/go';
import { FaUserGraduate } from 'react-icons/fa';
import { RiLogoutBoxLine } from 'react-icons/ri';
import { RiPlayList2Line } from 'react-icons/ri';
import { AiOutlineHome, AiFillDatabase } from 'react-icons/ai';
import { HiUserAdd, HiOutlineViewGrid } from 'react-icons/hi';

import { Link } from 'react-router-dom';

const NavBar = () => {
  const [state, setState] = useState(false);
  const { user } = useSelector((state) => state);
  const dispatch = useDispatch();

  const authenticatedUser = (
    <div className="nav-container">
      {user.user.roles && user.user.roles.includes('ROLE_ADMIN') && (
        <Link to="/admin/folderManage" className="nav-item">
          <i className="icon-button">
            <AiFillDatabase size={26} />
          </i>
        </Link>
      )}
      <Link to="/home" className="nav-item">
        <AiOutlineHome className="icon-content" />
      </Link>
      <li className="nav-item">
        <i href="#" className="icon-button" onClick={() => setState(!state)}>
          <FaUserGraduate className="icon-content" />
        </i>
      </li>

      {state ? (
        <div className="dropdown" onMouseLeave={() => setState(!state)}>
          <Link to="/userPage" className="menu-item">
            <i className="icon-button">
              <GoTasklist className="icon-content" />
            </i>
            <p className="menuDesc">Progress story</p>
          </Link>

          <Link to="/userLearningList" className="menu-item">
            <i className="icon-button">
              <RiPlayList2Line className="icon-content" />
            </i>
            <p className="menuDesc">To repeat</p>
          </Link>

          <Link
            to="/guest"
            className="menu-item"
            onClick={() => dispatch(logOutUser())}
          >
            <i className="icon-button">
              <RiLogoutBoxLine className="icon-content" />
            </i>
            <p className="menuDesc">Log out</p>
          </Link>
        </div>
      ) : null}
    </div>
  );

  const unauthenticatedUser = (
    <div className="nav-container">
      <Link to="/logIn" className="nav-item">
        <i className="icon-button">
          <HiUserAdd size={26} />
        </i>
      </Link>

      <Link to="/guest" className="nav-item">
        <i className="icon-button">
          <HiOutlineViewGrid className="icon-content" />
        </i>
      </Link>
    </div>
  );
  return (
    <nav className="navbar">
      <ul className="navbar-nav">
        {user.validToken ? authenticatedUser : unauthenticatedUser}
      </ul>
    </nav>
  );
};

export default NavBar;
