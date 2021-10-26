import React from "react";
import { Nav, Navbar } from "react-bootstrap";

/** Represents the top menu */
export default class TopMenu extends React.Component {
  override render() {
    return (
      <Navbar bg="light" variant="light">
        <Nav.Link href="/causeError">Cause Error</Nav.Link>
      </Navbar>
    );
  }
}
