import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { AuthenticationResponse, User } from "../../DatabaseEntities";
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
    },
    logout: (state) => {
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

export default userInfoSlice.reducer;
