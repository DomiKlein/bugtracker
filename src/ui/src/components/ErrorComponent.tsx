import React from "react";
import { Alert } from "react-bootstrap";

type ErrorComponentProps = {
  error: { message: string; stack?: string };
  header?: string;
};
export default class ErrorComponent extends React.Component<ErrorComponentProps> {
  override render() {
    const { message } = this.props.error;

    return (
      <Alert variant="danger">
        <h2>{this.getHeader()}</h2>
        <p>
          <b>Message:</b> {message}
        </p>
        {this.getStackTraceMessage()}
      </Alert>
    );
  }

  private getHeader() {
    const { header } = this.props;
    if (header) {
      return header;
    }
    return "Oops! Something went wrong!";
  }

  private getStackTraceMessage() {
    const { stack } = this.props.error;
    let stackElement = <></>;
    if (stack) {
      stackElement = (
        <p>
          <b>Stacktrace:</b> {stack}
        </p>
      );
    }
    return stackElement;
  }
}
