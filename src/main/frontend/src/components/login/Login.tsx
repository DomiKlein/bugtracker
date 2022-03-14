import { useState } from "react";
import { Button, Col, Container, Form, Row } from "react-bootstrap";
import { Bug } from "react-bootstrap-icons";
import { useDispatch } from "react-redux";
import { useServiceClient } from "../../core/Hooks";
import { userInfoSlice } from "../../core/store/UserInfoSlice";

export default function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const serviceClient = useServiceClient();
  const dispatch = useDispatch();

  const onSubmit = () => {
    serviceClient.login(username, password).then((res) => {
      dispatch(userInfoSlice.actions.login(res.data));

      const { authenticationToken } = res.data;
      serviceClient.setAuthToken(authenticationToken);
    });
  };

  return (
    <Container id="login-container" fluid className="my-auto">
      <Row className="justify-content-center">
        <Col xs={3}>
          <h2>
            <Bug /> Bugtracker
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col xs={3}>
          <Form>
            <Form.Group className="mt-3 mb-3">
              <Form.Label>Username</Form.Label>
              <Form.Control
                type="text"
                value={username}
                placeholder="Your username here..."
                onChange={(e) => setUsername(e.target.value)}
                required
              ></Form.Control>
            </Form.Group>

            <Form.Group className="mb-3">
              <Form.Label>Password</Form.Label>
              <Form.Control
                type="password"
                value={password}
                placeholder="Type your password"
                onChange={(e) => setPassword(e.target.value)}
                required
              ></Form.Control>
            </Form.Group>

            <Form.Group className="mb-3" style={{ textAlign: "center" }}>
              <Button variant="primary" onClick={onSubmit}>
                Login
              </Button>
            </Form.Group>
          </Form>
        </Col>
      </Row>
    </Container>
  );
}
