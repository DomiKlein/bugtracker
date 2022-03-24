import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { AuthenticationResponse, User } from "../../DatabaseEntities";
import ServiceClient from "../ServiceClient";
import type { RootState } from "./Store";

// Define a type for the slice state
interface UserInfoState {
  user?: User;
  mainToken?: string;
  refreshToken?: string;
}

// Define the initial state
const initialState: UserInfoState = {};

export const userInfoSlice = createSlice({
  name: "userInfo",
  initialState,
  reducers: {
    login: (state, action: PayloadAction<AuthenticationResponse>) => {
      state.user = action.payload.user;
      state.mainToken = action.payload.authenticationToken;
      state.refreshToken = action.payload.refreshToken;

      ServiceClient.Instance.setAuthTokens(state.mainToken, state.refreshToken);
    },
    refresh: (
      state,
      action: PayloadAction<{
        authToken: string;
        refreshToken: string;
      }>
    ) => {
      state.mainToken = action.payload.authToken;
      state.refreshToken = action.payload.refreshToken;

      ServiceClient.Instance.setAuthTokens(state.mainToken, state.refreshToken);
    },
    logout: (state) => {
      ServiceClient.Instance.setAuthTokens();
      // state will be cleared in root reducer
      if (Object.keys(state).length !== 0) {
        throw new Error("Logout failed. State is not empty!");
      }
    },
  },
});

export const { login, logout } = userInfoSlice.actions;

// Selectors
export const selectCurrentUser = (state: RootState) => state.userInfo.user;
export const selectMainAuthToken = (state: RootState) =>
  state.userInfo.mainToken;
export const selectRefreshAuthToken = (state: RootState) =>
  state.userInfo.refreshToken;

export default userInfoSlice.reducer;
