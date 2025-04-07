import React, { useState, useEffect, useRef } from "react";
import "bootstrap/dist/css/bootstrap.css";
import items from "./selected_products.json";
import "bootstrap/dist/css/bootstrap.css";
import "./index.css";

const Shop = () => {
  const [cart, setCart] = useState([]);
  const [cartTotal, setCartTotal] = useState(0);
  const [message, setMessage] = useState("");
  const [query, setQuery] = useState("");
  const [paintings, setPaintings] = useState(items);
  const [unfilteredPaintings, setUnfilteredPaintings] = useState(items);

  var orderObject = {
    name: useRef(),
    email: useRef(),
    card: useRef(),
    address1: useRef(),
    address2: useRef(),
    city: useRef(),
    state: useRef(),
    zip: useRef()
  }

  const [order, setOrder] = useState(orderObject);

  var checkoutForm = useRef();
  var confirmationPage = useRef();

  const addToCart = (el) => {
    setCart([...cart, el]);
  };

  const removeFromCart = (el) => {
    let itemFound = false;
    const updatedCart = cart.filter((cartItem) => {
      if (cartItem.id === el.id && !itemFound) {
        itemFound = true;
        return false;
      }
      return true;
    });
    if (itemFound) {
      setCart(updatedCart);
    }
  };

  const cartItems = cart.map((el) => (
    <div key={el.id}>
      <img class="img-fluid" src={el.image} width={30} alt=""/>
      {el.title}${el.price}
    </div>
  ));

  // useEffect(() => {
  //   total();
  // }, [cart]);

  const total = () => {
    let totalVal = 0;
    for (let i = 0; i < cart.length; i++) {
      totalVal += cart[i].price;
    }
    setCartTotal(totalVal);
  };

  function howManyofThis(id) {
    let hmot = cart.filter((cartItem) => cartItem.id === id);
    return hmot.length;
  }

  const listItems = (paintings) => {
    // PRODUCT
    return paintings.map((el) => (
      <div class="row border-top border-bottom" key={el.id}>
        <div class="row main align-items-center">
          <div class="col-2">
            <img class="img-fluid" src={el.image} alt=""/>
          </div>
          <div class="col">
            <div class="row text-muted">{el.title}</div>
            <div class="row">{el.category}</div>
          </div>
          <div class="col">
            <button
              type="button"
              variant="Dark"
              onClick={() => removeFromCart(el)}
            >
              {" "}-{" "}
            </button>{" "}
            <button type="button" variant="Dark" onClick={() => addToCart(el)}>
              {" "}
              +{" "}
            </button>
          </div>
          <div class="col">
            ${el.price} <span class="close">&#10005;</span>
            {howManyofThis(el.id)}
          </div>
        </div>
      </div>
    ));
  };

  const handleSearch = () => {
    console.log("handleSearch: ");
    console.log(query);
    console.log(paintings.length);

    // paintings.map((painting) => {
    //   console.log(painting.title);
    // });

    const results = paintings.filter((eachProduct) => {
      if (query === "") {
        return paintings;
      }
      return eachProduct.title.toLowerCase().includes(query.toLowerCase());
    });

    results.map((results) => {
      console.log(results.title);
    });



    if (query === "") {
      setPaintings(unfilteredPaintings);
    }
    else {
      setPaintings(results);
    }
  };

  function showFormAlert(show) {
    if (show) {
      const node = document.getElementById("formAlert");
      node.setAttribute("class", "col float-start alert alert-danger py-2");
    }
    else {
      const node = document.getElementById("formAlert");
      node.setAttribute("class", "col float-start alert alert-danger py-2 collapse");
    }
  }

  // function openCart() {
  //   const node = document.getElementById("cart");
  //   node.classList.toggle("hiddenDiv");

  //   const store = document.getElementById("store");
  //   store.classList.add("hiddenDiv");
  // }

  // function closeCart() {
  //   const node = document.getElementById("cart");
  //   node.classList.toggle("hiddenDiv");

  //   setPaintings(unfilteredPaintings);
  //   setQuery("");

  //   const store = document.getElementById("store");
  //   store.classList.remove("hiddenDiv");
  // }

  // function createCart() {
  //   return (
  //     <div>
  //       <div class="col-md-8 cart">
  //         <div class="title">
  //           <div class="row">
  //             <div class="col">
  //               <h4>
  //                 <b>The Art Shop</b>
  //               </h4>
  //             </div>
  //             <div class="col align-self-center text-right text-muted">
  //               Products selected {cart.length}
  //             </div>
  //           </div>
  //         </div>
  //         <div>{listItems}</div>
  //       </div>
  //       <div class="float-end">
  //         <p class="mb-0 me-5 d-flex align-items-center">
  //           <span class="small text-muted me-2">Order total:</span>
  //           <span class="lead fw-normal">${cartTotal}</span>
  //         </p>
  //       </div>
  //     </div>
  //   )
  // }

//////////////////////////////////////////////////////////////////////////////////

  function validateFields() {
    let valid = true;

    //check email
    const validEmail = new RegExp(
      /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
    );

    if (!validEmail.test(order.email.current.value)){
      order.email.current.setAttribute("class", "form-control is-invalid");
      valid = false;
    }
    else{
        order.email.current.setAttribute("class", "form-control is-valid");
    }

    //check name
    if (order.name.current.value.length === 0) {
      order.name.current.setAttribute("class", "form-control is-invalid");
      valid = false;
    }
    else {
      order.name.current.setAttribute("class", "form-control is-valid");
    }

    //check card
    const validCard = new RegExp(
      /^[0-9]{4}\-[0-9]{4}\-[0-9]{4}\-[0-9]{4}$/
    );

    if (!validCard.test(order.card.current.value)) {
      order.card.current.setAttribute("class", "form-control is-invalid");
      valid = false;
    }
    else {
      order.card.current.setAttribute("class", "form-control is-valid");
    }

    //check address1, required
    if (order.address1.current.value.length === 0) {
      order.address1.current.setAttribute("class", "form-control is-invalid");
      valid = false;
    }
    else {
      order.address1.current.setAttribute("class", "form-control is-valid");
    }

    //check address2, not required
    if (order.address2.current.value.length !== 0) {
      order.address2.current.setAttribute("class", "form-control is-valid");
    }
    else {
      order.address2.current.setAttribute("class", "form-control");
    }

    //check city
    if (order.city.current.value.length === 0) {
      order.city.current.setAttribute("class", "form-control is-invalid");
      valid = false;
    }
    else {
      order.city.current.setAttribute("class", "form-control is-valid");
    }

    //check zip
    const validZip = new RegExp(
      /^\d{5}$/

    )

    if (!validZip.test(order.zip.current.value)) {
      order.zip.current.setAttribute("class", "form-control is-invalid");
      valid = false;
    }
    else {
      order.zip.current.setAttribute("class", "form-control is-valid");
    }
    return valid;
  }

  function handleCardChange(card) {    
    const cardInput = card.target.value.replace(/\D/g, "");

    let cardWithDashes = "";

    for (let i = 0; i < cardInput.length; i++) {
      if (i % 4 === 0 && i > 0) {
        cardWithDashes += "-";
      }

      cardWithDashes += cardInput[i];
    }

    card.target.value = cardWithDashes;
  }

  const [someVar, setSomeVar] = useState(null);

  const renderData = () => {
      console.log('render');
      setSomeVar(true);
  }

  // function confirmation() {
  //   if(!validateFields()) {
  //     console.log("some fields are invalid");
  //     showFormAlert(true);
  //   }
  //   else {
  //     showFormAlert(false);

  //     checkoutForm.current.setAttribute("class", "collapse hiddenDiv");
  //     confirmationPage.current.setAttribute("class", "card w-50");
  //     renderData();

  //     return showOrderConfirmation();
  //   }

  // }

  function getFieldValue(field) {
    if (!field.current) {
      return "Not Entered";
    }
    else {
      console.log(field.current.value);
      return field.current.value;
    }
  }


  const listItemsPurchased = cart.map((curItem) => (
    <li class="list-group-item col">
      <div class="position-relative">
        <img class="position-relative start-0" src={curItem.image} alt=""></img>
        <p class="position-relative end-0"> Title: {curItem.title} <br></br> Price: {curItem.price}</p>
      </div>
    </li>
  ));

  function refresh() {
    window.location.reload(false);
  }

  function showOrderConfirmation() {

    return (
      <div>
        <h1 class="card-title">Order confirmed!</h1>
            <p class="card-text">Thanks for your order! Here's a summary of your order.</p>

            <h2 class="card-title">Customer information:</h2>

            <ul class="list-group list-group-flush">
              <li class="list-group-item">
                <p class="float-start">Name:</p>
                <p class="float-end">{getFieldValue(order.name)}</p>
              </li>
              <li class="list-group-item">
                <p class="float-start">E-mail:</p>
                <p class="float-end">{getFieldValue(order.email)}</p>
              </li>
              <li class="list-group-item">
                <p class="float-start">Card:</p>
                <p class="float-end"> ****-****-****-{getFieldValue(order.card).slice(-4)}</p>
              </li>
              <li class="list-group-item">
                <p class="float-start">Address:</p>
                <p class="float-end"> {getFieldValue(order.address1)}</p>
              </li>
              <li class="list-group-item">
                <p class="float-start">Secondary Address:</p>
                <p class="float-end"> {getFieldValue(order.address2)}</p>
              </li>
              <li class="list-group-item">
                <p class="float-start">City:</p>
                <p class="float-end"> {getFieldValue(order.city)}</p>
              </li>
              <li class="list-group-item">
                <p class="float-start">State:</p>
                <p class="float-end"> {getFieldValue(order.state)}</p>
              </li>
              <li class="list-group-item">
                <p class="float-start">Zip code:</p>
                <p class="float-end"> {getFieldValue(order.zip)}</p>
              </li>

              <h2 class="card-title pt-3">Items purchased:</h2>
            
              <ul class ="list-group list-group-flush">
                {listItemsPurchased}
              </ul>

              <button type="button" onClick={refresh.bind()} class="btn btn-outline-primary rounded">Back to start</button>
            </ul>
          </div>
    )
  }

  return (
    <div>
      <div class="input-group w-50 py-2 ps-3">
        <input
          type="search"
          class="form-control rounded"
          placeholder="Search"
          aria-label="Search"
          aria-describedby="search-addon"
          value={query}
          onChange={(e) => setQuery(e.target.value)}
        />
        <button
          type="button"
          class="btn btn-outline-primary"
          onClick={handleSearch}
        >
          Search
        </button>
      </div>

      {listItems(paintings)}
      <div>Items in Cart :</div>
      <div>{cartItems}</div>
      <div>Order total to pay :{cartTotal}</div>
    </div>
  );
};


export default Shop;