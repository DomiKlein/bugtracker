import React from "react";
import { Alert } from "react-bootstrap";

type ErrorComponentProps = {
  error: { message: string; stack: string };
};
export default class ErrorComponent extends React.Component<ErrorComponentProps> {
  override render() {
    const { message, stack } = this.props.error;
    return (
      <Alert variant="danger">
        <h2>Oops! Something went wrong!</h2>
        <p>
          <b>Message:</b> {message}
        </p>
        <p>
          <b>Stacktrace:</b> {stack}
        </p>
      </Alert>
    );
  }
}
