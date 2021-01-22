import React, { Component } from 'react';
import { FaFolderPlus } from 'react-icons/fa';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import {
  createFolder,
  getAllFolders,
} from '../../redux/actions/projectActions';

import FolderItem from './components/FolderItem';

import '../admin/components/projectsFolders.css';

class ProjectsFolders extends Component {
  constructor(props) {
    super(props);

    this.state = {
      folderName: '',
      description: '',
      imageFileName: '',
      addFolderShow: false,
    };
    this.onChangeHandler = this.onChangeHandler.bind(this);
    this.onChangeImageFormInputHandler = this.onChangeImageFormInputHandler.bind(
      this
    );
    this.onSubmitHandler = this.onSubmitHandler.bind(this);
  }

  componentDidMount() {
    this.props.getAllFolders();
  }

  onChangeHandler = (event) => {
    this.setState({
      [event.target.name]: event.target.value,
    });
  };

  onSubmitHandler = (event) => {
    const formData = new FormData();
    formData.append('imageFolderFile', this.state.imageFolderFile);
    formData.append('imageFileName', this.state.imageFileName);
    formData.append('folderName', this.state.folderName);
    formData.append('description', this.state.description);
    this.props.createFolder(formData);
    event.preventDefault();
  };

  onAddFolderElementHandler = () => {
    this.setState({
      addFolderShow: !this.state.addFolderShow,
    });
  };

  onChangeImageFormInputHandler = (event) => {
    this.setState({
      imageFolderFile: event.target.files[0],
    });
  };

  render() {
    const addFolderElement = (
      <div className="formInputContainer">
        <form onSubmit={this.onSubmitHandler} className="newFolderForm">
          <input
            name="text"
            name="imageFileName"
            placeholder="Image file name"
            onChange={this.onChangeHandler}
            className="inputField"
          ></input>
          <input
            name="folderName"
            placeholder="Folder name"
            onChange={this.onChangeHandler}
            className="inputField"
          />
          <textarea
            name="description"
            placeholder="Description"
            cols="33"
            rows="7"
            onChange={this.onChangeHandler}
            className="inputField"
          />
          <input
            onChange={this.onChangeImageFormInputHandler}
            name="imageFile"
            placeholder="Folder image"
            type="file"
            className="inputField"
          ></input>
          <button type="submit">Submit</button>
        </form>
      </div>
    );

    return (
      <div>
        <div>{this.state.addFolderShow ? addFolderElement : null}</div>
        <div>
          <FolderItem />
          <FaFolderPlus
            onClick={this.onAddFolderElementHandler}
            size={32}
            id="addFolder"
          ></FaFolderPlus>
        </div>
      </div>
    );
  }
}

ProjectsFolders.propTypes = {
  createFolder: PropTypes.func.isRequired,
  errors: PropTypes.object.isRequired,
  getAllFolders: PropTypes.func.isRequired,
};

const mapStateToProps = (state) => {
  return {
    errors: state.errors,
    allFolders: state.project.folderItems,
  };
};

export default connect(mapStateToProps, {
  createFolder,
  getAllFolders,
})(ProjectsFolders);
