import React from "react";
import { Spinner } from "react-bootstrap";
import ServiceClient from "../../core/ServiceClient";

type PageProps = {
  serviceClient: ServiceClient;
};

type PageState = {
  dataLoaded: boolean;
};

/** Pages should extends this class.  */
export default abstract class BasePage extends React.Component<
  PageProps,
  PageState
> {
  constructor(props: any) {
    super(props);
    this.state = {
      dataLoaded: false,
    };
  }

  /** Renders the page content. */
  protected abstract renderContent(): JSX.Element;

  /** Method for loading data. Subclasses shoud override this, if they need data for rendering. */
  protected loadData(): Promise<any> {
    return Promise.resolve();
  }

  override componentDidMount() {
    this.loadData().then(() => this.setState({ dataLoaded: true }));
  }

  protected get serviceClient() {
    return this.props.serviceClient;
  }
  override render() {
    if (!this.state.dataLoaded) {
      return <Loader />;
    }
    return this.renderContent();
  }
}

/** Creates a loader*/
function Loader() {
  return (
    <div className="d-flex justify-content-center">
      <Spinner animation="border" role="status" />
    </div>
  );
}
