import { api } from "../../api/client";
import type { Crop } from "./type";

export async function getCrops(): Promise<Crop[]> {
  const response = await api.get('/crops')
  return response.data
}