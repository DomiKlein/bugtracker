import "bootstrap/dist/css/bootstrap.min.css"; // Needed to apply Bootstrap CSS to DOM
import React from "react";
import ReactDOM from "react-dom";
import Root from "./components/Root";
import "./styles/root.less"; // Needed to apply CSS to DOM

ReactDOM.render(React.createElement(Root), document.getElementById("root"));
