import React, { ErrorInfo } from "react";
import ErrorComponent from "./ErrorComponent";

type ErrorBoundaryState = {
  error?: Error;
  errorInfo?: ErrorInfo;
};

/** The error boundary to catch errors of subcomponents */
export default class ErrorBoundary extends React.Component<
  any,
  ErrorBoundaryState
> {
  constructor(props: any) {
    super(props);
    this.state = {};
  }

  override componentDidCatch(error: Error, errorInfo: ErrorInfo) {
    this.setState({
      error: error,
      errorInfo: errorInfo,
    });
  }

  override render() {
    if (this.state.errorInfo) {
      // Error path
      return (
        <ErrorComponent
          message={this.state.error && this.state.error.toString()}
          stack={this.state.errorInfo.componentStack}
        />
      );
    }
    // Normally, just render children
    return this.props.children;
  }
}
