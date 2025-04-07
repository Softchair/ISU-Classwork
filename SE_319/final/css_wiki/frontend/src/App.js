import React, { useState, useEffect } from "react";
import "bootstrap/dist/css/bootstrap.css";
import "./page.css";
import BoxView from "./boxView";
import NewPageView from "./newPageView";

function App() {
  const [data, setData] = useState([]);
  const [selectedBox, setSelectedBox] = useState(null);
  const [showNewPageModal, setShowNewPageModal] = useState(false);
  const [searchQuery, setSearchQuery] = useState("");

  useEffect(() => {
    fetch("http://localhost:4000/")
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        setData(data);
      });
  }, []);

  const generatePage = () => {
    const rows = [];

    //For loop to create boxes
    for (let i = 0; i < data.length; i += 3) {
      const rowBoxes = [];

      //For loop to create 3 boxes per row
      for (let j = 0; j < 3; j++) {
        const index = i + j;
        if (index < data.length) {
          const box = (
            <div
              key={`box-${index}`}
              className="col m-1 text-center theme shadow"
              onClick={() => handleBoxClick(data[index])}
            >
              <h3 className="my-auto theme">{data[index].title}</h3>
              <p className="my-auto theme">{data[index].small_desc}</p>
            </div>
          );
          rowBoxes.push(box);
        }
      }

      const row = (
        <div
          key={`row-${i}`}
          className="row justify-content-lg-center align-items-center"
        >
          {rowBoxes}
        </div>
      );
      rows.push(row);

      //Adds a row break if its after a row
      if (i !== data.length - 1) {
        rows.push(<div key={`rowBreak-${i}`} className="w-100"></div>);
      }
    }

    //returns rows
    return rows;
  };

  function handleBoxClick(box) {
    setSelectedBox(box);
    document.body.style.overflow = "hidden";
  }

  function handleCloseModal() {
    setShowNewPageModal(false);
    window.location.reload();
  }

  function handleNewPageClick() {
    setShowNewPageModal(true);
  }

  async function saveBox(id, newTitle, newDescription, newSmallDesc) {
    try {
      const response = await fetch(`http://localhost:4000/update/${id}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          title: newTitle,
          full_desc: newDescription,
          small_desc: newSmallDesc
        }),
      });

      const data = await response.json();
      console.log(data.message);
      window.location.reload()
    } catch (error) {
      console.error("Error:", error);
    }
  }

  function handleCloseBox() {
    setSelectedBox(null);
    document.body.style.overflow = "auto";
  }

  const deletePage = (id) => {
    fetch("http://localhost:4000/delete/", {
      method: "DELETE",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ _id: id }),
    })
      .then((response) => response.json())
      .then((data) => {
        console.log("Delete a product completed : ", id);
        console.log(data);
        window.location.reload();
        setSelectedBox(null); // Close the box after deleting
      });
  };

  const handleSearch = (e) => {
    setSearchQuery(e.target.value);
    console.log("search: " + searchQuery);
  
    if (e.target.value === "") {
      fetchData();
    } else {
      fetchData(e.target.value);
    }
  };

  async function fetchData(tag) {
    try {
      const response = await fetch(`http://localhost:4000/`);
      const data = await response.json();
      if (tag) {
        const sortedData = data.filter(entry => entry.tags.includes(tag));
        setData(sortedData);
      } else {
        setData(data);
      }
    } catch (error) {
      console.error("Error:", error);
    }
  }

  return (
    <div id="outerShell" >
      <div className="d-flex justify-content-center">
        <h1 className="theme title">WIKI</h1>
      </div>
      <div className="container d-flex justify-content-evenly" id="topBar">
        <input
          className="col"
          type="search"
          placeholder="Filter Pages by TAG"
          value={searchQuery}
          onChange={handleSearch}
        ></input>

        <div className="col"></div>

        <div
          className="col addPage"
          onClick={handleNewPageClick}
        >
          <h4 className="col text-center addPage">Add Page</h4>
          {/* <img className="col" alt="box"></img> */}
        </div>
      </div>
      <div className="my-4"></div>
      <div className="w-100" id="rowBreak"></div>
      <div className="container" id="contentBox">
        {generatePage()}
      </div>
      {/* Modal to display the selected box */}
      {selectedBox && (
        <div className="modal-overlay" onClick={handleCloseModal}>
          <div
            className="modal-content"
            onClick={(event) => event.stopPropagation()}
          >
            <BoxView
              title={selectedBox.title}
              full_desc={selectedBox.full_desc}
              small_desc={selectedBox.small_desc}
              tags={selectedBox.tags}
              img={selectedBox.img}
              onClose={handleCloseBox}
              delete={() => deletePage(selectedBox._id)}
              onSave={(newDescription, newTitle, newSmallDesc) => saveBox(selectedBox._id, newTitle, newDescription, newSmallDesc)}
            />
          </div>
        </div>
      )}
      {/* Modal to display the new page view */}
      {showNewPageModal && (
        <div className="modal-overlay">
          <div className="modal-content">
            <NewPageView onClose={handleCloseModal} />
            <div className="d-flex justify-content-center mt-3">
              <div
                className="w-25 addPage rounded text-center border-3px mx-auto"
                onClick={handleCloseModal}
              >
                Close
              </div>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}

export default App;
