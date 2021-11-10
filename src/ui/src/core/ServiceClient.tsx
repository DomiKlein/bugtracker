import axios, { AxiosInstance, AxiosResponse } from "axios";
import { Ticket } from "../DatabaseEntities";

export default class ServiceClient {
  private http: AxiosInstance;

  constructor() {
    this.http = ServiceClient.initHttp();
  }

  /** Initializes the HTTP client. */
  private static initHttp(): AxiosInstance {
    const instance = axios.create({
      baseURL: "https://localhost:8080/api",
      headers: {
        "X-Requested-With": "XMLHttpRequest",
      },
    });

    instance.interceptors.response.use(
      (response) => response,
      (error) => this.handleError(error)
    );

    return instance;
  }

  /** The error handler for requests. */
  private static handleError(error: any) {
    throw new Error("Failed to execute service call. " + error);
  }

  /** Returns all tickets. */
  public getAllTickets(): Promise<Ticket[]> {
    return this.get("/tickets");
  }
  /** Performs a GET request. */
  private get<T = any, R = AxiosResponse<T>>(url: string): Promise<R> {
    return this.http.get<T, R>(url);
  }
}
