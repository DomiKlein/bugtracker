import axios, { AxiosInstance, AxiosResponse } from "axios";

export default class ServiceClient {
  static readonly headers: Readonly<Record<string, string | boolean>> = {
    Accept: "application/json",
    "Content-Type": "application/json; charset=utf-8",
    "Access-Control-Allow-Credentials": true,
    "X-Requested-With": "XMLHttpRequest",
  };

  private http: AxiosInstance;

  constructor() {
    this.http = ServiceClient.initHttp();
  }

  /** Initializes the HTTP client. */
  private static initHttp(): AxiosInstance {
    const instance = axios.create({
      baseURL: "https://localhost:8080/api",
      withCredentials: true,
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

  /** Performs a GET request. */
  public get<T = any, R = AxiosResponse<T>>(url: string): Promise<R> {
    return this.http.get<T, R>(url);
  }

  /** Performs a POST request. */
  public post<T = any, R = AxiosResponse<T>>(
    url: string,
    data?: T
  ): Promise<R> {
    return this.http.post<T, R>(url, data);
  }

  /** Performs a PUT request. */
  public put<T = any, R = AxiosResponse<T>>(url: string, data?: T): Promise<R> {
    return this.http.put<T, R>(url, data);
  }

  /** Performs a DELETE request. */
  public delete<T = any, R = AxiosResponse<T>>(url: string): Promise<R> {
    return this.http.delete<T, R>(url);
  }
}
