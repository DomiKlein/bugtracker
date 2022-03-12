import axios, { AxiosInstance, AxiosRequestConfig, AxiosResponse } from "axios";
import { AuthenticationResponse, Ticket, User } from "../DatabaseEntities";

export default class ServiceClient {
  private http: AxiosInstance;
  private authToken?: string;

  private readonly rootConfig: AxiosRequestConfig = {
    baseURL: "http://localhost:8080/api",
  };

  constructor() {
    this.http = ServiceClient.initHttp();
  }

  /** Initializes the HTTP client. */
  private static initHttp(): AxiosInstance {
    const instance = axios.create();

    instance.interceptors.response.use((response) => response);

    return instance;
  }

  /** Sets the authentication used for later requests. */
  public setAuthToken(token: string) {
    this.authToken = token;
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
    return this.http.get<T, R>(url, this.configWithAuth());
  }

  /** Performs a POST request. */
  private post<T = any, R = AxiosResponse<T>>(
    url: string,
    payload: any
  ): Promise<R> {
    return this.http.post<T, R>(url, payload, this.configWithAuth());
  }

  /** Performs a PUT request. */
  private put<T = any, R = AxiosResponse<T>>(
    url: string,
    payload: any
  ): Promise<R> {
    return this.http.put<T, R>(url, payload, this.configWithAuth());
  }

  /** Performs a DELETE request. */
  private delete<T = any, R = AxiosResponse<T>>(url: string): Promise<R> {
    return this.http.delete<T, R>(url, this.configWithAuth());
  }

  private configWithAuth(): AxiosRequestConfig {
    let config = { ...this.rootConfig };
    if (this.authToken) {
      config = {
        ...config,
        headers: {
          Bearer: this.authToken,
        },
      };
    }
    return config;
  }
}
