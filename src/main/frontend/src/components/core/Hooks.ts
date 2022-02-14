import ServiceClient from "../../core/ServiceClient";

/** Hook for obtaining a service client. */
export function useServiceClient() {
  return new ServiceClient();
}
