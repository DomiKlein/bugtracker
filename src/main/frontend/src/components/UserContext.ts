import { createContext } from "react";
import { User } from "../DatabaseEntities";

interface IUserContext {
  user?: User;
  mainToken?: string;
  refreshToken?: string;
  setUser: (user: User, mainToken: string, refreshToken: string) => void;
}

const userContext = createContext<IUserContext>({
  // @ts-ignore
  setUser: (user: User, mainToken: string, refreshToken: string) => {
    // Intentionally empty, will be overridden by used class
  },
});

export { userContext };
