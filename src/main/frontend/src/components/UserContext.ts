import { createContext } from "react";
import { User } from "../DatabaseEntities";

interface IUserContext {
  user?: User;
  mainToken?: string;
  refreshToken?: string;
}

const userContext = createContext<IUserContext>({});

export { userContext };
