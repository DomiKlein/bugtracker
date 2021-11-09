import React from "react";
import { Col, Container, Row } from "react-bootstrap";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import ServiceClient from "../core/ServiceClient";
import ErrorBoundary from "./error/ErrorBoundary";
import SideMenu from "./menu/SideMenu";
import TopMenu from "./menu/TopMenu";
import HomePage from "./pages/home/HomePage";
import PageNotFound from "./pages/PageNotFound";
import TicketsPage from "./pages/tickets/TicketsPage";

/** Represents the whole site */
export default class Root extends React.Component {
  readonly serviceClient = new ServiceClient();
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
                  <Switch>
                    <Route path="/tickets">
                      <TicketsPage serviceClient={this.serviceClient} />
                    </Route>
                    <Route exact path="/" component={HomePage} />
                    <Route component={PageNotFound} />
                  </Switch>
                </ErrorBoundary>
              </Container>
            </Col>
          </Row>
        </Container>
      </Router>
    );
  }
}
