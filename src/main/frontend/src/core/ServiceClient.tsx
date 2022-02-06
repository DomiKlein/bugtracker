import axios, { AxiosInstance, AxiosResponse } from "axios";
import { Ticket, User } from "../DatabaseEntities";

export default class ServiceClient {
  private http: AxiosInstance;

  constructor() {
    this.http = ServiceClient.initHttp();
  }

  /** Initializes the HTTP client. */
  private static initHttp(): AxiosInstance {
    const instance = axios.create({
      baseURL: "http://localhost:8080/api",
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

  /** Returns all users. */
  public getAllUsers(): Promise<AxiosResponse<User[]>> {
    return this.get("/users");
  }

  /** Returns all tickets. */
  public getAllTickets(): Promise<AxiosResponse<Ticket[]>> {
    return this.get("/tickets");
  }

  /** Creates a ticket. */
  public createTicket(ticket: Ticket): Promise<AxiosResponse<Ticket>> {
    return this.post("/tickets", ticket);
  }

  /** Updates a ticket. */
  public updateTicket(ticket: Ticket): Promise<AxiosResponse<Ticket>> {
    return this.put("/tickets", ticket);
  }

  /** Deletes a ticket. */
  public deleteTicket(ticketId: number): Promise<AxiosResponse<Ticket>> {
    return this.delete(`/tickets/${ticketId}`);
  }

  /** Performs a GET request. */
  private get<T = any, R = AxiosResponse<T>>(url: string): Promise<R> {
    return this.http.get<T, R>(url);
  }

  /** Performs a POST request. */
  private post<T = any, R = AxiosResponse<T>>(
    url: string,
    payload: any
  ): Promise<R> {
    return this.http.post<T, R>(url, payload);
  }

  /** Performs a PUT request. */
  private put<T = any, R = AxiosResponse<T>>(
    url: string,
    payload: any
  ): Promise<R> {
    return this.http.put<T, R>(url, payload);
  }

  /** Performs a DELETE request. */
  private delete<T = any, R = AxiosResponse<T>>(url: string): Promise<R> {
    return this.http.delete<T, R>(url);
  }
}
