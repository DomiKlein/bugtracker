import React from "react";
import { Alert } from "react-bootstrap";

type ErrorComponentProps = {
  error: { message: string; stack?: string };
  header?: string;
};

/** Represents an error message */
export default class ErrorComponent extends React.Component<ErrorComponentProps> {
  override render() {
    const { message } = this.props.error;

    return (
      <Alert variant="danger">
        <h2>{this.getHeader()}</h2>
        <p>
          <b>Message:</b> {message}
        </p>
        {this.getStackTrace()}
      </Alert>
    );
  }

  /** Returns the text which should be put in the header */
  private getHeader() {
    const { header } = this.props;
    if (header) {
      return header;
    }
    return "Oops! Something went wrong!";
  }
  /**
   * Returns an element which represents the stack trace.
   * May return an empty component if no stack trace should be shown.
   */
  private getStackTrace() {
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
