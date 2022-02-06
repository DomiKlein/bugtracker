import { useState } from "react";
import { Button, Form, Modal } from "react-bootstrap";

interface TicketCreateFormProps {
  onClose: () => any;
}

export default function TicketCreateForm(props: TicketCreateFormProps) {
  const [show, setShow] = useState(true);
  const [title, setTitle] = useState<string>();
  const [description, setDescription] = useState<string>();

  const handleClose = () => {
    setShow(false);
    props.onClose();
  };

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
      </Modal.Dialog>

      <Modal.Footer>
        <Button
          variant="primary"
          type="submit"
          onClick={() =>
            alert(`Title : ${title} and Description : ${description}`)
          }
        >
          Submit
        </Button>
      </Modal.Footer>
    </Modal>
  );
}
