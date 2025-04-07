const express = require("express");
const mongoose = require("mongoose");
const cors = require("cors");
const app = express();
const Data = require("./dataSchema.js");

app.use(express.json());
app.use(cors());

app.use(express.static("public"));

mongoose.connect("mongodb://127.0.0.1:27017/wiki_data", {
  dbName: "wiki_data",
  useNewUrlParser: true,
  useUnifiedTopology: true,
});

const port = process.env.PORT || 4000;
const host = "localhost";

app.listen(port, () => {
  console.log(`App listening at http://%s:%s`, host, port);
});

app.get("/favicon.ico", async (req, resp) => {
  return;
});

app.get("/", async (req, resp) => {

  if (req.params._id === "favicon.ico") {
    return res.status(404)
  }
  const query = {};
  const allData = await Data.Data.find(query);
  //console.log(allData);
  resp.send(allData);
});

app.get("/:id", async (req, resp) => {
  console.log("----------------" + req.params._id);

  if (req.params._id === "favicon.ico") {
    return res.status(404)
  }

  const id = req.params.id;
  const query = { _id: id };
  const oneData = await Data.Data.findOne(query);
  console.log(oneData);
  resp.send(oneData);
});

app.put('/update/:id', async (req, res) => {
  const id = req.params.id;
  const newTitle = req.body.title;
  const newDescription = req.body.full_desc;
  const newSmall_desc = req.body.small_desc
  
  try {
    const result = await Data.Data.findByIdAndUpdate(id, {
      title: newTitle,
      full_desc: newDescription,
      small_desc: newSmall_desc
    });
    
    if (!result) {
      return res.status(404).json({ message: `Box ${id} not found.` });
    }
    
    res.json({ message: `Box ${id} updated successfully.` });
  } catch (err) {
    console.error(err);
    res.status(500).json({ message: `Error while updating box ${id}: ${err}` });
  }
});

app.post("/insert", async (req, res) => {
  const p_id = req.body._id;
  const title = req.body.title;
  const smalldescription = req.body.small_desc;
  const fulldescription = req.body.full_desc;
  const category = req.body.category;
  const image = req.body.img;
  const tags = req.body.tags;

  const formData = new Data.Data({
    _id: p_id,
    title: title,
    img: image,
    small_desc: smalldescription,
    full_desc: fulldescription,
    category: category,
    tags: tags,
  });

  try {
    // await formData.save();
    await Data.Data.create(formData);
    const messageResponse = { message: `Box ${p_id} added correctly` };
    res.send(JSON.stringify(messageResponse));
  } catch (err) {
    console.log("Error while adding a new box:" + err);
  }
});

app.delete("/delete", async (req, res) => {
  console.log("Delete :", req.body);
  try {
    const query = { _id: req.body._id };
    await Data.Data.deleteOne(query);
    const messageResponse = {
      message: `Data ${req.body._id} deleted correctly`,
    };
    res.send(JSON.stringify(messageResponse));
  } catch (err) {
    console.log("Error while deleting :" + req.body._id + " " + err);
  }
});
