import ServiceClient from "./ServiceClient";
import { TypedUseSelectorHook, useDispatch, useSelector } from "react-redux";
import type { RootState, AppDispatch } from "./store/Store";

/** Hook for obtaining a service client. */
export function useServiceClient() {
  return new ServiceClient();
}

// Redux
export const useAppDispatch = () => useDispatch<AppDispatch>();
export const useAppSelector: TypedUseSelectorHook<RootState> = useSelector;
