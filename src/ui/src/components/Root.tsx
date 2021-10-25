import React from "react";
import { Col, Container, Nav, Navbar, Row } from "react-bootstrap";
import {
  BrowserRouter as Router,
  Redirect,
  Route,
  Switch,
} from "react-router-dom";
import HomePage from "./pages/home/HomePage";
import SideMenu from "./pages/SideMenu";
import TicketsPage from "./pages/tickets/TicketsPage";

export default class Root extends React.Component {
  readonly start = "/";

  override render() {
    return (
      <Router>
        <Container fluid>
          <Row>
            <Col className="noPadding" xs={2}>
              <SideMenu />
            </Col>
            <Col className="noPadding">
              <Container>
                <Navbar bg="light" variant="light">
                  <Nav.Link href="/">Home</Nav.Link>
                  <Nav.Link href="/tickets">Tickets</Nav.Link>
                </Navbar>
                <Container fluid>
                  <Switch>
                    <Route path="/">
                      <HomePage />
                    </Route>
                    <Route path="/tickets">
                      <TicketsPage />
                    </Route>
                    <Route>
                      <Redirect to="/" />
                    </Route>
                  </Switch>
                </Container>
              </Container>
            </Col>
          </Row>
        </Container>
      </Router>
    );
  }
}
