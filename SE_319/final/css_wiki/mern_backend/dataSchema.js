const mongoose = require("mongoose");

const ReactFormDataSchema = new mongoose.Schema({
  _id: { type: Number },
  title: {type: String},
  img: { type: String },
  small_desc: { type: String },
  full_desc: { type: String },
  tags: { type: String },
}, { collection: "data" });

ReactFormDataSchema.pre("save", async function (next) {
  if (this.isNew) {
    const count = await mongoose.model("data").find().sort({_id:-1}).limit(1);
    const lastId = count.length > 0 ? parseInt(count[0]._id) : 0;
    this._id = lastId + 1;
  }
  next();
});



const Data = mongoose.model("data", ReactFormDataSchema);

module.exports = {
  Data,
};