import React, { useState } from "react";

function NewPageView(props) {
  const [title, setTitle] = useState();
  const [smallDesc, setSmallDesc] = useState();
  const [fullDesc, setFullDesc] = useState();
  const [tags, setTags] = useState();
  const [isSubmitted, setIsSubmitted] = useState(false);
  const img = "N/A";

  const handleSubmit = (event) => {
    event.preventDefault();

    const data = {
      title: title,
      img: img,
      small_desc: smallDesc,
      full_desc: fullDesc,
      tags: tags,
    };

    fetch("http://localhost:4000/insert", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    })
      .then((response) => response.json())
      .then((data) => console.log(data))
      .catch((error) => console.error(error));
    setIsSubmitted(true);
  };

  if (isSubmitted) {
    return <div>Form submitted successfully!</div>;
  }

  return (
    <div className="newPage">
      <h2>New Page Form</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="title">Title:</label>
          <input
            type="text"
            className="form-control"
            id="title"
            value={title}
            onChange={(event) => setTitle(event.target.value)}
          />
        </div>
        <div className="form-group">
          <label htmlFor="smallDesc">Small Description:</label>
          <textarea
            className="form-control"
            id="smallDesc"
            rows="4"
            value={smallDesc}
            onChange={(event) => setSmallDesc(event.target.value)}
          ></textarea>
        </div>
        <div className="form-group">
          <label htmlFor="fullDesc">Full Description:</label>
          <textarea
            className="form-control"
            id="fullDesc"
            rows="8"
            value={fullDesc}
            onChange={(event) => setFullDesc(event.target.value)}
          ></textarea>
        </div>
        <div className="form-group">
          <label htmlFor="tags">Tag:</label>
          <input
            type="text"
            className="form-control"
            id="tags"
            value={tags}
            onChange={(event) => setTags(event.target.value)}
          />
        </div>
        <button type="submit" value="Submit" className="btn btn-primary">
          Submit
        </button>
      </form>
    </div>
  );
}

export default NewPageView;
