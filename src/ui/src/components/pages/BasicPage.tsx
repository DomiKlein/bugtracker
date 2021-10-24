import React from "react";
import { Col, Container, Nav, Navbar, Row } from "react-bootstrap";
import SideMenu from "./SideMenu";

export default class BasicPage extends React.Component<
  { content: React.ReactElement },
  {}
> {
  override render() {
    return (
      <Container fluid>
        <Row>
          <Col className="noPadding" xs={2}>
            <SideMenu />
          </Col>
          <Col className="noPadding">
            <Container>
              <Navbar bg="light" variant="light">
                <Nav.Link href="/">Home</Nav.Link>
                <Nav.Link href="/about">About</Nav.Link>
              </Navbar>
              <Container fluid>{this.props.content}</Container>
            </Container>
          </Col>
        </Row>
      </Container>
    );
  }
}
