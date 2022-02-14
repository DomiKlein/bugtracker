import { useEffect, useState } from "react";
import { Button, Table } from "react-bootstrap";
import { Trash } from "react-bootstrap-icons";
import { Ticket } from "../../../DatabaseEntities";
import { useServiceClient } from "../../core/Hooks";
import Loader from "../../core/Loader";

/** Represents the Tickets page */
export default function TicketsPage() {
  const [tickets, setTickets] = useState<Ticket[] | null>(null);
  const serviceClient = useServiceClient();

  useEffect(() => {
    serviceClient.getAllTickets().then((fetchedTickets) => {
      setTickets(fetchedTickets.data);
    });
  }, []);

  const handleDelete = async (ticketId: number) => {
    const res = await serviceClient.deleteTicket(ticketId);
    const deletedTicket = res.data;
    if (deletedTicket) {
      setTickets(tickets!.filter((t) => t.ticketId !== ticketId));
    }
  };

  if (!tickets) {
    return <Loader />;
  }

  return (
    <>
      <h1>Tickets</h1>
      {tickets.length > 0 ? (
        <Table striped bordered hover>
          <thead>
            <tr>
              <th>Title</th>
              <th>Description</th>
              <th>Creator</th>
              <th>Assignee</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {tickets.map((ticket) => (
              <tr>
                <td>{ticket.title}</td>
                <td>{ticket.description}</td>
                <td>{ticket.creator.username}</td>
                <td>{ticket.assignee?.username}</td>
                <td>
                  <Button
                    variant="outline-secondary"
                    size="sm"
                    onClick={() => handleDelete(ticket.ticketId!)}
                  >
                    <Trash />
                  </Button>
                </td>
              </tr>
            ))}
          </tbody>
        </Table>
      ) : (
        <div>No tickets available</div>
      )}
    </>
  );
}
