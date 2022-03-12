import { Col, Container, Row } from "react-bootstrap";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import PageNotFound from "./util/PageNotFound";
import ErrorBoundary from "./error/ErrorBoundary";
import SideMenu from "./menu/SideMenu";
import TopMenu from "./menu/TopMenu";
import HomePage from "./home/HomePage";
import TicketsPage from "./tickets/TicketsPage";
import { useAppSelector } from "../core/Hooks";
import { selectCurrentUser } from "../core/store/UserInfoSlice";
import LoginPage from "./login/LoginPage";

/** Represents the whole site */
export default function App() {
  const user = useAppSelector(selectCurrentUser);
  console.log("Current user: " + user);
  if (!user) {
    return <LoginPage />;
  }

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
