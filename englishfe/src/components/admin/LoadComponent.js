import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import { createProject } from '../../redux/actions/fileActions';
import FileItems from './components/FileItems';
import { BsPlusCircle } from 'react-icons/bs';
import { BiMinusCircle } from 'react-icons/bi';
import './loadComponent.css';

class LoadComponent extends Component {
  constructor(props) {
    super(props);

    this.state = {
      file: null,
      startNewAudioLoadTrough: 0,
      sentence: '',
      fileFolder: '',
      showForm: false,
    };

    this.onFileUpload = this.onFileUpload.bind(this);
    this.onChangeHandler = this.onChangeHandler.bind(this);
    this.onSubmitHandler = this.onSubmitHandler.bind(this);
    this.showHideForm = this.showHideForm.bind(this);
  }

  componentDidMount() {
    const { folderName } = this.props.match.params;
    // this.props.getAllFromFolder(folderName);
    this.setState({
      fileFolder: folderName,
    });
  }

  onSubmitHandler = (event) => {
    event.preventDefault();

    const formData = new FormData();
    formData.append('file', this.state.file);
    formData.append('fileName', this.state.sentence);
    formData.append(
      'startNewAudioLoadTrough',
      this.state.startNewAudioLoadTrough
    );
    formData.append('sentence', this.state.sentence);
    formData.append('fileFolder', this.state.fileFolder);

    // axios.post('http://localhost:8080/storage/uploadFile', formData);

    this.props.createProject(formData, this.props.history);
  };

  onFileUpload = (event) => {
    this.setState({
      file: event.target.files[0],
    });
  };

  onChangeHandler = (event) => {
    this.setState({
      [event.target.name]: event.target.value,
    });
  };

  showHideForm() {
    this.setState({
      showForm: !this.state.showForm,
    });
  }

  render() {
    const fileForm = (
      <div className="addNewFileFormContainer">
        <form onSubmit={this.onSubmitHandler}>
          {/* <input
            onChange={this.onChangeHandler}
            name="fileName"
            type="text"
            placeholder="File name"
            // defaultValue={Math.random().toString(36).substring(7)}
            className="newFileInput"
          /> */}

          <input
            onChange={this.onChangeHandler}
            name="startNewAudioLoadTrough"
            type="number"
            placeholder="Start new audio load trough"
            defaultValue={this.state.startNewAudioLoadTrough}
            className="newFileInput"
          />
          <input
            onChange={this.onChangeHandler}
            name="sentence"
            type="text"
            placeholder="Sentence"
            size="30"
            className="newFileInput"
          />

          <input
            onChange={this.onFileUpload}
            name="file"
            type="file"
            placeholder="File"
            className="newFileInput"
          />

          <button type="submit">Save</button>
        </form>
      </div>
    );

    return (
      <div>
        {/* {console.log(this.props.match.params)} */}
        {/* <button onClick={this.showHideForm}>{this.state.showFirm ? BsPlusCircle :}</button> */}
        {this.state.showForm ? (
          <BiMinusCircle
            size="27"
            onClick={this.showHideForm}
            className="newLearningUnitShow"
          />
        ) : (
          <BsPlusCircle
            size="27"
            onClick={this.showHideForm}
            className="newLearningUnitShow"
          />
        )}
        {this.state.showForm ? fileForm : null}
        <FileItems folderName={this.props.match.params} />
      </div>
    );
  }
}

LoadComponent.propTypes = {
  createProject: PropTypes.func.isRequired,
  // getAllFromFolder: PropTypes.func.isRequired,
  errors: PropTypes.object.isRequired,
};

const mapStateToProps = (state) => ({
  errors: state.errors,
});
export default connect(mapStateToProps, { createProject })(LoadComponent);
// export default LoadComponent;
