import BasePage from "../BasePage";

/** Represents the Tickets page */
export default class TicketsPage extends BasePage {
  override loadData(): Promise<any> {
    return this.serviceClient.getAllTickets();
  }

  override renderContent() {
    return <h1>Tickets</h1>;
  }
}
