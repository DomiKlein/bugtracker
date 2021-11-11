import { Alert } from "react-bootstrap";

type ErrorComponentProps = {
  error: { message: string; stack?: string };
  header?: string;
};

/** Represents an error message */
export default function ErrorComponent(props: ErrorComponentProps) {
  const { error, header } = props;
  const { message, stack } = error;

  return (
    <Alert variant="danger">
      <h2>{header ? header : "Oops! Something went wrong!"}</h2>
      <p>
        <b>Message:</b> {message}
      </p>
      {props.error.stack ? (
        <p>
          <b>Stacktrace:</b> {stack}
        </p>
      ) : (
        <></>
      )}
    </Alert>
  );
}
