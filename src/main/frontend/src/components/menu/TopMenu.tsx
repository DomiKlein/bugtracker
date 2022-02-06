import { useState } from "react";
import { Button, Dropdown, Navbar, NavItem, NavLink } from "react-bootstrap";
import { PersonFill, PlusSquare } from "react-bootstrap-icons";
import TicketCreateForm from "../pages/tickets/TicketCreateForm";

/** Represents the top menu */
export default function TopMenu() {
  const [showTicketCreationDialog, setShowTicketCreationDialog] =
    useState(false);
  return (
    <>
      <Navbar id="top-menu" bg="light" variant="light">
        <div className="me-auto" />
        <Button
          variant="primary"
          onClick={() => setShowTicketCreationDialog(true)}
        >
          <PlusSquare />
          &nbsp; Create a ticket
        </Button>
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
      {showTicketCreationDialog && (
        <TicketCreateForm onClose={() => setShowTicketCreationDialog(false)} />
      )}
    </>
  );
}
