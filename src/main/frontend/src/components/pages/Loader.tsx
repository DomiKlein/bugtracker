import { Spinner } from "react-bootstrap";

/** Creates a loader which can be used as placeholder, when fetching data. */
export default function Loader() {
  return (
    <div className="d-flex justify-content-center">
      <Spinner animation="border" role="status" />
    </div>
  );
}
