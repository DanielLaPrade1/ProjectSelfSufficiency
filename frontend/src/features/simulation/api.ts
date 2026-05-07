import { api } from "../../api/client";

export async function runSimulation() {
  const response = await api.get('/simulation/run')
  return response.data
}