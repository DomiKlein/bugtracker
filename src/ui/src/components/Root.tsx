import React from "react";
import { Col, Container, Row } from "react-bootstrap";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import ErrorBoundary from "./ErrorBoundary";
import PageNotFound from "./pages/error/PageNotFound";
import HomePage from "./pages/home/HomePage";
import TicketsPage from "./pages/tickets/TicketsPage";
import SideMenu from "./SideMenu";
import TopMenu from "./TopMenu";

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
                  <Switch>
                    <Route path="/tickets" component={TicketsPage} />
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
