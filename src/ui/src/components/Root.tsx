import React from "react";
import { Col, Container, Nav, Navbar, Row } from "react-bootstrap";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import ErrorBoundary from "./ErrorBoundary";
import ErrorExample from "./pages/error/ErrorExample";
import HomePage from "./pages/home/HomePage";
import SideMenu from "./pages/SideMenu";
import TicketsPage from "./pages/tickets/TicketsPage";

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
              <Navbar bg="light" variant="light">
                <Nav.Link href="/">Home</Nav.Link>
                <Nav.Link href="/tickets">Tickets</Nav.Link>
              </Navbar>

              <Container fluid id="main-container">
                <ErrorBoundary>
                  <Switch>
                    <Route path="/error" component={ErrorExample} />
                    <Route path="/tickets" component={TicketsPage} />
                    <Route exact path="/" component={HomePage} />
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
