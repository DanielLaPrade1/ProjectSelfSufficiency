import { useQuery } from "@tanstack/react-query";
import { getCrops } from "../api";
import type { Crop } from "../type";

export function useCrops() {
  return useQuery<Crop[]>({
    queryKey: ["crops"],
    queryFn: getCrops,
  });
}