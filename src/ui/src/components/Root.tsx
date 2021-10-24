import React from "react";
import BasicPage from "./pages/BasicPage";
import HomePage from "./pages/home/HomePage";

export default class Root extends React.Component {
  override render() {
    return <BasicPage content={<HomePage />} />;
  }
}
