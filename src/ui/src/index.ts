import "./styles/root.less"; // Needed to apply CSS to DOM
import "bootstrap/dist/css/bootstrap.min.css"; // Needed to apply Bootstrap CSS to DOM
import { User } from "./Types";
import ReactDOM from "react-dom";
import Root from "./components/Root";
import React from "react";

console.log("Hello world!");
const user: User = {
  userId: 0,
};
console.log(user);

ReactDOM.render(React.createElement(Root), document.getElementById("root"));
