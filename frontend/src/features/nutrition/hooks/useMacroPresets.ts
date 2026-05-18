import { useQuery } from "@tanstack/react-query";
import { getMacroDistributionPresets } from "../api";
import type { MacroDistribution } from "../type";

export function useMacroPresets() {
  return useQuery<MacroDistribution[]>({
    queryKey: ["macroPresets"],
    queryFn: getMacroDistributionPresets,
  });
}