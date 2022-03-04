import { useEffect, useState } from "react";
import { Button, Form, Modal } from "react-bootstrap";
import { Ticket, User } from "../../DatabaseEntities";
import { useServiceClient } from "../../core/Hooks";
import Loader from "../util/Loader";

interface TicketCreateFormProps {
  onClose: () => any;
}

export default function TicketCreateForm(props: TicketCreateFormProps) {
  const [show, setShow] = useState(true);
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [users, setUsers] = useState<User[] | null>(null);
  const [creatorUsername, setCreatorUsername] = useState("");
  const serviceClient = useServiceClient();

  useEffect(() => {
    serviceClient.getAllUsers().then((fetchedUsers) => {
      setUsers(fetchedUsers.data);
    });
  }, []);

  const handleClose = () => {
    setShow(false);
    props.onClose();
  };

  const onSubmit = () => {
    const user = users!.find((u) => u.username === creatorUsername)!;
    const ticket: Ticket = {
      creator: user,
      title: title,
      description: description,
    };
    serviceClient.createTicket(ticket);
  };

  if (!users) {
    return (
      <Modal centered show={show} onHide={handleClose} size="lg">
        <Modal.Header closeButton>
          <Modal.Title>Create a new ticket</Modal.Title>
        </Modal.Header>
        <Loader />
      </Modal>
    );
  }

  return (
    <Modal centered show={show} onHide={handleClose} size="lg">
      <Modal.Header closeButton>
        <Modal.Title>Create a new ticket</Modal.Title>
      </Modal.Header>

      <Modal.Dialog id="ticket-create-dialog">
        <Form.Group className="mb-3">
          <Form.Control
            size="lg"
            type="text"
            placeholder="Enter title here..."
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            required
          />
        </Form.Group>
        <Form.Group className="mb-3">
          <Form.Label>Description</Form.Label>
          <Form.Control
            as="textarea"
            rows={6}
            placeholder="Put your detailed description here..."
            value={description}
            onChange={(e) => setDescription(e.target.value)}
            required
          />
        </Form.Group>
        <Form.Select onChange={(e) => setCreatorUsername(e.target.value)}>
          <option>Select an creator</option>
          {users.map((user) => (
            <option value={user.username}>
              {user.lastName}, {user.firstName} ({user.username})
            </option>
          ))}
        </Form.Select>
      </Modal.Dialog>

      <Modal.Footer>
        <Button variant="primary" type="submit" onClick={onSubmit}>
          Submit
        </Button>
      </Modal.Footer>
    </Modal>
  );
}
