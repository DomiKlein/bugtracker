import { useState } from "react";
import { User } from "../DatabaseEntities";
import App from "./App";
import LoginPage from "./login/LoginPage";
import { userContext } from "./UserContext";

export default function Entry() {
  const [user, setUser] = useState<User | undefined>();
  const [mainToken, setMainToken] = useState<string | undefined>();
  const [refreshToken, setRefreshToken] = useState<string | undefined>();

  const setContextValues = (
    user: User,
    mainToken: string,
    refreshToken: string
  ) => {
    setUser(user);
    setMainToken(mainToken);
    setRefreshToken(refreshToken);
  };
  const context = { user, mainToken, refreshToken, setUser: setContextValues };

  return (
    <userContext.Provider value={context}>
      {user ? <App /> : <LoginPage />}
    </userContext.Provider>
  );
}
