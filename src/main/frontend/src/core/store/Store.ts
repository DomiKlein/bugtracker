import {
  AnyAction,
  combineReducers,
  configureStore,
  Reducer,
} from "@reduxjs/toolkit";
import {
  FLUSH,
  PAUSE,
  PERSIST,
  persistReducer,
  persistStore,
  PURGE,
  REGISTER,
  REHYDRATE,
} from "redux-persist";
import storage from "redux-persist/lib/storage";
import userInfoReducer from "./UserInfoSlice";

/** Config for store persistor */
const persistConfig = {
  key: "root",
  storage,
};

// Creating reducers, bundling them and enabled persisting
const reducers = combineReducers({ userInfo: userInfoReducer });
const persistedReducer = persistReducer(persistConfig, reducers);
const rootReducer: Reducer = (state: RootState, action: AnyAction) => {
  if (action.type === "userInfo/logout") {
    storage.removeItem("persist:root");

    state = {} as RootState;
  }

  return persistedReducer(state, action);
};

const store = configureStore({
  reducer: rootReducer,
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware({
      serializableCheck: {
        ignoredActions: [FLUSH, REHYDRATE, PAUSE, PERSIST, PURGE, REGISTER],
      },
    }),
});

export const persistor = persistStore(store);
export type RootState = ReturnType<typeof store.getState>;

export default store;
