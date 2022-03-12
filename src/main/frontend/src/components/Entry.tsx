import { useState } from "react";
import { User } from "../DatabaseEntities";
import App from "./App";
import LoginPage from "./login/LoginPage";

export default function Entry() {
  const [user] = useState<User | undefined>();

  return <>{user ? <App /> : <LoginPage />}</>;
}
