import "bootstrap/dist/css/bootstrap.min.css"; // Needed to apply Bootstrap CSS to DOM
import ReactDOM from "react-dom";
import Entry from "./components/Entry";
import "./styles/root.less"; // Needed to apply CSS to DOM

ReactDOM.render(<Entry />, document.getElementById("root"));
