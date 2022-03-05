import { Alert } from "react-bootstrap";

type ErrorComponentProps = {
  message?: string;
  stack?: string;
  header?: string;
};

/** Represents an error message */
export default function ErrorComponent(props: ErrorComponentProps) {
  const { message, stack, header } = props;

  return (
    <Alert variant="danger">
      <h2>{header ? header : "Oops! Something went wrong!"}</h2>
      <p>
        <b>Message:</b> {message ? message : "Unknown error"}
      </p>
      <details style={{ whiteSpace: "pre-wrap" }}>
        {stack ? (
          <p>
            <b>Stacktrace:</b> {stack}
          </p>
        ) : (
          <></>
        )}
      </details>
    </Alert>
  );
}
