import { Col, Container, Row } from "react-bootstrap";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import PageNotFound from "./util/PageNotFound";
import ErrorBoundary from "./error/ErrorBoundary";
import SideMenu from "./menu/SideMenu";
import TopMenu from "./menu/TopMenu";
import HomePage from "./home/HomePage";
import TicketsPage from "./tickets/TicketsPage";

/** Represents the whole site */
export default function App() {
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
