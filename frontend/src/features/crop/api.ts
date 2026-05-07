import { api } from "../../api/client";

export async function getCrops() {
  const response = await api.get('/crops')
  return response.data
}