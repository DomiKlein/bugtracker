import { useEffect, useState } from "react";
import { Ticket } from "../../../DatabaseEntities";
import { useServiceClient } from "../Hooks";
import Loader from "../Loader";

/** Represents the Tickets page */
export default function TicketsPage() {
  const [tickets, setTickets] = useState<Ticket[] | null>(null);
  const serviceClient = useServiceClient();

  useEffect(() => {
    serviceClient.getAllTickets().then((fetchedTickets) => {
      setTickets(fetchedTickets.data);
    });
  }, []);
  if (!tickets) {
    return <Loader />;
  }

  return (
    <>
      <h1>Tickets</h1>
      {tickets.length > 0 ? (
        <ul>
          {tickets.map((ticket) => (
            <li key={ticket.id}>{ticket.title}</li>
          ))}
        </ul>
      ) : (
        <div>No tickets available</div>
      )}
    </>
  );
}
