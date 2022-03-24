import { memo, useEffect } from "react";
import { Col, Container, Row } from "react-bootstrap";
import { useDispatch, useSelector } from "react-redux";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import ServiceClient from "../core/ServiceClient";
import {
  selectCurrentUser,
  selectMainAuthToken,
  selectRefreshAuthToken,
  userInfoSlice,
} from "../core/store/UserInfoSlice";
import ErrorBoundary from "./error/ErrorBoundary";
import HomePage from "./home/HomePage";
import Login from "./login/Login";
import SideMenu from "./menu/SideMenu";
import TopMenu from "./menu/TopMenu";
import TicketsPage from "./tickets/TicketsPage";
import PageNotFound from "./util/PageNotFound";

/** Represents the whole site */
function App() {
  console.log("Rendering APP");
  const user = useSelector(selectCurrentUser);

  if (!user) {
    return <Login />;
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

const MemoizedApp = memo(App);

export default function WrappedApp() {
  const mainAuthToken = useSelector(selectMainAuthToken);
  const refreshAuthToken = useSelector(selectRefreshAuthToken);
  const dispatch = useDispatch();

  ServiceClient.Instance.setAuthTokens(mainAuthToken, refreshAuthToken);

  const updateTokens = (authToken?: string, refreshToken?: string) => {
    if (!authToken || !refreshToken) {
      console.log("LOGING OUT OH NO!");
      // dispatch(userInfoSlice.actions.logout());
      return;
    }

    console.log("UPDATED TOKEN YAY");
    dispatch(userInfoSlice.actions.refresh({ authToken, refreshToken }));
  };

  useEffect(() => {
    setTimeout(() => {
      // do something 1 sec after clicked has changed
      ServiceClient.Instance.getAllUsers();
      console.log("DOING SOMETHING");
    }, 63000);
  });

  ServiceClient.Instance.setUpdateCallback(updateTokens);
  console.log("Render WrappedApp");

  return <MemoizedApp />;
}
