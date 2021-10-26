import React from "react";
import ErrorComponent from "./ErrorComponent";

type ErrorBoundaryState = {
  error?: { message: string; stack: string };
  info?: { componentStack: string };
};

/** The error boundary to catch errors of subcomponents */
export default class ErrorBoundary extends React.Component<
  any,
  ErrorBoundaryState
> {
  override state: ErrorBoundaryState;

  constructor(props: any) {
    super(props);
    this.state = {};
  }

  static getDerivedStateFromError(error: any) {
    return { error };
  }

  override componentDidCatch = (error: any, info: any) => {
    this.setState({ error, info });
  };

  override render() {
    const { children } = this.props;
    const { error } = this.state;

    return error ? <ErrorComponent error={error} /> : children;
  }
}
