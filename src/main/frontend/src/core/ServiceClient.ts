import axios, { AxiosInstance, AxiosResponse } from "axios";
import { AuthenticationResponse, Ticket, User } from "../DatabaseEntities";

export default class ServiceClient {
  private http: AxiosInstance;
  private authToken?: string;

  constructor() {
    this.http = this.initHttp();
  }

  /** Initializes the HTTP client. */
  private initHttp(): AxiosInstance {
    const instance = axios.create({
      baseURL: "http://localhost:8080/api",
      headers: {},
    });

    instance.interceptors.request.use((req) => {
      if (this.authToken) {
        req.headers = { ...req.headers, Authorization: this.authToken };
      }
      return req;
    });
    return instance;
  }

  /** Sets the authentication used for later requests. */
  public setAuthToken(token: string) {
    this.authToken = token;
  }

  /** Removes all tokens for further requests. */
  public clearAuth() {
    this.authToken = undefined;
  }

  /** Get JWT tokens. */
  public login(
    username: string,
    password: string
  ): Promise<AxiosResponse<AuthenticationResponse>> {
    return this.post("/auth/login", { username, password });
  }

  /** Finds a user by a username. */
  public findByUsername(username: string): Promise<AxiosResponse<User>> {
    return this.get(`/users/${username}`);
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
