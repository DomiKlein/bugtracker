import React from "react";
import {
  Button,
  Dropdown,
  Nav,
  Navbar,
  NavItem,
  NavLink,
} from "react-bootstrap";
import { PersonFill, PlusSquare } from "react-bootstrap-icons";

/** Represents the top menu */
export default class TopMenu extends React.Component<any> {
  override render() {
    return (
      <Navbar id="top-menu" bg="light" variant="light">
        <div className="me-auto" />
        <Nav.Link href="/tickets#create">
          <Button variant="primary">
            <PlusSquare />
            &nbsp; Create a ticket
          </Button>
        </Nav.Link>
        <Dropdown as={NavItem}>
          <Dropdown.Toggle variant="light" as={NavLink}>
            <PersonFill size={35} color="gray" />
          </Dropdown.Toggle>

          <Dropdown.Menu>
            <Dropdown.Header>User Settings</Dropdown.Header>
            <Dropdown.Item>Settings</Dropdown.Item>
          </Dropdown.Menu>
        </Dropdown>
      </Navbar>
    );
  }
}
