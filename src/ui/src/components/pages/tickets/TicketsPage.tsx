import BasePage from "../BasePage";

/** Represents the Tickets page */
export default class TicketsPage extends BasePage {
  override loadData(): Promise<any> {
    return new Promise((resolve) => {
      // TODO: Load tickets via Axios
      setTimeout(resolve, 5000);
    });
  }

  override renderContent() {
    return <h1>Tickets</h1>;
  }
}
