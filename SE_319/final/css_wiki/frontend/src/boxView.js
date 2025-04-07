import React, { useState } from 'react';

function BoxView(props) {
  const [isEditing, setIsEditing] = useState(false);
  const [editedText, setEditedText] = useState(props.full_desc);
  const [editedTitle, setEditedTitle] = useState(props.title);
  const [editedSmallDesc, setEditedSmallDesc] = useState(props.small_desc);

  const handleDescriptionInputChange = (event) => {
    setEditedText(event.target.value);
  };

  const handleTitleInputChange = (event) => {
    setEditedTitle(event.target.value);
  };

  const handleSmallDescInputChange = (event) => {
    setEditedSmallDesc(event.target.value);
  };

  const handleEditClick = () => {
    setIsEditing(true);
  };

  const handleCancelClick = () => {
    setIsEditing(false);
    setEditedText(props.full_desc);
    setEditedTitle(props.title);
    setEditedSmallDesc(props.small_desc);
  };

  const handleSaveClick = () => {
    setIsEditing(false);
    props.onSave(editedText, editedTitle, editedSmallDesc);
  };

  return (
    <div className="container my-4 newPage">
      <div className="row">
        <div className="col-md-6">
          <h1 className="display-4">
            {isEditing ? (
              <input type="text" className="form-control" value={editedTitle} onChange={handleTitleInputChange} />
            ) : (
              props.title
            )}
          </h1>
          <p className="lead">
            {isEditing ? (
              <input type="text" className="form-control" value={editedSmallDesc} onChange={handleSmallDescInputChange} />
            ) : (
              props.small_desc
            )}
          </p>
          <hr className="my-4" />
          {isEditing ? (
            <div>
              <textarea className="form-control" rows="8" value={editedText} onChange={handleDescriptionInputChange}></textarea>
              <button className="btn btn-primary m-2" onClick={handleSaveClick}>Save</button>
              <button className="btn btn-danger m-2" onClick={handleCancelClick}>Cancel</button>
            </div>
          ) : (
            <p className="lead">{props.full_desc}</p>
          )}
          <p>Tags: {props.tags}</p>
        </div>
        <div className="col-md-6 text-center">
          <button className="btn btn-danger m-2" onClick={props.onClose}>Close</button>
          {isEditing ? null : (
            <button className="btn btn-primary m-2" onClick={handleEditClick}>Edit</button>
          )}
          <button className="btn btn-danger m-2" onClick={props.delete}>Delete</button>
        </div>
      </div>
    </div>
  );
}

export default BoxView;
