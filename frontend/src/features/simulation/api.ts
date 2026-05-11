import { api } from "../../api/client";
import type { SimulationRequest, SimulationResponse } from "./type";

export async function runSimulation(request: SimulationRequest): Promise<SimulationResponse> {
  const response = await api.post('/simulation/run', request)
  return response.data
}