// @ts-nocheck
import React from "react";
export default class ErrorExample extends React.Component {
  override render() {
    throw Error("Simulated error.");
    return <></>;
  }
}
