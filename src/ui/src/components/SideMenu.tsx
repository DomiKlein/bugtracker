import React from "react";
import { Container, Nav, Navbar } from "react-bootstrap";
import { Bug } from "react-bootstrap-icons";

/** Represents the side menu */
export default class SideMenu extends React.Component {
  override render() {
    return (
      <Navbar
        id="side-menu"
        className="align-items-start"
        bg="dark"
        variant="dark"
      >
        <Nav className="flex-column">
          <Container>
            <Navbar bg="dark" variant="dark">
              <Navbar.Brand>
                <Bug /> Bugtracker
              </Navbar.Brand>
            </Navbar>
          </Container>
          <Nav.Link href="/">Home</Nav.Link>
          <Nav.Link href="/tickets">Tickets</Nav.Link>
        </Nav>
      </Navbar>
    );
  }
}
