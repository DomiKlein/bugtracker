import ErrorComponent from "../error/ErrorComponent";

/**
 * Represents an error message which tells the user
 * that the requested page does not exist
 */
export default function PageNotFound() {
  const message =
    "Looks like the page you requested does not exist. If you think this is an error, please send an error report.";
  return <ErrorComponent header={"404 Page not found"} message={message} />;
}
