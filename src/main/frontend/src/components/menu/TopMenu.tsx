import { useState } from "react";
import { Button, Dropdown, Navbar, NavItem, NavLink } from "react-bootstrap";
import {
  DoorClosed,
  Gear,
  PersonFill,
  PlusSquare,
} from "react-bootstrap-icons";
import { useDispatch } from "react-redux";
import { userInfoSlice } from "../../core/store/UserInfoSlice";
import TicketCreateForm from "../tickets/TicketCreateForm";

/** Represents the top menu */
export default function TopMenu() {
  const [showTicketCreationDialog, setShowTicketCreationDialog] =
    useState(false);
  const dispatch = useDispatch();
  const iconSize = 20;

  const logout = () => dispatch(userInfoSlice.actions.logout());

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
            <Dropdown.Item>
              <Gear size={iconSize} />
              Settings
            </Dropdown.Item>
            <Dropdown.Divider />
            <Dropdown.Header>Logout</Dropdown.Header>
            <Dropdown.Item onClick={logout}>
              <DoorClosed size={iconSize} />
              Logout
            </Dropdown.Item>
          </Dropdown.Menu>
        </Dropdown>
      </Navbar>
      {showTicketCreationDialog && (
        <TicketCreateForm onClose={() => setShowTicketCreationDialog(false)} />
      )}
    </>
  );
}
