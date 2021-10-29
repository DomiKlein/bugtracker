import React, { Suspense } from "react";
import { Col, Container, Row, Spinner } from "react-bootstrap";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import ErrorBoundary from "./error/ErrorBoundary";
import SideMenu from "./menu/SideMenu";
import TopMenu from "./menu/TopMenu";
import PageNotFound from "./pages/error/PageNotFound";
import HomePage from "./pages/home/HomePage";
import TicketsPage from "./pages/tickets/TicketsPage";

/** Creates a loader*/
function Loader(): JSX.Element {
  return (
    <Container fluid>
      <Spinner animation="border" role="status" />
    </Container>
  );
}

/** Represents the whole site */
export default class Root extends React.Component {
  override render() {
    return (
      <Router>
        <Container fluid>
          <Row>
            <Col className="noPadding" xs={2}>
              <SideMenu />
            </Col>
            <Col className="noPadding">
              <TopMenu />
              <Container fluid id="main-container">
                <ErrorBoundary>
                  <Suspense fallback={Loader}>
                    <Switch>
                      <Route path="/tickets" component={TicketsPage} />
                      <Route exact path="/" component={HomePage} />
                      <Route path="/loader" component={Loader} />
                      <Route component={PageNotFound} />
                    </Switch>
                  </Suspense>
                </ErrorBoundary>
              </Container>
            </Col>
          </Row>
        </Container>
      </Router>
    );
  }
}
