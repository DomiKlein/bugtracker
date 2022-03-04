import { useState } from "react";
import { User } from "../DatabaseEntities";
import App from "./App";
import LoginPage from "./login/LoginPage";
import { userContext } from "./UserContext";

export default function Entry() {
  const [user] = useState<User | undefined>();
  const [mainToken] = useState<string | undefined>();
  const [refreshToken] = useState<string | undefined>();

  const context = { user, mainToken, refreshToken };

  return (
    <userContext.Provider value={context}>
      {user ? <App /> : <LoginPage />}
    </userContext.Provider>
  );
}
