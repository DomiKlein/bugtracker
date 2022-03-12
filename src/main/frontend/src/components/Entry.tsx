import { Provider } from "react-redux";
import App from "./App";
import store from "../core/store/Store";
import { persistor } from "../core/store/Store";
import { PersistGate } from "redux-persist/integration/react";
import Loader from "./util/Loader";

export default function Entry() {
  return (
    <Provider store={store}>
      <PersistGate persistor={persistor} loading={<Loader />}>
        <App />
      </PersistGate>
    </Provider>
  );
}
