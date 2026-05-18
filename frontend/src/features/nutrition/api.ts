import { api } from "../../api/client";
import type { MacroDistribution } from "./type";

export async function getMacroDistributionPresets(): Promise<MacroDistribution[]> {
  const response = await api.get('/nutrition/macros/presets')
  return response.data
}