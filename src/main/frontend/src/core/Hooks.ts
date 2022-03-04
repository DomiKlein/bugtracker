import ServiceClient from "./ServiceClient";

/** Hook for obtaining a service client. */
export function useServiceClient() {
  return new ServiceClient();
}
