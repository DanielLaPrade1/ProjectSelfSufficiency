import axios from "axios";

export const api = axios.create({
  baseURL: 'http://localhost:8080/api/v1',
  withCredentials: true,
  withXSRFToken: true,
  xsrfCookieName: 'XSRF-TOKEN',
  xsrfHeaderName: "X-XSRF-TOKEN"
})